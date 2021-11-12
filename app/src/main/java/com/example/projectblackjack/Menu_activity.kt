package com.example.projectblackjack

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Menu_activity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    lateinit var db: AppDatabase
    var mediaPlayer: MediaPlayer? = null
    lateinit var sound: ImageView
    lateinit var rulesDialog: ImageView
    lateinit var highscoreView: CardView
    var listOfHighscores = mutableListOf<Highscore>()
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        job = Job()
        db = AppDatabase.getInstance(this)

        val rulesBtn = findViewById<Button>(R.id.rulesBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val dialogBtn = findViewById<Button>(R.id.dialogBtn)
        val scoreBtn = findViewById<Button>(R.id.scoreBtn)
        val closeBtn = findViewById<Button>(R.id.closeBtn)
        highscoreView = findViewById(R.id.highscoreView)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = HighscoreAdapter(this, listOfHighscores)

        rulesDialog = findViewById(R.id.rulesDialog)
        sound = findViewById(R.id.sound)


        /*Music created. Music is looping. Music started.
        !! means variable is mutable and could be null at this point, but I assure that it's not*/
        mediaPlayer = MediaPlayer.create(this, R.raw.solitaire)
        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()

        //If music is playing, music pauses. Else music resumes and image changes.
        sound.setOnClickListener() {
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
        // Opens the rule window
        rulesBtn.setOnClickListener() {
            rulesDialog.visibility = View.VISIBLE
            dialogBtn.visibility = View.VISIBLE
            rulesBtn.visibility = View.GONE
            playBtn.visibility = View.GONE

        }
        // Closes the rule window
        dialogBtn.setOnClickListener() {
            rulesDialog.visibility = View.GONE
            dialogBtn.visibility = View.GONE
            rulesBtn.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE


        }
        // Opens the highscore window
        scoreBtn.setOnClickListener() {

            closeBtn.visibility = View.VISIBLE
            rulesBtn.visibility = View.GONE
            playBtn.visibility = View.GONE
            scoreBtn.visibility = View.GONE
            highscoreView.visibility = View.VISIBLE



        }
        // Closes the highscore window
        closeBtn.setOnClickListener() {
            highscoreView.visibility = View.GONE
            closeBtn.visibility = View.GONE
            rulesBtn.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE
            scoreBtn.visibility = View.VISIBLE

        }


    }


    // Starts game_activity.
    private fun startGame() {
        val intent = Intent(this, Game_activity::class.java)
        mediaPlayer!!.pause()
        sound.setImageResource(R.drawable.soundoff)

        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()

        mediaPlayer!!.start()
        sound.setImageResource(R.drawable.sound)
        
        getHighscores()



    }

    fun getHighscores() {
        val highscores = async(Dispatchers.IO) {
            db.highscoreDao.getAll()
        }
        launch {
            val list = highscores.await().toMutableList()

            for(i in list) {
                listOfHighscores.add(i)
            }

            listOfHighscores.sortByDescending { it.score }
            var position = 1

            for (score in listOfHighscores) {
                score.position = position
                position++
            }

        }

    }


}
