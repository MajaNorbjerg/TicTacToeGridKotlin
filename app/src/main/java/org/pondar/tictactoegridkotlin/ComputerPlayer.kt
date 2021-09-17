package org.pondar.tictactoegridkotlin

import android.util.Log
import android.widget.ImageView
import org.pondar.tictactoegridkotlin.databinding.ActivityMainBinding
import kotlin.random.Random

class ComputerPlayer {


    fun placeInCorner(fields: IntArray, binding: ActivityMainBinding) {
        val list = listOf(0, 2, 6, 8)
        val randomIndex = Random.nextInt(list.size);
        var randomNumber = list[randomIndex]
        fields[randomNumber]=2 // Set data as player 2
        convertIndex(randomNumber,binding).setImageResource(R.drawable.bolle)
        // here we can use the selected element to print it for example
        Log.d("NUMBER 4 IS CLICKED", "It is clicked")
    }

    private fun convertIndex(index: Int, binding: ActivityMainBinding) : ImageView{
        return when (index) {
            0 -> binding.field0
            1 -> binding.field1
            2 -> binding.field2
            3 -> binding.field3
            4 -> binding.field4
            5 -> binding.field5
            6 -> binding.field6
            7 -> binding.field7
            8 -> binding.field8
            else -> binding.field0
        }

    }
}