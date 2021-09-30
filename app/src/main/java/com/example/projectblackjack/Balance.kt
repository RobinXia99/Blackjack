package com.example.projectblackjack

class Balance(var balance: Int) {
    fun win (betAmount: Int) {
        balance += betAmount*2
    }
    fun push (betAmount: Int) {
        balance += betAmount
    }

}