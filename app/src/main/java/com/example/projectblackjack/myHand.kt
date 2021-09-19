package com.example.projectblackjack

class MyHand(var score: Int) : Cards() {

    fun draw (value: Int) {
        score += value

    }
}