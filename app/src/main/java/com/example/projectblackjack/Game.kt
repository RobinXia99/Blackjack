package com.example.projectblackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Game : AppCompatActivity() {

    private lateinit var myScore: TextView
    private lateinit var dealerScore: TextView
    lateinit var myCard1: ImageView
    lateinit var myCard2: ImageView
    lateinit var myCard3: ImageView
    lateinit var myCard4: ImageView
    lateinit var myCard5: ImageView
    lateinit var myCard6: ImageView
    private lateinit var dealercard1: ImageView
    private lateinit var dealercard2: ImageView
    private lateinit var dealercard3: ImageView
    private lateinit var dealercard4: ImageView
    private lateinit var dealercard5: ImageView
    private lateinit var dealercard6: ImageView
    private lateinit var prompt: TextView
    lateinit var playAgain: Button
    lateinit var betFragment: Button
    lateinit var hitBtn: Button
    lateinit var standBtn: Button
    lateinit var roundCount: TextView
    lateinit var balanceCount: TextView
    var round = 1 // Round is every iteration of the game after startGame()
    var turn = 1 // turn is everytime I get the option hit or stand
    var betAmount = 0  // The betting amount for every round. Takes value from BettingFragment
    var purse = Balance(1000)  // The starting balance
    val dealerhand = theDealer(0) // The Dealers score
    val playerhand = MyHand(0)  // My score
    val DeckOfCards = Deck()
    var newCard = Cards()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        myCard1 = findViewById(R.id.mycard1)
        myCard2 = findViewById(R.id.mycard2)
        myCard3 = findViewById(R.id.mycard3)
        myCard4 = findViewById(R.id.mycard4)
        myCard5 = findViewById(R.id.mycard5)
        myCard6 = findViewById(R.id.mycard5)
        dealercard1 = findViewById(R.id.dealercard1)
        dealercard2 = findViewById(R.id.dealercard2)
        dealercard3 = findViewById(R.id.dealercard3)
        dealercard4 = findViewById(R.id.dealercard4)
        dealercard5 = findViewById(R.id.dealercard5)
        dealercard6 = findViewById(R.id.dealercard6)
        myScore = findViewById(R.id.myScore)
        dealerScore = findViewById(R.id.dealerScore)
        prompt = findViewById(R.id.prompter)
        hitBtn = findViewById(R.id.hitBtn)
        standBtn = findViewById(R.id.standBtn)
        playAgain = findViewById(R.id.nextroundBtn)
        betFragment = findViewById(R.id.betFragment)
        roundCount = findViewById(R.id.roundView)
        balanceCount = findViewById(R.id.balanceView)



//--------------------------------------------------
        startGame()
//--------------------------------------------------
        hitBtn.setOnClickListener() {
            when (turn) {
                1 -> {
                    playerhand.draw(GetCard().cardValue)
                    myCard3.setImageResource(newCard.cardImage)
                    myCard3.visibility = View.VISIBLE
                    myScore.text = playerhand.score.toString()
                }
                2 -> {
                    playerhand.draw(GetCard().cardValue)
                    myCard4.setImageResource(newCard.cardImage)
                    myCard4.visibility = View.VISIBLE
                    myScore.text = playerhand.score.toString()
                }
                3 -> {
                    playerhand.draw(GetCard().cardValue)
                    myCard5.setImageResource(newCard.cardImage)
                    myCard5.visibility = View.VISIBLE
                    myScore.text = playerhand.score.toString()
                }
                4 -> {
                    playerhand.draw(GetCard().cardValue)
                    myCard6.setImageResource(newCard.cardImage)
                    myCard6.visibility = View.VISIBLE
                    myScore.text = playerhand.score.toString()
                }
            }
            turn++
            if (playerhand.score >= 21) {
                hitBtn.visibility = View.GONE
                standBtn.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        dealersTurn()
                    }, 3000 // delay in milliseconds
                )
                if (playerhand.score > 21) {
                    prompt.text = "BUST"
                }
            }
        }
        standBtn.setOnClickListener() {
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            dealersTurn()
        }
        playAgain.setOnClickListener() {
            purse.balance -= betAmount
            balanceCount.text = "Balance: ${purse.balance}$"
            resetRound()
            startGame()
            playAgain.visibility = View.GONE
            betFragment.visibility = View.GONE

        }

        betFragment.setOnClickListener() {
            val betting = BettingFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, betting, "Bet")
            transaction.commit()
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            playAgain.visibility = View.GONE
            betFragment.visibility = View.GONE
            betAmount = 0
        }

    }

    fun startGame() {
        roundCount.text = "Round: ${round}"
        balanceCount.text = "Balance: ${purse.balance}$"

        hitBtn.visibility = View.VISIBLE
        standBtn.visibility = View.VISIBLE

        dealCards()
        if (playerhand.score == 21) {
            prompt.text = "BLACKJACK!"
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    prompt.text = " "
                    dealersTurn()

                }, 3000 // delay in milliseconds
            )

        } else if (playerhand.score > 21) {
            prompt.text = "BUST"
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    prompt.text = " "
                    dealersTurn()

                }, 3000 // delay in milliseconds
            )
        }

    }

    fun dealCards() {

        DeckOfCards.createDeck()

        playerhand.draw(GetCard().cardValue)
        myCard1.setImageResource(newCard.cardImage)
        playerhand.draw(GetCard().cardValue)
        myCard2.setImageResource(newCard.cardImage)
        myScore.text = playerhand.score.toString()

        dealerhand.draw(GetCard().cardValue)
        dealercard1.setImageResource(newCard.cardImage)
        dealerScore.text = dealerhand.score.toString()
    }

    fun dealersTurn() {
        val dealerCards = arrayOf(dealercard2, dealercard3, dealercard4, dealercard5, dealercard6)
        var cardDrawIndex = 0
        hitBtn.visibility = View.GONE
        standBtn.visibility = View.GONE
        while (dealerhand.score < 17) {
            dealerhand.draw(GetCard().cardValue)
            dealerCards[cardDrawIndex].setImageResource(newCard.cardImage)
            dealerCards[cardDrawIndex].visibility = View.VISIBLE
            dealerScore.text = dealerhand.score.toString()
            cardDrawIndex++
            turn++
        }

            compareScores()


    }

    fun compareScores() {
        if (playerhand.score > 21 && dealerhand.score > 21 || playerhand.score == dealerhand.score) {
            prompt.text = "PUSH"
            endOfRoundPrompt()
            purse.balance += betAmount
            balanceCount.text = "Balance: ${purse.balance}$"

        } else if ((playerhand.score <= 21 && dealerhand.score > 21) || (playerhand.score > dealerhand.score && playerhand.score <= 21)) {
            prompt.text = "YOU WIN"
            endOfRoundPrompt()
            purse.balance += (betAmount * 2)
            balanceCount.text = "Balance: ${purse.balance}$"

        } else {
            prompt.text = "YOU LOSE"
            endOfRoundPrompt()
            if (betAmount > purse.balance) {
                betAmount = 0
            }
        }
    }

    fun endOfRoundPrompt() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                prompt.text = " "
                playAgain.visibility = View.VISIBLE
                betFragment.visibility = View.VISIBLE
                round++
                roundCount.text = "Round: ${round}"

            }, 3000 // delay in milliseconds
        )
    }


    fun resetRound() {
        val dealerCards = arrayOf(dealercard2, dealercard3, dealercard4, dealercard5, dealercard6)
        val playerCards = arrayOf(myCard3, myCard4, myCard5, myCard6)
        playerhand.score = 0
        dealerhand.score = 0
        turn = 1
        for (c in dealerCards) {
            c.visibility = View.INVISIBLE
        }
        for (c in playerCards) {
            c.visibility = View.INVISIBLE
        }
        dealercard2.setImageResource(R.drawable.carddesign)
        dealercard2.visibility = View.VISIBLE
    }

    fun GetCard(): Cards {
        newCard = DeckOfCards.getNewCard()
        return newCard
    }

    fun showBtns() {
        playAgain.visibility = View.VISIBLE
        betFragment.visibility = View.VISIBLE
    }


}
