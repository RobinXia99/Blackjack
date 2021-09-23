package com.example.projectblackjack

class Balance(var balance: Int) {
    fun win (amount: Int) {
        balance += amount
    }

    fun lose (amount: Int) {
        balance -= amount
    }


}