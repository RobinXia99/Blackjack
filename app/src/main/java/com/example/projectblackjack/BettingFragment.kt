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

        var everyOtherClick = 2

        //

        // kelösjkl
        fun AddBet () {
            val gameActivity = activity as Game
            if((gameActivity.purse.balance >= 0)
                &&
                (gameActivity.purse.balance >= chip.tag.toString().toInt())) {

            if ((everyOtherClick % 2) == 0) {
                gameActivity.betAmount += chip.tag.toString().toInt()
                everyOtherClick++
            } else {
                gameActivity.betAmount -= chip.tag.toString().toInt()
                everyOtherClick++
            }
                }
            betTextView.text = gameActivity.betAmount.toString()
        }

        chip25.setOnClickListener() {
            chip = chip25
            AddBet()
        }
        chip50.setOnClickListener() {
            chip = chip50
            AddBet()
        }
        chip100.setOnClickListener() {
            chip = chip100
            AddBet()
        }
        chip200.setOnClickListener() {
            chip = chip200
            AddBet()
        }
        chip500.setOnClickListener() {
            chip = chip500
            AddBet()
        }
        chip1000.setOnClickListener() {
            chip = chip1000
            AddBet()
        }

            betBtn.setOnClickListener() {
            val gameActivity = activity as Game
            gameActivity.showBtns()
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        }

        return view


    }



}

