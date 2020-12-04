package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.model.Actor
import java.security.AccessController.getContext

class ActorAdapter(private val listActor: List<Actor>, private val star: Int) :
    RecyclerView.Adapter<ActorAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_detail, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bin(listActor[position], star)
    }

    override fun getItemCount(): Int = listActor.size

    class DetailsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvName = item.findViewById<TextView>(R.id.tv_actor_name)
        private val ivActor = item.findViewById<ImageView>(R.id.iv_actor_details)


        fun bin(actor: Actor, star: Int) {
            tvName.text = actor.name
            ivActor.setImageResource(actor.urlImage)
        }
    }
}