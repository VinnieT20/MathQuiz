package com.example.quiz.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.getRoot())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()


        val totalScore = intent.getIntExtra("total",0)
        val correctAnsw = intent.getIntExtra("score",0)
        val wrong = totalScore-correctAnsw

        binding.totalQuestions.text=totalScore.toString()
        binding.rightAnsw.text=correctAnsw.toString()
        binding.wrongAnsw.text=wrong.toString()

        binding.btnRetry.setOnClickListener{
            val intent=Intent(this,SetsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnQuit.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}