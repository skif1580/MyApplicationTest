package com.example.myapplicationtest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.ActorAdapter
import com.example.myapplicationtest.model.Actor
import com.example.myapplicationtest.model.Data

class MovieDetailsFragment : Fragment() {
    private var rvActor: RecyclerView? = null
    var listActor = listOf<Actor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listActor = Data().addActorToList()
        rvActor = view.findViewById(R.id.rv_actor)
        rvActor?.adapter = ActorAdapter(listActor)
        rvActor?.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {

        fun newInstance() =
            MovieDetailsFragment()
    }
}