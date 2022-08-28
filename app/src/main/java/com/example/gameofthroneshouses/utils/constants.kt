package com.example.gameofthroneshouses.utils

import android.content.Context
import android.content.Intent
import com.example.gameofthroneshouses.ui.DetailsActivity

const val SERVER_URL =  "https://anapioficeandfire.com/"
const val HOUSE_NAME = "HOUSE_NAME"
const val DB_NAME = "GameOfThronesDb"

fun Context.startDetailsActivity(name:String) =
    Intent(this, DetailsActivity::class.java).also {
        it.putExtra(HOUSE_NAME, name)
        startActivity(it)
    }