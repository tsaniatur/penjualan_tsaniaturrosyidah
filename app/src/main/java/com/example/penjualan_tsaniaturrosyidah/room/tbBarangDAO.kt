package com.example.penjualan_tsaniaturrosyidah.room

import androidx.room.*

@Dao
interface tbBarangDAO{
    @Insert
    fun addtbBarang(tbbrg: tbBarang)

    @Update
    fun updatetbBarang(tbbrg: tbBarang)

    @Delete
    fun deletetbBarang(tbbrg: tbBarang)

    @Query("SELECT * FROM tbbarang")
    fun tampilbarang(): List<tbBarang>

    @Query("SELECT * FROM tbbarang WHERE id_brg=:idBarang")
    fun tampilkan(idBarang: Int): List<tbBarang>
}