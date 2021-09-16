package com.example.projectblackjack

class MyHand(var score: Int) {

    fun draw (value: Int) {
        score += value
    }
}