package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.data.Actor

class ActorAdapter(private val actor: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_detail, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bin(actor[position])
    }

    override fun getItemCount(): Int = actor.size

    class DetailsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvName = item.findViewById<TextView>(R.id.tv_actor_name)
        private val ivActor = item.findViewById<ImageView>(R.id.iv_actor_details)


        fun bin(actor: Actor) {
            tvName.text = actor.name
            Glide.with(itemView.context)
                .load(actor.picture)
                .into(ivActor)
        }
    }
}