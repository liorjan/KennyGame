package com.example.kennygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
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
                timeText.text = "Time's off"
                handler.removeCallbacks(runnable)
                for (image in imageArray)
                    image.visibility = View.INVISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: " + p0 / 1000
            }

        }.start()


    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)

            }
        }

        handler.post { runnable }

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }
    }

    fun increaseScore(view: view) {
        score++

        scoreText.text = "score: " + score
    }

}
