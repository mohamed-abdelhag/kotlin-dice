package com.dexster.hbda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rollButton: Button
    private lateinit var diceImage1: ImageView
    private lateinit var diceImage2: ImageView
    private lateinit var userNumber: EditText
    private lateinit var resultText: TextView
    private lateinit var deQuestion: TextView
    private lateinit var youWon: TextView
    private lateinit var youLose: TextView

    private var diceRoll1 = 0
    private var diceRoll2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.button)
        diceImage1 = findViewById(R.id.imageView)
        diceImage2 = findViewById(R.id.imageView2)
        userNumber = findViewById(R.id.editTextText2)
        resultText = findViewById(R.id.textViewR)
        deQuestion = findViewById(R.id.textViewQ)
        youWon = findViewById(R.id.textViewW)
        youLose = findViewById(R.id.textViewL)

        diceImage1.setImageResource(R.drawable.dice_5)
        diceImage2.setImageResource(R.drawable.dice_3)

        rollButton.setOnClickListener {
            diceRoll1 = rollDice()
            diceRoll2 = rollDice2()
            val totalRoll = getResult(diceRoll1, diceRoll2)
            val userNumberText = userNumber.text.toString()

            resultText.text = "You Got a $totalRoll !!"
            deQuestion.text = ""

            if (userNumberText.isNotBlank()) {
                try {
                    val userNumber = userNumberText.toInt()
                    if (userNumber == totalRoll) {
                        youWon.text = "Hooly Molly fuck pnna stick you won"
                        youLose.text = ""
                    } else {
                        youWon.text = ""
                        youLose.text = "Womp Womp you lost try again"
                    }
                } catch (e: NumberFormatException) {
                    // Handle invalid input
                }
            }
        }
    }

    fun rollDice(): Int {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage1.setImageResource(drawableResource)
        return diceRoll
    }

    fun rollDice2(): Int {
        val dice = Dice(6)
        val diceRoll2 = dice.roll()
        val drawableResource = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage2.setImageResource(drawableResource)
        return diceRoll2
    }

    fun getResult(diceRoll1: Int, diceRoll2: Int): Int {
        return diceRoll1 + diceRoll2
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
