package com.example.projectblackjack

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

open class Deck(deckNum: Int) : Cards() {





      /*  val DeckOfCards = mutableListOf<Cards>()


       fun dealCards(round: Int) {


            var value = 2
            var cardIndex = 1


            for (img in cardIMG) {
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
            for (i in 0..51) {

                /*Log.d("Cards", "${DeckOfCards[i].cardValue}  ${DeckOfCards[i].cardImage}")
                //2,3,4,5,6,7,8,9,10,10,10,10,11 - 2,3,4,5,6,7,8,9,10,10,10,10,11 value loop
                //1,2,3,4,5,6,7,8,9,10,11,12,13 - 14,15,16,17,18,19,20,21,22,23,24,25 card index*/
            }


            DeckOfCards.shuffle()

        }*/



}

    open class Cards(var cardValue: Int = 0, var cardImage: Int = 0) {

        companion object {
            var cardIMG = arrayOf(
                R.drawable.spades_2,
                R.drawable.spades_3,
                R.drawable.spades_4,
                R.drawable.spades_5,
                R.drawable.spades_6,
                R.drawable.spades_7,
                R.drawable.spades_8,
                R.drawable.spades_9,
                R.drawable.spades_10,
                R.drawable.spades_jack,
                R.drawable.spades_queen,
                R.drawable.spades_king,
                R.drawable.spades_ace,
                R.drawable.hearts_2,
                R.drawable.hearts_3,
                R.drawable.hearts_4,
                R.drawable.hearts_5,
                R.drawable.hearts_6,
                R.drawable.hearts_7,
                R.drawable.hearts_8,
                R.drawable.hearts_9,
                R.drawable.hearts_10,
                R.drawable.hearts_jack,
                R.drawable.hearts_queen,
                R.drawable.hearts_king,
                R.drawable.hearts_ace,
                R.drawable.club_2,
                R.drawable.club_3,
                R.drawable.club_4,
                R.drawable.club_5,
                R.drawable.club_6,
                R.drawable.club_7,
                R.drawable.club_8,
                R.drawable.club_9,
                R.drawable.club_10,
                R.drawable.club_jack,
                R.drawable.club_queen,
                R.drawable.club_king,
                R.drawable.club_ace,
                R.drawable.diamonds_2,
                R.drawable.diamonds_3,
                R.drawable.diamonds_4,
                R.drawable.diamonds_5,
                R.drawable.diamonds_6,
                R.drawable.diamonds_7,
                R.drawable.diamonds_8,
                R.drawable.diamonds_9,
                R.drawable.diamonds_10,
                R.drawable.diamonds_jack,
                R.drawable.diamonds_queen,
                R.drawable.diamonds_king,
                R.drawable.diamonds_ace
            )
        }

    }

