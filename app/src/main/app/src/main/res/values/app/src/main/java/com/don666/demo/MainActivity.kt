package com.don666.demo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : Activity() {

    private var balance = 10000
    private var bet = 100

    private lateinit var balanceText: TextView
    private lateinit var resultText: TextView
    private lateinit var slotText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(30, 40, 30, 40)
            setBackgroundColor(Color.rgb(18, 18, 18))
        }

        val title = TextView(this).apply {
            text = "DON666"
            textSize = 32f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        balanceText = TextView(this).apply {
            text = "🪙 Demo Coins: $balance"
            textSize = 20f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        slotText = TextView(this).apply {
            text = "🍒   🍋   🔔"
            textSize = 42f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 50, 0, 50)
        }

        resultText = TextView(this).apply {
            text = "Press SPIN to play"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val spinButton = Button(this).apply {
            text = "🎰 SPIN"
            textSize = 20f
            setOnClickListener {
                spin()
            }
        }

        val resetButton = Button(this).apply {
            text = "RESET DEMO COINS"
            setOnClickListener {
                balance = 10000
                updateBalance()
                resultText.text = "Balance reset!"
            }
        }

        root.addView(title)
        root.addView(balanceText)
        root.addView(slotText)
        root.addView(resultText)

        root.addView(
            spinButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        root.addView(
            resetButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        setContentView(root)
    }

    private fun spin() {

        if (balance < bet) {
            Toast.makeText(
                this,
                "Not enough demo coins!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        balance -= bet

        val symbols = listOf("🍒", "🍋", "🔔", "⭐", "💎", "7️⃣")

        val a = symbols.random()
        val b = symbols.random()
        val c = symbols.random()

        slotText.text = "$a   $b   $c"

        when {
            a == b && b == c -> {
                val reward = bet * 10
                balance += reward
                resultText.text = "🎉 JACKPOT! +$reward coins"
            }

            a == b || b == c || a == c -> {
                val reward = bet * 2
                balance += reward
                resultText.text = "✨ WIN! +$reward coins"
            }

            else -> {
                resultText.text = "Try again!"
            }
        }

        updateBalance()
    }

    private fun updateBalance() {
        balanceText.text = "🪙 Demo Coins: $balance"
    }
}
