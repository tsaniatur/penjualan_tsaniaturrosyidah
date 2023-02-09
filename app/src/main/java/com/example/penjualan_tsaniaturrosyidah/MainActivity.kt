package com.example.penjualan_tsaniaturrosyidah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan_tsaniaturrosyidah.room.Constant
import com.example.penjualan_tsaniaturrosyidah.room.DB_penjualan
import com.example.penjualan_tsaniaturrosyidah.room.tbBarang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { DB_penjualan(this) }
    lateinit var adapterBarang: AdapterBarang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListener()
        setUpRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()

    }
    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val niaga = db.tbbarangDao().tampilbarang()
            Log.d("Main Activity", "dbResponse:$niaga")
            withContext(Dispatchers.Main){
                adapterBarang.setData(niaga)
            }
        }
    }
    fun setUpListener () {
        btnInput.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    fun intentEdit(stokbarang:Int, intentType:Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", stokbarang)
                .putExtra("intent_type", intentType)
        )
    }
    private fun setUpRecyclerView() {
        adapterBarang = AdapterBarang(arrayListOf(), object:AdapterBarang.OnAdapterListener{

            override fun onClick(benda: tbBarang) {
               intentEdit(benda.id_brg, Constant.TYPE_READ)
            }

            override fun onUpdate(benda: tbBarang) {
                intentEdit(benda.id_brg, Constant.TYPE_UPDATE)
            }

            override fun onDelete(benda: tbBarang) {
                hapusbarang(benda)
            }
        })
        rvListbrg.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterBarang
        }
    }
    fun hapusbarang(benda: tbBarang){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin Hapus ${benda.nm_brg}")
            setNegativeButton("Batal") {dialogInterface, i -> dialogInterface.dismiss()
            }
            setPositiveButton("Ya") {dialogInterface, i -> dialogInterface.dismiss()
            CoroutineScope(Dispatchers.IO).launch {
                db.tbbarangDao().deletetbBarang(benda)
                dialogInterface.dismiss()
                loadData()
            }
        }
    }
        alertDialog.show()
    }
}