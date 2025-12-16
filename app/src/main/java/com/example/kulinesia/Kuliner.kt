package com.example.kulinesia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kuliner(
    val name:String,
    val description:String,
    val photo:Int
) : Parcelable
