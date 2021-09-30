package com.example.projectblackjack

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Menu : AppCompatActivity() {


    var mediaPlayer: MediaPlayer? = null
    lateinit var sound: ImageView
    lateinit var rulesDialog: ImageView
    lateinit var highscoreDialog: ImageView
    lateinit var lbName: TextView
    lateinit var lbName2: TextView
    lateinit var lbName3: TextView
    lateinit var lbScore: TextView
    lateinit var lbScore2: TextView
    lateinit var lbScore3: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rulesBtn = findViewById<Button>(R.id.rulesBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val dialogBtn = findViewById<Button>(R.id.dialogBtn)
        val scoreBtn = findViewById<Button>(R.id.scoreBtn)
        val closeBtn = findViewById<Button>(R.id.closeBtn)
        lbName = findViewById(R.id.nameView)
        lbName2 = findViewById(R.id.nameView2)
        lbName3 = findViewById(R.id.nameView3)
        lbScore = findViewById(R.id.scoreView)
        lbScore2 = findViewById(R.id.scoreView2)
        lbScore3 = findViewById(R.id.scoreView3)

        rulesDialog = findViewById(R.id.rulesDialog)
        sound = findViewById(R.id.sound)
        highscoreDialog = findViewById(R.id.highscoreView)


        /*Music created. Music is looping. Music started.
        !! means variable is mutable and could be null at this point, but I assure that it's not*/
        mediaPlayer = MediaPlayer.create(this, R.raw.solitaire)
        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()

        //If music is playing, music pauses. Else music resumes and image changes.
        sound.setOnClickListener(){
            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                mediaPlayer!!.pause()
                sound.setImageResource(R.drawable.soundoff)
            } else {
                mediaPlayer!!.start()
                sound.setImageResource(R.drawable.sound)
            }
        }

        playBtn.setOnClickListener() {
            startGame()
        }

        rulesBtn.setOnClickListener() {
            rulesDialog.visibility = View.VISIBLE
            dialogBtn.visibility = View.VISIBLE
            rulesBtn.visibility = View.GONE
            playBtn.visibility = View.GONE

        }
        dialogBtn.setOnClickListener() {
            rulesDialog.visibility = View.GONE
            dialogBtn.visibility = View.GONE
            rulesBtn.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE


        }
        scoreBtn.setOnClickListener() {
            highscoreDialog.visibility = View.VISIBLE
            closeBtn.visibility = View.VISIBLE

            rulesBtn.visibility = View.GONE
            playBtn.visibility = View.GONE
            scoreBtn.visibility = View.GONE
            lbName.visibility = View.VISIBLE
            lbName2.visibility = View.VISIBLE
            lbName3.visibility = View.VISIBLE
            lbScore.visibility = View.VISIBLE
            lbScore2.visibility = View.VISIBLE
            lbScore3.visibility = View.VISIBLE



        }
        closeBtn.setOnClickListener() {
            highscoreDialog.visibility = View.GONE
            closeBtn.visibility = View.GONE
            rulesBtn.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE
            scoreBtn.visibility = View.VISIBLE
            lbName.visibility = View.GONE
            lbName2.visibility = View.GONE
            lbName3.visibility = View.GONE
            lbScore.visibility = View.GONE
            lbScore2.visibility = View.GONE
            lbScore3.visibility = View.GONE
        }



    }

    private fun startGame() {
        val intent = Intent(this, Game::class.java)
        mediaPlayer!!.pause()
        sound.setImageResource(R.drawable.soundoff)

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        mediaPlayer!!.start()
        sound.setImageResource(R.drawable.sound)
        val sharedPref = getSharedPreferences("highScore", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "---")
        val score = sharedPref.getInt("score", 0)
        val name2 = sharedPref.getString("name2", "---")
        val score2 = sharedPref.getInt("score2", 0)
        val name3 = sharedPref.getString("name3", "---")
        val score3 = sharedPref.getInt("score3", 0)

        lbName.text = name
        lbName2.text = name2
        lbName3.text = name3
        lbScore.text = score.toString() +"$"
        lbScore2.text = score2.toString() +"$"
        lbScore3.text = score3.toString() +"$"








    }

}
