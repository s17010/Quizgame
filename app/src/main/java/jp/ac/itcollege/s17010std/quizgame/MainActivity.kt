package jp.ac.itcollege.s17010std.quizgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        button.setOnClickListener() {
            val intent = Intent(applicationContext,RandomNormal::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener() {
            val intent = Intent(applicationContext,RandomHeard::class.java)
            startActivity(intent)
        }
    }
}
