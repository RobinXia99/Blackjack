package com.example.projectblackjack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class BettingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chip25: ImageView
        val chip50: ImageView
        val chip100: ImageView
        val chip200: ImageView
        val chip500: ImageView
        val chip1000: ImageView
        lateinit var betTextView: TextView
        lateinit var chip: ImageView


        val view = inflater.inflate(R.layout.fragment_betting, container, false,)
        val betBtn = view.findViewById<Button>(R.id.betBtn)
        chip25 = view.findViewById(R.id.chip25)
        chip50 = view.findViewById(R.id.chip50)
        chip100 = view.findViewById(R.id.chip100)
        chip200 = view.findViewById(R.id.chip200)
        chip500 = view.findViewById(R.id.chip500)
        chip1000 = view.findViewById(R.id.chip1000)
        betTextView = view.findViewById(R.id.betNum)


        //

        fun AddBet () {
            val gameActivity = activity as Game

            if ((gameActivity.betAmount + chip.tag.toString().toInt() <= gameActivity.purse.balance)) {
                gameActivity.betAmount += chip.tag.toString().toInt()
            }

            betTextView.text = gameActivity.betAmount.toString()
        }
        fun removeBet () {
            val gameActivity = activity as Game
            if (gameActivity.betAmount >= chip.tag.toString().toInt()) {
                gameActivity.betAmount -= chip.tag.toString().toInt()
                betTextView.text = gameActivity.betAmount.toString()
            }
        }


        var chip25Index = 2
        chip25.setOnClickListener() {
            chip = chip25
            if(chip25Index % 2 == 0) {
                AddBet()
                chip25Index++
            } else {
                removeBet()
                chip25Index++
            }
        }
        var chip50Index = 2
        chip50.setOnClickListener() {
            chip = chip50
            if(chip50Index % 2 == 0) {
                AddBet()
                chip50Index++
            } else {
                removeBet()
                chip50Index++
            }
        }
        var chip100Index = 2
        chip100.setOnClickListener() {
            chip = chip100
            if(chip100Index % 2 == 0) {
                AddBet()
                chip100Index++
            } else {
                removeBet()
                chip100Index++
            }
        }
        var chip200Index = 2
        chip200.setOnClickListener() {
            chip = chip200
            if(chip200Index % 2 == 0) {
                AddBet()
                chip200Index++
            } else {
                removeBet()
                chip200Index++
            }
        }
        var chip500Index = 2
        chip500.setOnClickListener() {
            chip = chip500
            if(chip500Index % 2 == 0) {
                AddBet()
                chip500Index++
            } else {
                removeBet()
                chip500Index++
            }
        }
        var chip1000Index = 2
        chip1000.setOnClickListener() {
            chip = chip1000
            if(chip1000Index % 2 == 0) {
                AddBet()
                chip1000Index++
            } else {
                removeBet()
                chip1000Index++
            }
        }

            betBtn.setOnClickListener() {
                val gameActivity = activity as Game
                gameActivity.showBtns()
                chip25Index = 2
                chip50Index = 2
                chip100Index = 2
                chip200Index = 2
                chip500Index = 2
                chip1000Index = 2
                activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        }

        return view


    }



}

