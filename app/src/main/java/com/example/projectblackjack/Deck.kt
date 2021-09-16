package com.example.projectblackjack

import android.graphics.drawable.Drawable

open class Deck (numOfCards: Int) {

    /*init {


        val DeckOfCards = mutableListOf<Cards>()

        for (a in 1..numOfCards) {
            for (b in Cards.cardVal) {
                for (c in Cards.cardIMG) {
                    DeckOfCards.add(Cards(b, c))
                }
            }
        }

    }

     */
}

    class Cards(var cardValue: Int = 0, var cardImage: Int = 0) {

        companion object {
            var cardVal = arrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11)
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
