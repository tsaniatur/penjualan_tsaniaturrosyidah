package com.example.penjualan_tsaniaturrosyidah.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbBarang(
    @PrimaryKey(autoGenerate =true)
    val id_brg : Int,
    val nm_brg : String,
    val hrg_brg : Int,
    val stok : Int
)