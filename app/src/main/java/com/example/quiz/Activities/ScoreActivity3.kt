package com.example.quiz.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.databinding.ActivityScore3Binding

class ScoreActivity3 : AppCompatActivity() {
    private lateinit var binding:ActivityScore3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScore3Binding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.getRoot())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()


        val totalScore = intent.getIntExtra("total3",0)
        val correctAnsw = intent.getIntExtra("score3",0)
        val wrong = totalScore-correctAnsw

        binding.totalQuestion3.text=totalScore.toString()
        binding.rightAnsw3.text = correctAnsw.toString()
        binding.wrongAnsw3.text = wrong.toString()

        binding.btnRetry3.setOnClickListener{
            val intent = Intent(this,SetsActivity2::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnQuit3.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}