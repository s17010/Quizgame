package jp.ac.itcollege.s17010std.quizgame

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultLabel = findViewById<TextView>(R.id.resultLabel)
        //val totalScoreLabel = findViewById<TextView>(R.id.totalScoreLabel)

        //正解数を取得
        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        //トータルスコアの読み出し
        val prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE)
        var totalScore = prefs.getInt("totalScore", 0)

        //トータルスコアに今回のスコアを加算
        totalScore += score

        //TextViewに表示する
        resultLabel.setText(score.toString() + " / 10")

        /*totalScoreLabel.setText("トータルスコア: $totalScore")

        //トータルスコアを保存
        val editor = prefs.edit()
        editor.putInt("totalScore", totalScore)
         editor.apply()*/
    }

    fun returnTop(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
