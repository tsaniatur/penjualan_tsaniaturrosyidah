package com.example.penjualan_tsaniaturrosyidah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_tsaniaturrosyidah.room.tbBarang
import kotlinx.android.synthetic.main.activity_adapter_barang.view.*

class AdapterBarang (private val barang : ArrayList<tbBarang>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<AdapterBarang.tbBarangViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tbBarangViewHolder {
        return tbBarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter_barang, parent, false)
        )
    }

    override fun getItemCount() = barang.size

    override fun onBindViewHolder(holder: tbBarangViewHolder, position: Int) {
       val brg = barang[position]
        holder.view.tvNmabrg.text = brg.nm_brg
        holder.view.tvIdbrg.text = brg.id_brg.toString()
        holder.view.tvIdbrg.setOnClickListener {
            listener.onClick(brg)
        }
        holder.view.ivEdit.setOnClickListener {
            listener.onUpdate(brg)
        }
        holder.view.ivDelete.setOnClickListener {
            listener.onDelete(brg)
        }
    }
class tbBarangViewHolder (val view: View): RecyclerView.ViewHolder(view)

    fun setData (list: List<tbBarang>) {
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(benda: tbBarang)
        fun onUpdate(benda: tbBarang)
        fun onDelete(benda: tbBarang)
    }
}