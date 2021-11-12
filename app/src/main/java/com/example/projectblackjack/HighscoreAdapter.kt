package com.example.projectblackjack

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HighscoreAdapter(val context: Context, val highscores:  List<Highscore>, ) :
    RecyclerView.Adapter<HighscoreAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = highscores.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.highscore_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val highscore = highscores[position]

        holder.score.text = "${highscore.score}$"
        holder.scoreHolder.text = highscore.name
        holder.scoreHolderPosition.text = highscore.position.toString()


        when (highscore.position) {
            1 -> holder.scoreHolderPosition.text = "\uD83E\uDD47"
            2 -> holder.scoreHolderPosition.text = "\uD83E\uDD48"
            3 -> holder.scoreHolderPosition.text = "\uD83E\uDD49"
        }

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val scoreHolder = itemView.findViewById<TextView>(R.id.txtUsername)
        val score = itemView.findViewById<TextView>(R.id.txtScore)
        val scoreHolderPosition = itemView.findViewById<TextView>(R.id.txtPosition)

    }

}
