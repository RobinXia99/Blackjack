package com.example.projectblackjack

open class Hand(var score: Int = 0) : Cards() {

    val HandOfCards = mutableListOf<Cards>()

    open fun draw(card: Cards) {

    }


}

class DealerHand : Hand() {

    override fun draw(card: Cards) {
        score += card.cardValue
        HandOfCards.add(card)
        if (score > 21) {
            for (i in HandOfCards) {
                if (i.cardValue == 11) {
                    i.cardValue = 1
                    score -= 10
                    break
                }
            }
        }
    }
}

class PlayerHand : Hand() {

    override fun draw(card: Cards) {
        score += card.cardValue
        HandOfCards.add(card)
        if (score > 21) {
            for (i in HandOfCards) {
                if (i.cardValue == 11) {
                    i.cardValue = 1
                    score -= 10
                    break
                }
            }
        }
    }
}
