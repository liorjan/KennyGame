package com.example.kennygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score = 0

        imageArray = arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9)

        hideImages()

        object : CountDownTimer(10000,1000){
            override fun onFinish() {
                timeText.text = "Times off"
                handler.removeCallbacks(runnable)
                for (image in imageArray)
                    image.visibility = View.INVISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                val p0:Int = (millisUntilFinished/1000).toInt()
                timeText.text = "Time: " + p0
            }

        }.start()
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                //hide all images
                for (image in imageArray)
                {
                    image.visibility = View.INVISIBLE
                }
                //init random function
                val random = Random
                //random a number between 0 - 8
                val index = random.nextInt(8 - 0)
                //show selected image
                imageArray[index].visibility = View.VISIBLE
                //run this function again in 0.5 sec
                handler.postDelayed(runnable,500)
            }
        }

        handler.post(runnable)
    }

    fun increaseScore(view: android.view.View) {
        score++
        scoreText.text = "score: " + score
    }
}