package org.pondar.tictactoegridkotlin

import android.database.CrossProcessCursor
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import org.pondar.tictactoegridkotlin.databinding.ActivityMainBinding
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class ComputerPlayer {

    val EMPTY = 0
    val CROSS = 1
    val CIRCLE = 2
    var computerMove = false
    lateinit var fieldsCopy : IntArray

    fun makeAMove(counter: Int, fields: IntArray, binding: ActivityMainBinding){
        println("Computermove:  " + computerMove)
        if(counter == 1) {
            //if(!computerMove) cleverPlacement(fields, binding)
            if (!computerMove) placeInCorner(fields, binding)
        }
        if(counter == 2) {

            if (!computerMove) block(fields, binding)
            if(!computerMove) cleverPlacement(fields, binding)
            if (!computerMove) placeInSide(fields, binding)
          //  if (!computerMove) placeInCorner(fields, binding)
        }
        if(counter == 3) {

            if (!computerMove) win(fields, binding)
            if (!computerMove) block(fields, binding)
            if(!computerMove) cleverPlacement(fields, binding)
        }
    }

    private fun cleverPlacement(fields: IntArray, binding: ActivityMainBinding){
        var listOfBlanks = mutableListOf<Int>()
        for (i in 0..8) {
            if (fields[i] == 0) listOfBlanks.add(i)
        }
        println(listOfBlanks.size)

        var listOfPoints = mutableListOf<Int>()

        for (i in 0 until listOfBlanks.size){
        listOfPoints.add(0)
        }
        println("The real fields $fields")
        for (field in fields) {
            println("The real fields $field")
        }

        fieldsCopy = fields
        for (field in fieldsCopy) {
            println("The copy of fields $field")
        }

        for (blankIndex in 0 until listOfBlanks.size){
//            println("LISST OF BLANKS: ${fieldsCopy[listOfBlanks[blankIndex]]}")
//            println("Blankindexes: $blankIndex")




            fieldsCopy[listOfBlanks[blankIndex]] = CIRCLE
            for (field in fieldsCopy) {
                println("The copy of fields inside for $field")
            }

           // println("LISST OF NEW BLANKS: ${fieldsCopy[listOfBlanks[blankIndex]]}")
           for (i in 1..8){
                var combinationIndexes = combinationIndexConverter(i)
                var valueList = listOf<Int>(fieldsCopy[combinationIndexes[0]],fieldsCopy[combinationIndexes[1]],fieldsCopy[combinationIndexes[2]])

              // println("For each combination: ${fieldsCopy[blankIndex]}")
                if(valueList.count{it == CIRCLE} == 2 && valueList.count{it == EMPTY} == 1){
                    listOfPoints[blankIndex] =  listOfPoints[blankIndex]+1
                    // HER SKAL DEN KIGGE EFTER DET NÆSTE TRÆK, MEN DET GIDER DEN IKKE RIGTIG
                    for(j in 0..2){
                        if(valueList[j] == 0){
                            var indexOfEmptyField =  combinationIndexes[j]
                            // println("-j the empty field: ${indexOfEmptyField}")
                            fieldsCopy[indexOfEmptyField] = CROSS
                            for(k in 1..8){
                                var nextMoveCombinationIndexes = combinationIndexConverter(k)
                                var potentialValueList = listOf<Int>(fieldsCopy[nextMoveCombinationIndexes[0]],fieldsCopy[nextMoveCombinationIndexes[1]],fieldsCopy[nextMoveCombinationIndexes[2]])
                                println("Potential valuelist $potentialValueList")
                                if(potentialValueList.count{it == CROSS} == 2 && potentialValueList.count{it == EMPTY} == 1){
                                    listOfPoints[blankIndex] =  listOfPoints[blankIndex]-1
                                }
                            }
                            fieldsCopy[indexOfEmptyField] = EMPTY
                        }
                    }
                }
            }

            fieldsCopy[listOfBlanks[blankIndex]] = EMPTY

        }
        for (field in fields) {
            println("The real fields after $field")
        }

        for (field in fieldsCopy) {
            println("The copy of fields after $field")
        }

       println("listOfPoints2: $listOfPoints")


        val maxIdx = listOfPoints.indices.maxByOrNull{ listOfPoints[it] } ?: -1
        println("max index: $maxIdx")

        setThatPond(listOfBlanks[maxIdx], fields, binding)

    // setThatPond(randomNumber, fields, binding)
    }

    private fun placeInCorner(fields: IntArray, binding: ActivityMainBinding) {
        var list = mutableListOf<Int>()

        if(fields[4] == 0){
            setThatPond(4, fields, binding)
        }else {
            if (fields[0] == 0) list.add(0)
            if (fields[2] == 0) list.add(2)
            if (fields[6] == 0) list.add(6)
            if (fields[8] == 0) list.add(8)
            if (list.size > 0) {
                val randomIndex = Random.nextInt(list.size)
                var randomNumber = list[randomIndex]
                setThatPond(randomNumber, fields, binding)
            }
        }
    }

    private fun placeInSide(fields: IntArray, binding: ActivityMainBinding) {
        var list = mutableListOf<Int>()

            if (fields[1] == 0) list.add(1)
            if (fields[3] == 0) list.add(3)
            if (fields[5] == 0) list.add(5)
            if (fields[7] == 0) list.add(7)
            if (list.size > 0) {
                val randomIndex = Random.nextInt(list.size)
                var randomNumber = list[randomIndex]
                setThatPond(randomNumber, fields, binding)
        }
    }
    private fun win(fields: IntArray, binding: ActivityMainBinding){
       checkForTwoOnALine(fields, binding, CIRCLE)
    }
    private fun block(fields: IntArray, binding: ActivityMainBinding){
        checkForTwoOnALine(fields, binding, CROSS)
    }

    private fun checkForTwoOnALine(fields: IntArray, binding: ActivityMainBinding, ticOrToe: Int){
       combinationLoop@ for (i in 1..8){
            var combinationIndexes = combinationIndexConverter(i)
            var valueList = listOf<Int>(fields[combinationIndexes[0]],fields[combinationIndexes[1]],fields[combinationIndexes[2]])

            if(valueList.count{it == ticOrToe} == 2 && valueList.count{it == EMPTY} == 1){
                for(j in 0..2){
                    if(valueList[j] == 0){
                        var indexOfEmptyField =  combinationIndexes[j]
                        setThatPond(indexOfEmptyField, fields, binding)
                        break@combinationLoop
                    }
                }
            }
        }
    }
    private fun setThatPond(index: Int, fields: IntArray, binding: ActivityMainBinding){
        fields[index] = 2 // Set data as player 2
        convertIndex(index, binding).setImageResource(R.drawable.bolle)
        computerMove = true
    }

    private fun combinationIndexConverter(number: Int): List<Int> {
        return when (number) {
            1 -> listOf<Int>(0,1,2)
            2 -> listOf<Int>(3,4,5)
            3 ->  listOf<Int>(6,7,8)

            4 -> listOf<Int>(0,3,6)
            5 -> listOf<Int>(1,4,7)
            6 ->  listOf<Int>(2,5,8)

            7 -> listOf<Int>(0,4,8)
            8 ->  listOf<Int>(2,4,6)

            else -> listOf<Int>()
        }
    }
    private fun convertIndex(index: Int, binding: ActivityMainBinding) : ImageView {
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