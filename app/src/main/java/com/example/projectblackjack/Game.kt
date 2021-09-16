package com.example.projectblackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import java.util.Collections.shuffle

class Game : AppCompatActivity() {

    private lateinit var myScore: TextView
    lateinit var dealerScore: TextView
    lateinit var myCard1: ImageView
    lateinit var myCard2: ImageView
    private lateinit var dealercard1: ImageView
    private lateinit var dealercard2: ImageView

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_game)

        myCard1 = findViewById(R.id.mycard1)
        myCard2 = findViewById(R.id.mycard2)
        dealercard1 = findViewById(R.id.dealercard1)
        dealercard2 = findViewById(R.id.dealercard2)
        myScore = findViewById(R.id.myScore)
        dealerScore = findViewById(R.id.dealerScore)
        var dealerhand = theDealer(0)
        var playerhand = MyHand(0)


        fun dealCards() {

            val DeckOfCards = mutableListOf<Cards>()
            var value = 2
            var cardIndex = 1


            for (img in Cards.cardIMG) {
                DeckOfCards.add(Cards(value, img))
                if (value < 10) {
                    value++

                } else if (cardIndex == 12 || cardIndex == 25 || cardIndex == 38
                    || cardIndex == 51) {
                    value++

                } else if(cardIndex == 13 || cardIndex == 26 || cardIndex == 39 || cardIndex == 52) {
                    value = 2
                }
                cardIndex++

            }
            for (i in 0..51) {

                Log.d("Cards", "${DeckOfCards[i].cardValue}  ${DeckOfCards[i].cardImage}")
                //2,3,4,5,6,7,8,9,10,10,10,10,11 - 2,3,4,5,6,7,8,9,10,10,10,10,11 value loop
                //1,2,3,4,5,6,7,8,9,10,11,12,13 - 14,15,16,17,18,19,20,21,22,23,24,25 card index
            }


            DeckOfCards.shuffle()


            playerhand.draw(DeckOfCards[0].cardValue)
            playerhand.draw(DeckOfCards[1].cardValue)
            myScore.text = playerhand.score.toString()
            myCard1.setImageResource(DeckOfCards[0].cardImage)
            myCard2.setImageResource(DeckOfCards[1].cardImage)

            dealerhand.draw(DeckOfCards[2].cardValue)
            dealerScore.text = dealerhand.score.toString()
            dealerhand.draw(DeckOfCards[3].cardValue)
            dealercard1.setImageResource(DeckOfCards[2].cardImage)


        }

        fun startGame() {
            dealCards()
        }
        startGame()


    }
}
