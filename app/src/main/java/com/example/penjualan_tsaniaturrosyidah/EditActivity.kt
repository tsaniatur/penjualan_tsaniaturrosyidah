package com.example.penjualan_tsaniaturrosyidah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan_tsaniaturrosyidah.room.Constant
import com.example.penjualan_tsaniaturrosyidah.room.DB_penjualan
import com.example.penjualan_tsaniaturrosyidah.room.tbBarang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { DB_penjualan(this) }
    private var stokbarang: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
        setupView()
        stokbarang = intent.getIntExtra("intent_id", 0)
        Toast.makeText(this, stokbarang.toString(), Toast.LENGTH_SHORT).show()
    }
    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnUpdate.visibility = View.GONE
                btnSimpan.visibility = View.GONE
                et_id.visibility = View.GONE
                tampilkan()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpan.visibility = View.GONE
                tampilkan()
            }
        }
    }

    private fun setupListener(){
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbbarangDao().addtbBarang(
                    tbBarang(et_id.text.toString().toInt(),
                    et_nmbrg.text.toString(),
                    et_hrgbrg.text.toString().toInt(),
                    et_stok.text.toString().toInt())
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbbarangDao().updatetbBarang(
                    tbBarang(stokbarang,
                        et_nmbrg.text.toString(),
                        et_hrgbrg.text.toString().toInt(),
                        et_stok.text.toString().toInt())
                )
                finish()
            }
        }
    }
    fun tampilkan(){
        stokbarang = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val jualan= db.tbbarangDao().tampilkan(stokbarang)[0]
            val dataId:String = jualan.id_brg.toString()
            val harga: String = jualan.hrg_brg.toString()
            val datastok:String = jualan.stok.toString()
            et_id.setText(dataId)
            et_nmbrg.setText(jualan.nm_brg)
            et_hrgbrg.setText(harga)
            et_stok.setText(datastok)
            }
        }
    }