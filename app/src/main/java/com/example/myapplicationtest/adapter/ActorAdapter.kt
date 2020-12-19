package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplicationtest.R
import com.example.myapplicationtest.data.Actor

class ActorAdapter :
    RecyclerView.Adapter<ActorAdapter.DetailsViewHolder>() {

    private var actorList = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_detail, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size

    fun swapData(actor: List<Actor>) {
        this.actorList = actor
        notifyDataSetChanged()
    }

    class DetailsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvName = item.findViewById<TextView>(R.id.tv_actor_name)
        private val ivActor = item.findViewById<ImageView>(R.id.iv_actor_details)
        private val transform = MultiTransformation(CenterCrop(), GranularRoundedCorners(16f, 16f,16f,16f))

        fun bind(actor: Actor) {
            tvName.text = actor.name
            Glide.with(itemView.context)
                .load(actor.picture)
                .apply(RequestOptions.bitmapTransform(transform))
                .centerCrop()
                .into(ivActor)
        }
    }
}