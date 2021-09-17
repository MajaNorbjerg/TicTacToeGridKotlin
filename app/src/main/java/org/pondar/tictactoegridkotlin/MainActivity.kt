package org.pondar.tictactoegridkotlin

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
    val PALYER2 = 1
    var counter = 0
    private var fields = IntArray(9)
    lateinit var binding : ActivityMainBinding
    val EMPTY = 0
    val CROSS = 1
    val CIRCLE = 2



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
        binding.newGameButton.setOnClickListener{reset()
            }
    }

    private fun reset(){
        counter = 0
        turn = PLAYER1
        Log.d("tag", "This is reset")
        for(i in fields.indices)
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

        if(counter in 0..5) {

            if (view?.id == R.id.field0) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[0] == EMPTY) {
                    counter++
                    //An example of how to set the image is shown below
                    //you of course need to check if the field is empty
                    //before setting a new image
                    //and also if the turn is X or O
                    if (turn == PLAYER1) {
                        fields[0] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = PALYER2
                    } else {
                        fields[0] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = PLAYER1
                    }
                }else {Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()}
                //then you need to update your field int[] array also to save the state
            }



            if (view?.id == R.id.field1) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[1] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[1] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[1] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else {Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()}
                //then you need to update your field int[] array also to save the state
            }

            if (view?.id == R.id.field2) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[2] == EMPTY) {
                    counter++
                    if (turn == PLAYER1) {
                        fields[2] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[2] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }


            if (view?.id == R.id.field3) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[3] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[3] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[3] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }

            if (view?.id == R.id.field4) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[4] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[4] = CROSS
                        image.setImageResource(R.drawable.kryds)


                        turn = 1
                    }
                                        else {
                        fields[4] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }
            if (view?.id == R.id.field5) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[5] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[5] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[5] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }

            if (view?.id == R.id.field6) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[6] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[6] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[6] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }

            if (view?.id == R.id.field7) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[7] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[7] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[7] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state
            }

            if (view?.id == R.id.field8) {
                val image = view as ImageView

                Log.d("Field_Clicked", "field 0 pressed")

                //TODO something here
                if (fields[8] == EMPTY) {
                    counter++
                    if (turn == 0) {
                        fields[8] = CROSS
                        image.setImageResource(R.drawable.kryds)
                        turn = 1
                    } else {
                        fields[8] = CIRCLE
                        image.setImageResource(R.drawable.bolle)
                        turn = 0
                    }
                }else Toast.makeText(applicationContext, "Theres already a tic or a toe here, choose an empty field", Toast.LENGTH_SHORT).show()
                //then you need to update your field int[] array also to save the state


            }


        }else{

            Log.d("Game over", "Gameover and maybe something won")
        }
        checkWinner()
    } //end of clicklistener

    private fun checkWinner(){
if(fields[0] != EMPTY && fields[0] == fields[1] && fields[0] == fields[2] ||
    fields[3] != EMPTY && fields[3] == fields[4] && fields[3] == fields[5] ||
    fields[6] != EMPTY && fields[6] == fields[7] && fields[6] == fields[8] ||
    fields[0] != EMPTY && fields[0] == fields[3] && fields[0] == fields[6] ||
    fields[1] != EMPTY && fields[1] == fields[4] && fields[1] == fields[7] ||
    fields[2] != EMPTY && fields[2] == fields[5] && fields[2] == fields[8] ||
    fields[0] != EMPTY && fields[0] == fields[4] && fields[0] == fields[8] ||
    fields[2] != EMPTY && fields[2] == fields[4] && fields[2] == fields[6]
){
    // empty 0, cross 1, circle 2
        counter = 9
    if(turn == PLAYER1)
            Toast.makeText(applicationContext, "Player2 is the winner", Toast.LENGTH_SHORT).show()
else
        Toast.makeText(applicationContext, "Player1 is the winner", Toast.LENGTH_SHORT).show()
    }

    }
}
