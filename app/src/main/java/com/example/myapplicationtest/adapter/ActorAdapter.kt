package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.model.Actor

class ActorAdapter(private val listActor: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(listActor[position])
    }

    override fun getItemCount(): Int = listActor.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName = itemView.findViewById<TextView>(R.id.tv_actor_name)
        private val ivActor = itemView.findViewById<ImageView>(R.id.iv_actor_details)

        fun bin(actor: Actor) {
            tvName.text = actor.name
            Glide.with(itemView)
                .load(actor.urlImage)
                .into(ivActor)
        }
    }
}