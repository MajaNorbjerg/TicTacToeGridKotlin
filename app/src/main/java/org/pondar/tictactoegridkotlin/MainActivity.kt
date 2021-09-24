package org.pondar.tictactoegridkotlin

import android.database.CrossProcessCursor
import android.graphics.Color
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.CircularPropagation
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import org.pondar.tictactoegridkotlin.databinding.ActivityMainBinding
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var turn = 0
    val PLAYER1 = 0
    val PLAYER2 = 1
    var winnerHasBeenFound = false
    var counter = 0
    private var fields = IntArray(9)
    lateinit var binding: ActivityMainBinding
    val EMPTY = 0
    val CROSS = 1
    val CIRCLE = 2
    var computer = ComputerPlayer()
    private var chosenField: Int? = null
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //we add click listeners, this, to all our fields
        binding.table.setOnClickListener(this)
        binding.field0.setOnClickListener(this)
        binding.field1.setOnClickListener(this)
        binding.field2.setOnClickListener(this)
        binding.field3.setOnClickListener(this)
        binding.field4.setOnClickListener(this)
        binding.field5.setOnClickListener(this)
        binding.field6.setOnClickListener(this)
        binding.field7.setOnClickListener(this)
        binding.field8.setOnClickListener(this)
        //val btn = findViewById<Button>(R.id.newGameButton)
        binding.newGameButton.setOnClickListener {
            reset()
        }


    }

    private fun reset() {
        counter = 0
        winnerHasBeenFound = false
        turn = PLAYER1
        Log.d("tag", "This is reset")
        for (i in fields.indices) {
            fields[i] = EMPTY
            resetChosen(i)
        }
        binding.newGameButton.setBackgroundColor(Color.LTGRAY)
        binding.whatToDoText.text = "Try tic tac toe again, press a field to start"

        binding.field0.setImageDrawable(null)
        binding.field1.setImageDrawable(null)
        binding.field2.setImageDrawable(null)
        binding.field3.setImageDrawable(null)
        binding.field4.setImageDrawable(null)
        binding.field5.setImageDrawable(null)
        binding.field6.setImageDrawable(null)
        binding.field7.setImageDrawable(null)
        binding.field8.setImageDrawable(null)
    }

    override fun onClick(view: View?) {
        // TODO Here you need to get the ID of the view
        // being pressed and then if the view is pressed
        // you need to first put a "X", then next time
        // put a "O" and also make sure that you cannot
        // put a "O" or a "X" if there is already something.

        Log.d("counter: ", "$counter")
        computer.computerMove = false


        for (fieldIndex in 0..8) { // For each field
            println("ViewID : ${view?.id}")
            if (view?.id == getViewId(fieldIndex)) { // Find the pressed view
                val image = view as ImageView
                Log.d("Field_Clicked", "field 0 pressed")
                if (!winnerHasBeenFound) {
                    if (counter in 0..2) {

                        if (fields[fieldIndex] == EMPTY) { // If field is empty
                            if (turn == PLAYER1) {
                                // Player do your move
                                //--------------------------------------------
                                fields[fieldIndex] = CROSS
                                image.setImageResource(R.drawable.kryds)
                                counter++
                                checkWinner()

                                // Computer do your move
                                //--------------------------------------------
                                handler.postDelayed({
                                    computer.makeAMove(counter, fields, binding)
                                    checkWinner()
                                },400)
                            }else {
                                Toast.makeText(
                                    applicationContext,
                                    "It´s sushi´s turn",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else Toast.makeText(
                            applicationContext,
                            "Theres already a tic or a toe here, choose an empty field",
                            Toast.LENGTH_SHORT
                        ).show()
                        if(counter == 1)
                            binding.whatToDoText.text = "Nice you are the sticks"


                    }  // if counter is 1..2 ends
                    else {

                        if (turn == PLAYER1) {
                            when {
                                fields[fieldIndex] == CROSS -> {
                                    chosenField = fieldIndex // Save index of the chosen one


                                    // Reset all Crosses to not chosen
                                    for ((index, value) in fields.withIndex()) {
                                        println("$index, : : : $value")
                                        if (value == CROSS) {
                                            // It has to change the imageView and not the number
                                            resetChosen(index)
                                        }
                                    }

                                   image.setBackgroundResource(R.drawable.selected)
                                    binding.whatToDoText.text = "Chose a place to put them, or some other sticks"
                                }
                                fields[fieldIndex] == CIRCLE -> {
                                    // Hvis highlighted er valgt
                                    Toast.makeText(
                                        applicationContext,
                                        "You cannot put your pond here, choose an empty field",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Ellers
                                    Toast.makeText(
                                        applicationContext,
                                        "Choose one of your own ponds to move",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                fields[fieldIndex] == EMPTY -> { // If field is empty

                                    if (chosenField != null) { // If highlighted is chosen

                                        // Reset all Crosses to not chosen
                                        findViewById<ImageView>(getViewId(chosenField!!)).setBackgroundResource(R.drawable.border)

                                        findViewById<ImageView>(getViewId(chosenField!!)).setImageDrawable(
                                            null
                                        )

                                        fields[chosenField!!] =
                                            EMPTY // Remove pond from highlighted
                                        fields[fieldIndex] = CROSS // Add new pond

                                        image.setImageResource(R.drawable.kryds) // Set new x
                                        counter++
                                        chosenField = null
                                        checkWinner()


                                        // Computer do your move
                                        //--------------------------------------------
                                        handler.postDelayed({
                                            computer.makeAMove(counter, fields, binding)
                                            checkWinner()
                                        },400)

                                    } else if (chosenField == null) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Choose a pond to move",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "It´s sushi´s turn",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }else{Toast.makeText(
                    applicationContext,
                    "There´s already a winner",
                    Toast.LENGTH_SHORT
                ).show()}
            }
        }


    } //end of clicklistener

    private fun resetChosen(index: Int) {


        if (findViewById<ImageView>(getViewId(index)).id == getViewId(index)) {
            findViewById<ImageView>(getViewId(index)).setBackgroundResource(R.drawable.border)
        }
    }

    private fun checkWinner() {
        if (fields[0] != EMPTY && fields[0] == fields[1] && fields[0] == fields[2] ||
            fields[3] != EMPTY && fields[3] == fields[4] && fields[3] == fields[5] ||
            fields[6] != EMPTY && fields[6] == fields[7] && fields[6] == fields[8] ||
            fields[0] != EMPTY && fields[0] == fields[3] && fields[0] == fields[6] ||
            fields[1] != EMPTY && fields[1] == fields[4] && fields[1] == fields[7] ||
            fields[2] != EMPTY && fields[2] == fields[5] && fields[2] == fields[8] ||
            fields[0] != EMPTY && fields[0] == fields[4] && fields[0] == fields[8] ||
            fields[2] != EMPTY && fields[2] == fields[4] && fields[2] == fields[6]
        ) {
            // empty 0, cross 1, circle 2
            winnerHasBeenFound = true
            binding.newGameButton.setBackgroundColor(Color.rgb(100,240,100))

            when (turn) {
                PLAYER1 -> {
                    Toast.makeText(applicationContext, "Chopsticks is the winner", Toast.LENGTH_SHORT)
                        .show()
                    binding.whatToDoText.text = "Congratulations to you! Chopsticks is the winner"
                }
                PLAYER2 -> {
                    Toast.makeText(applicationContext, "Sushi is the winner", Toast.LENGTH_SHORT)
                        .show()
                    binding.whatToDoText.text = "Better luck next time! Sushi is the winner"
                }
                else -> {
                    Toast.makeText(applicationContext, "Its a tie", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if (turn == PLAYER1) turn = PLAYER2
            else if (turn == PLAYER2) turn = PLAYER1
        }
    }

    private fun getViewId(index: Int): Int {
        return when (index) {
            0 -> R.id.field0
            1 -> R.id.field1
            2 -> R.id.field2
            3 -> R.id.field3
            4 -> R.id.field4
            5 -> R.id.field5
            6 -> R.id.field6
            7 -> R.id.field7
            8 -> R.id.field8
            else -> R.id.field0
        }
    }
}
