package com.example.projectblackjack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Menu : AppCompatActivity() {


    lateinit var rulesDialog: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rulesBtn = findViewById<Button>(R.id.rulesBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val dialogBtn = findViewById<Button>(R.id.dialogBtn)
        rulesDialog = findViewById(R.id.rulesDialog)

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


    }

    private fun startGame() {
        val intent = Intent(this, Game::class.java)
        startActivity(intent)
    }

}
