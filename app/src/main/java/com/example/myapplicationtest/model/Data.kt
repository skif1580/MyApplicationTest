package com.example.myapplicationtest.model

import com.example.myapplicationtest.R

class Data {
    var listActor= mutableListOf<Actor>()
    fun addActorToList():List<Actor>{
        listActor.add(Actor(R.string.movie_cast_1_name.toString(),R.drawable.robert.toString()))
        listActor.add(Actor(R.string.movie_cast_2_name.toString(),R.drawable.chris.toString()))
        listActor.add(Actor(R.string.movie_cast_3_name.toString(),R.drawable.mark.toString()))
        listActor.add(Actor(R.string.movie_cast_4_name.toString(),R.drawable.hemaworth.toString()))
        return listActor
    }
}