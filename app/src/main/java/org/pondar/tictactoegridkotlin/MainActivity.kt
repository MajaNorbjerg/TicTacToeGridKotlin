package org.pondar.tictactoegridkotlin

import android.database.CrossProcessCursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.CircularPropagation
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import org.pondar.tictactoegridkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var turn = 0
    val PLAYER1 = 0
    val PLAYER2 = 1
    var counter = 0
    private var fields = IntArray(9)
    lateinit var binding: ActivityMainBinding
    val EMPTY = 0
    val CROSS = 1
    val CIRCLE = 2
    var computer = ComputerPlayer()


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
        turn = PLAYER1
        Log.d("tag", "This is reset")
        for (i in fields.indices)
            fields[i] = EMPTY
        //findViewById<ImageView>(R.id.field0)
        binding.field0.setImageResource(R.drawable.blank)
        binding.field1.setImageResource(R.drawable.blank)
        binding.field2.setImageResource(R.drawable.blank)
        binding.field3.setImageResource(R.drawable.blank)
        binding.field4.setImageResource(R.drawable.blank)
        binding.field5.setImageResource(R.drawable.blank)
        binding.field6.setImageResource(R.drawable.blank)
        binding.field7.setImageResource(R.drawable.blank)
        binding.field8.setImageResource(R.drawable.blank)
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
                            computer.makeAMove(counter, fields, binding)
                            checkWinner()
                        }
                    } else Toast.makeText(applicationContext,"Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                    }  // if counter is 1..2 ends
                    else {
                        when {
                            fields[fieldIndex] == CROSS -> {
                                // Reset alle Cross billeder til not choosen
                                for ((index, value) in fields.withIndex()){
                                    println("$index, : : : $value")
                                    if(value == CROSS){

                                        // It has to change the imageView and not the number
                                       if(findViewById<ImageView>(getViewId(index)).id == getViewId(index)){
                                           findViewById<ImageView>(getViewId(index)).scaleType= ImageView.ScaleType.FIT_XY
                                       }
                                    }
                                }

                                // Gemme index på den der er klikket
                                // Highlighte den valgte
                                image.scaleType= ImageView.ScaleType.CENTER

                            }
                            fields[fieldIndex] == CIRCLE -> {
                                // Hvis highlighted er valgt
                                Toast.makeText(applicationContext,"You cannot put your pond here, choose an empty field", Toast.LENGTH_SHORT).show()
                               // Ellers
                                Toast.makeText(applicationContext,"Choose one of your own ponds to move", Toast.LENGTH_SHORT).show()
                            }
                            fields[fieldIndex] == EMPTY -> { // If field is empty

                                // Hvis highlighted er valgt
                                // Set Cross her og fjern fra highlighted
                                // Reset highlighted value

                                // ellers toast
                                Toast.makeText(applicationContext,"Choose a pond to move", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }


    } //end of clicklistener

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
            counter = 9
            when (turn) {
                PLAYER1 -> {
                    Toast.makeText(applicationContext, "Player1 is the winner", Toast.LENGTH_SHORT)
                        .show()
                }
                PLAYER2 -> {
                    Toast.makeText(applicationContext, "Player2 is the winner", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(applicationContext, "Its a tie", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            if(turn == PLAYER1) turn = PLAYER2
            else if(turn == PLAYER2) turn = PLAYER1
        }
    }

    private fun getViewId(index: Int) : Int {
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
