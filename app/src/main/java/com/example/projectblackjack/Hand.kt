package com.example.projectblackjack

open class Hand(var score: Int = 0) : Cards() {

    val HandOfCards = mutableListOf<Cards>()

    open fun draw(card: Cards) {

    }


}

class DealerHand : Hand() {
    /* draws a card from DeckOfCards, adds the cardvalue to score and stores it in HandOfCards.
    Checks if score is over 21, in which case the Ace will be counted as 1.*/
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
