package com.example.quiz.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.ActivityScore2Binding

class ScoreActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityScore2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScore2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.getRoot())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        val totalScore = intent.getIntExtra("total2",0)
        val correctAnsw = intent.getIntExtra("score2",0)
        val wrong = totalScore-correctAnsw

        binding.totalQuestions2.text=totalScore.toString()
        binding.rightAnsw2.text = correctAnsw.toString()
        binding.wrongAnsw2.text = wrong.toString()

        binding.btnRetry2.setOnClickListener{
            val intent = Intent(this,SetsActivity2::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnQuit2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}