package com.don666.demo

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    private var coins = 10000

    private lateinit var coinText: TextView
    private lateinit var slotText: TextView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(30, 40, 30, 40)
        root.setBackgroundColor(Color.rgb(20, 20, 20))

        val title = TextView(this)
        title.text = "DON666"
        title.textSize = 34f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER

        coinText = TextView(this)
        coinText.text = "🪙 Demo Coins: $coins"
        coinText.textSize = 20f
        coinText.setTextColor(Color.WHITE)
        coinText.gravity = Gravity.CENTER

        slotText = TextView(this)
        slotText.text = "🍒   🍋   🔔"
        slotText.textSize = 42f
        slotText.setTextColor(Color.WHITE)
        slotText.gravity = Gravity.CENTER
        slotText.setPadding(0, 50, 0, 50)

        resultText = TextView(this)
        resultText.text = "Press SPIN to play"
        resultText.textSize = 18f
        resultText.setTextColor(Color.WHITE)
        resultText.gravity = Gravity.CENTER

        val spinButton = Button(this)
        spinButton.text = "🎰 SPIN"
        spinButton.setOnClickListener {
            spin()
        }

        val resetButton = Button(this)
        resetButton.text = "RESET COINS"
        resetButton.setOnClickListener {
            coins = 10000
            updateCoins()
            resultText.text = "Demo balance reset!"
        }

        root.addView(title)
        root.addView(coinText)
        root.addView(slotText)
        root.addView(resultText)
        root.addView(spinButton)
        root.addView(resetButton)

        setContentView(root)
    }

    private fun spin() {

        val bet = 100

        if (coins < bet) {
            resultText.text = "Not enough demo coins!"
            return
        }

        coins -= bet

        val symbols = listOf(
            "🍒", "🍋", "🔔",
            "⭐", "💎", "7️⃣"
        )

        val a = symbols[Random.nextInt(symbols.size)]
        val b = symbols[Random.nextInt(symbols.size)]
        val c = symbols[Random.nextInt(symbols.size)]

        slotText.text = "$a   $b   $c"

        when {
            a == b && b == c -> {
                val reward = bet * 10
                coins += reward
                resultText.text = "🎉 JACKPOT! +$reward coins"
            }

            a == b || b == c || a == c -> {
                val reward = bet * 2
                coins += reward
                resultText.text = "✨ WIN! +$reward coins"
            }

            else -> {
                resultText.text = "Try again!"
            }
        }

        updateCoins()
    }

    private fun updateCoins() {
        coinText.text = "🪙 Demo Coins: $coins"
    }
}
