package com.example.kulinesia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kuliner(
    val name:String,
    val description:String,
    val photo:Int,
    var origin: String,
    var recipe: String,
    var url: String,
    var time: String ,
    var servings: String ,
    var difficulty: String,
    var ingredients: String
) : Parcelable
