package com.bignerdranch.android.callbackexample
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get a reference to the textview object in the view xml file through its ID
        val textView: TextView = findViewById(R.id.textView)
        //Callback Function loaded on create
        fun myCallbackFunction(myCallback: MyCallback){
            val tWord = textView.text
            val letter = "A"
            //Some basic logic
            if(tWord.toString().contains(letter)) {
                myCallback.onSuccess()
            } else {
                myCallback.onFailure("$tWord does not contain $letter")
            }
        }
        //create an on click listener on the text using the My Callback interface
        textView.setOnClickListener{
            //run the myCallBackFunction
            myCallbackFunction(object: MyCallback{
                //override the interface functions
                override fun onSuccess() {
                    Toast.makeText(this@MainActivity, "Inside Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(error: String) {
                    Toast.makeText(this@MainActivity, "Inside Failure - $error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
//interface containing the methods for implementation
interface MyCallback{
    fun onSuccess()
    fun onFailure(error: String)
}