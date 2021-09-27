package com.example.projectblackjack

class theDealer (var score: Int) : Cards()  {


    fun draw (value: Int) {
        score += value
    }
}