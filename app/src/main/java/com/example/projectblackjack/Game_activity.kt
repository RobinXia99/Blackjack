package com.example.projectblackjack

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Game_activity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null
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
    private lateinit var prompt: TextView // Displays text in the middle depending on event
    lateinit var playAgain: Button // Starts next round
    lateinit var betFragment: Button // Opens my betting window
    lateinit var hitBtn: Button // Draws a card.
    lateinit var standBtn: Button // Ends my turn and the dealer starts
    lateinit var cashoutBtn: Button
    lateinit var confirmName: Button
    lateinit var roundCount: TextView
    lateinit var balanceCount: TextView
    lateinit var cashoutName: EditText
    var round = 1 // Round is every iteration of the game after startGame()
    var turn = 1 // turn is everytime I get the option hit or stand
    var betAmount = 0  // The betting amount for every round. Takes value from BettingFragment
    var purse = Balance(1000)  // The starting balance
    val dealerhand: Hand = DealerHand() // The Dealers score
    val playerhand: Hand = PlayerHand() // My score
    val DeckOfCards = Deck()
    var newCard = Cards()


    val HandOfCards = mutableListOf<Cards>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        myCard1 = findViewById(R.id.mycard1)
        myCard2 = findViewById(R.id.mycard2)
        myCard3 = findViewById(R.id.mycard3)
        myCard4 = findViewById(R.id.mycard4)
        myCard5 = findViewById(R.id.mycard5)
        myCard6 = findViewById(R.id.mycard6)
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
        cashoutBtn = findViewById(R.id.cashoutBtn)
        confirmName = findViewById(R.id.confirmName)
        betFragment = findViewById(R.id.betFragment)
        roundCount = findViewById(R.id.roundView)
        balanceCount = findViewById(R.id.balanceView)
        cashoutName = findViewById(R.id.cashoutName)

//--------------------------------------------------
        startGame()
//--------------------------------------------------

        /* Draws a card depending on what turn it is. Sets the new image and makes the card visible
        * My turn ends when I either hit 21 or more. I can draw 4 cards max after
        * my first 2 because the probability of getting a bad score with 6 cards is very low.  */
        hitBtn.setOnClickListener() {
            when (turn) {
                turn -> {
                    hit()
                }

            }
            turn++
            if (playerhand.score >= 21) {
                hitBtn.visibility = View.GONE
                standBtn.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        dealersTurn()
                    }, 2000 // delay in milliseconds
                )
                if (playerhand.score > 21) {
                    prompt.text = "BUST"
                }
            }
        }
        // Ends my turn immediately and starts dealersTurn()
        standBtn.setOnClickListener() {
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            dealersTurn()
        }
        // Subtracts my bet from my balance, resets the round and resumes the game.
        playAgain.setOnClickListener() {
            purse.balance -= betAmount
            if (purse.balance > 0) {
                mediaPlayer = MediaPlayer.create(this, R.raw.losemoney)
                mediaPlayer!!.start()
            }
            balanceCount.text = "Balance: ${purse.balance}$"
            resetRound()
            startGame()
            playAgain.visibility = View.GONE
            betFragment.visibility = View.GONE
            cashoutBtn.visibility = View.GONE

        }
        // Inflates my betting fragment and resets my bet to 0.
        betFragment.setOnClickListener() {
            val betting = BettingFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, betting, "Bet")
            transaction.commit()
            hitBtn.visibility = View.GONE
            standBtn.visibility = View.GONE
            playAgain.visibility = View.GONE
            betFragment.visibility = View.GONE
            cashoutBtn.visibility = View.GONE
            betAmount = 0
        }
        // Asks for your name and submits your name and score to the highscore list.
        cashoutBtn.setOnClickListener() {
            playAgain.visibility = View.GONE
            betFragment.visibility = View.GONE
            cashoutName.visibility = View.VISIBLE
            confirmName.visibility = View.VISIBLE
        }
        // Runs the saveScore function and navigates to menu activity.
        confirmName.setOnClickListener() {
            val intent = Intent(this, Menu_activity::class.java)
            saveScore()

            startActivity(intent)
        }

    }

    /* Creates a SharePreference object that stores key-values that you can edit, put and get.
    * Getting a highscore pushes the current highscore to 2nd place and so on. The values are permanently
    * stored after using apply(). edit.apply() will clear the object.
    * */
    fun saveScore() {
        val sharedPref = getSharedPreferences("highScore", Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        val name = sharedPref.getString("name", "---")
        val score = sharedPref.getInt("score", 0)
        val name2 = sharedPref.getString("name2", "---")
        val score2 = sharedPref.getInt("score2", 0)
        val name3 = sharedPref.getString("name3", "---")
        val score3 = sharedPref.getInt("score3", 0)
        if (purse.balance > score) {
            edit.putString("name2", name)
            edit.putInt("score2", score)
            edit.putString("name", cashoutName.text.toString())
            edit.putInt("score", purse.balance)

        } else if (purse.balance in (score2 + 1) until score) {
            edit.putString("name3", name2)
            edit.putInt("score3", score2)
            edit.putString("name2", cashoutName.text.toString())
            edit.putInt("score2", purse.balance)
        } else if (purse.balance in (score3 + 1) until score2) {
            edit.putString("name3", cashoutName.text.toString())
            edit.putInt("score3", purse.balance)
        }

        edit.apply()
    }

    /* Starts the game by setting the round and balance. Then calls the dealCard function.
    * If the first cards dealt adds up to 21, the round ends and dealer begins to draw cards. */
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

        }

    }

    /* DeckOfCards.createDeck() creates and shuffles the deck inside the Deck class.
    * .draw() calls the draw function inside the Hand class. It takes the value of getCard()
    * which returns a Card object and adds it to HandOfCards.*/
    fun dealCards() {

        DeckOfCards.createDeck()


        playerhand.draw(getCard())
        myCard1.setImageResource(newCard.cardImage)
        playerhand.draw(getCard())
        myCard2.setImageResource(newCard.cardImage)
        myScore.text = playerhand.score.toString()

        dealerhand.draw(getCard())
        dealercard1.setImageResource(newCard.cardImage)
        dealerScore.text = dealerhand.score.toString()

    }

    /* dealersTurn is called after players turn is over. If player busted, the round is automatically over
    and compareScores is called.*/
    fun dealersTurn() {
        val dealerCards = arrayOf(dealercard2, dealercard3, dealercard4, dealercard5, dealercard6)
        var cardDrawIndex = 0
        hitBtn.visibility = View.GONE
        standBtn.visibility = View.GONE
        if (playerhand.score <= 21) {
            while (dealerhand.score < 17) {
                dealerhand.draw(getCard())
                dealerCards[cardDrawIndex].setImageResource(newCard.cardImage)
                dealerCards[cardDrawIndex].visibility = View.VISIBLE
                dealerScore.text = dealerhand.score.toString()
                cardDrawIndex++
            }
        }

        compareScores()


    }

    /* Compares the score of dealer and player, displays WIN, LOSE or PUSH depending on condition. */
    fun compareScores() {
        if (playerhand.score > 21 && dealerhand.score > 21 || playerhand.score == dealerhand.score) {
            prompt.text = "PUSH"
            endOfRoundPrompt()
            purse.push(betAmount)
            balanceCount.text = "Balance: ${purse.balance}$"

        } else if ((playerhand.score <= 21 && dealerhand.score > 21) || (playerhand.score > dealerhand.score && playerhand.score <= 21)) {
            prompt.text = "YOU WIN"
            mediaPlayer = MediaPlayer.create(this, R.raw.win)
            mediaPlayer!!.start()
            endOfRoundPrompt()
            purse.win(betAmount)
            balanceCount.text = "Balance: ${purse.balance}$"


        } else {
            prompt.text = "YOU LOSE"
            mediaPlayer = MediaPlayer.create(this, R.raw.lose)
            mediaPlayer!!.start()
            endOfRoundPrompt()
            if (betAmount > purse.balance) {
                betAmount = 0
            }
        }
    }

    /* endOfRoundPrompt is called after the scored are compared. It increments the round value and shows
    * additional buttons. */
    fun endOfRoundPrompt() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                prompt.text = " "
                playAgain.visibility = View.VISIBLE
                betFragment.visibility = View.VISIBLE
                cashoutBtn.visibility = View.VISIBLE
                round++
                roundCount.text = "Round: ${round}"

            }, 2000 // delay in milliseconds
        )
    }

    // Round is reset. Every card is turned upside down or made invisible and hands are cleared.
    fun resetRound() {
        val dealerCards = arrayOf(dealercard3, dealercard4, dealercard5, dealercard6)
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
        playerhand.HandOfCards.clear()
        dealerhand.HandOfCards.clear()
    }

    /* Calls the getNewCard function that returns a card from DeckOfCards and applies it to newCard.
    newCard is then added to HandOfCards. */
    fun getCard(): Cards {
        newCard = DeckOfCards.getNewCard()
        HandOfCards.add(newCard)
        return newCard
    }

    // Shows the buttons again after returning from betting fragment.
    fun showBtns() {
        playAgain.visibility = View.VISIBLE
        betFragment.visibility = View.VISIBLE
    }
    // Used by hitBtn. Draws a card and sets the different cards image depending on turn.
    fun hit () {
        val playerCards = arrayOf(myCard3, myCard4, myCard5, myCard6)
        playerhand.draw(getCard())
        playerCards[turn-1].setImageResource(newCard.cardImage)
        playerCards[turn-1].visibility = View.VISIBLE
        myScore.text = playerhand.score.toString()



    }


}
