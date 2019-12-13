package com.example.coroutinesfullexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.NullPointerException
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        download_text.setOnClickListener {
            text_data_google.text = ""
            text_data_facebook.text = ""
            lifecycleScope.launch {
                fetchData()
            }
        }

//        coroutinesException()
    }

    private suspend fun fetchData() {
        val executionTime = measureTimeMillis {
            val googleData = withContext(Dispatchers.IO) {
                getDataFromNetwork("https://www.google.com/")
            }
            text_data_google.text = googleData

            val facebookData = withContext(Dispatchers.IO) {
                getDataFromNetwork("https://www.facebook.com/")
            }
            text_data_facebook.text = facebookData
        }

        Log.d("MainActivity", "Time elapsed for launch $executionTime")

        //region
//        val executionTimeAsync = measureTimeMillis {
//            val googleData1 = lifecycleScope.async(Dispatchers.IO){
//                getDataFromNetwork("https://www.google.com/")
//            }
//
//            val facebookData1 = lifecycleScope.async(Dispatchers.IO){
//                getDataFromNetwork("https://www.facebook.com/")
//            }
//
//            text_data_google.text = googleData1.await()
//            text_data_facebook.text = facebookData1.await()
//        }
//        Log.d("MainActivity", "Time elapsed for async $executionTimeAsync")
        //endregion
    }


    private fun coroutinesException(){
        lifecycleScope.launch {
            try {
                val name: String? = null
                val count = name!!.length
                Log.d("MainActivity", "Length of string is: : $count")
            } catch (ex: NullPointerException) {
                Log.d("MainActivity", "NullPointerException error occurred")
            } catch (ex: Exception) {
                Log.d("MainActivity", "Error occurred}")
            }

            //region
//            val result = async{
//                val name: String? = null
//                name!!.length
//            }
//            Log.d("MainActivity", "After the result from async")
//            try {
//                Log.d("MainActivity", "Length of string is: : ${result.await()}")
//            } catch (ex: NullPointerException) {
//                Log.d("MainActivity", "NullPointerException error occurred in async")
//            } catch (ex: Exception) {
//                Log.d("MainActivity", "Error occurred in async")
//
//            }
            //endregion
        }
    }
}


