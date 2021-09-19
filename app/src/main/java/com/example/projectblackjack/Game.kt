package com.example.projectblackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Collections.shuffle

class Game : AppCompatActivity() {

    private lateinit var myScore: TextView
    lateinit var dealerScore: TextView
    lateinit var myCard1: ImageView
    lateinit var myCard2: ImageView
    lateinit var myCard3: ImageView
    lateinit var myCard4: ImageView
    lateinit var myCard5: ImageView
    private lateinit var dealercard1: ImageView
    private lateinit var dealercard2: ImageView
    private lateinit var dealercard3: ImageView
    private lateinit var dealercard4: ImageView
    private lateinit var dealercard5: ImageView
    private lateinit var prompt: TextView
    var round = 1 // Round is every iteration of the game starting from startgame
    var turn = 1 // turn is everytime I get the option hit or stand

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_game)

        myCard1 = findViewById(R.id.mycard1)
        myCard2 = findViewById(R.id.mycard2)
        myCard3 = findViewById(R.id.mycard3)
        myCard4 = findViewById(R.id.mycard4)
        myCard5 = findViewById(R.id.mycard5)
        dealercard1 = findViewById(R.id.dealercard1)
        dealercard2 = findViewById(R.id.dealercard2)
        dealercard3 = findViewById(R.id.dealercard3)
        dealercard4 = findViewById(R.id.dealercard4)
        dealercard5 = findViewById(R.id.dealercard5)
        myScore = findViewById(R.id.myScore)
        dealerScore = findViewById(R.id.dealerScore)
        prompt = findViewById(R.id.prompter)
        val roundCount = findViewById<TextView>(R.id.roundView)
        val hitBtn = findViewById<Button>(R.id.hitBtn)
        val standBtn = findViewById<Button>(R.id.standBtn)
        val playAgain = findViewById<Button>(R.id.nextroundBtn)
        val dealerhand = theDealer(0)
        val playerhand = MyHand(0)
        var stand: Boolean
        var hit: Boolean
        var bust = false

        val dealerCards = arrayOf(dealercard2, dealercard3, dealercard4, dealercard5)
        val playerCards = arrayOf(myCard3, myCard4, myCard5)
        val DeckOfCards = mutableListOf<Cards>()

        fun dealCards() {


            var value = 2
            var cardIndex = 1


            for (img in Cards.cardIMG) {
                DeckOfCards.add(Cards(value, img))
                if (value < 10) {
                    value++

                } else if (cardIndex == 12 || cardIndex == 25 || cardIndex == 38
                    || cardIndex == 51
                ) {
                    value++

                } else if (cardIndex == 13 || cardIndex == 26 || cardIndex == 39 || cardIndex == 52) {
                    value = 2
                }
                cardIndex++

            }
            /*for (i in 0..51) {

                Log.d("Cards", "${DeckOfCards[i].cardValue}  ${DeckOfCards[i].cardImage}")
                //2,3,4,5,6,7,8,9,10,10,10,10,11 - 2,3,4,5,6,7,8,9,10,10,10,10,11 value loop
                //1,2,3,4,5,6,7,8,9,10,11,12,13 - 14,15,16,17,18,19,20,21,22,23,24,25 card index
            }*/


            DeckOfCards.shuffle()


            playerhand.draw(DeckOfCards[20].cardValue)
            playerhand.draw(DeckOfCards[21].cardValue)
            myScore.text = playerhand.score.toString()
            myCard1.setImageResource(DeckOfCards[20].cardImage)
            myCard2.setImageResource(DeckOfCards[21].cardImage)

            dealerhand.draw(DeckOfCards[30].cardValue)
            dealercard1.setImageResource(DeckOfCards[30].cardImage)
            dealerScore.text = dealerhand.score.toString()
        }

        fun endOfRoundPrompt() {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    prompt.text = " "
                    playAgain.setVisibility(View.VISIBLE)

                }, 4000 // delay in milliseconds
            )
        }


        fun compareScores() {
            if (playerhand.score > 21 && dealerhand.score > 21 || playerhand.score == dealerhand.score) {
                prompt.text = "DRAW"
                endOfRoundPrompt()

            } else if ((playerhand.score <= 21 && dealerhand.score > 21)
                || (playerhand.score > dealerhand.score && playerhand.score < 21)) {
                prompt.text = "YOU WIN"
                endOfRoundPrompt()

            } else {
                prompt.text = "YOU LOSE"
                endOfRoundPrompt()
            }
        }


        fun dealersTurn() {
            var cardDrawIndex = 0
            while (dealerhand.score < 17) {
                dealerhand.draw(DeckOfCards[turn].cardValue)
                dealerCards[cardDrawIndex].setImageResource(DeckOfCards[turn].cardImage)
                dealerCards[cardDrawIndex].setVisibility(View.VISIBLE)
                dealerScore.text = dealerhand.score.toString()
                cardDrawIndex++
                turn++
            }
            if (dealerhand.score >= 17) {
                compareScores()
            }

        }
        hitBtn.setOnClickListener() {
            when (turn) {
                1 -> {
                    playerhand.draw(DeckOfCards[turn].cardValue)
                    myCard3.setImageResource(DeckOfCards[turn].cardImage)
                    myCard3.setVisibility(View.VISIBLE)
                    myScore.text = playerhand.score.toString()
                }
                2 -> {
                    playerhand.draw(DeckOfCards[turn].cardValue)
                    myCard4.setImageResource(DeckOfCards[turn].cardImage)
                    myCard4.setVisibility(View.VISIBLE)
                    myScore.text = playerhand.score.toString()
                }
                3 -> {
                    playerhand.draw(DeckOfCards[turn].cardValue)
                    myCard5.setImageResource(DeckOfCards[turn].cardImage)
                    myCard5.setVisibility(View.VISIBLE)
                    myScore.text = playerhand.score.toString()
                }
            }
            turn++
            if (playerhand.score >= 21) {
                hitBtn.setVisibility(View.GONE)
                standBtn.setVisibility(View.GONE)
                dealersTurn()
            }
        }
        standBtn.setOnClickListener() {
            hitBtn.setVisibility(View.GONE)
            standBtn.setVisibility(View.GONE)
            dealersTurn()
        }



        fun startRound() {
            roundCount.text = "Round: ${round.toString()}"
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    hitBtn.setVisibility(View.VISIBLE)
                    standBtn.setVisibility(View.VISIBLE)
                }, 1500 // delay in milliseconds
            )
            dealCards()
            if (playerhand.score == 21) {
                prompt.text = "BLACKJACK!"
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        hitBtn.setVisibility(View.GONE)
                        standBtn.setVisibility(View.GONE)
                        prompt.text = " "
                        dealersTurn()

                    }, 4000 // delay in milliseconds
                )

            }

        }
        startRound()

        fun resetRound() {
            playerhand.score = 0
            dealerhand.score = 0
            turn = 1
            for (c in dealerCards) {
                c.setVisibility(View.INVISIBLE)
            }
            for (c in playerCards) {
                c.setVisibility(View.INVISIBLE)
            }
            round++
        }

        playAgain.setOnClickListener() {
            resetRound()
            startRound()
            playAgain.setVisibility(View.GONE)

        }

    }
}
