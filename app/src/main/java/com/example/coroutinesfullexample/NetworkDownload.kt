package com.example.coroutinesfullexample

import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL

fun getDataFromNetwork(url: String): String{
    val urlConnection = URL(url).openConnection()
            as HttpURLConnection
    try {
        return urlConnection.inputStream.bufferedReader().readText()
    } finally {
        urlConnection.disconnect()
    }
}

