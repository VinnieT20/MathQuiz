package com.example.quiz.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.quiz.MainActivity
import com.example.quiz.models.SetModel3
import com.example.quiz.R
import com.example.quiz.adapters.SetAdapter3
import com.example.quiz.databinding.ActivitySets3Binding




class SetsActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivitySets3Binding
    private lateinit var list:ArrayList<SetModel3>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySets3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        val voltarBtn3 = findViewById<ImageView>(R.id.btnVoltar3)

        voltarBtn3.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        list = arrayListOf(
            SetModel3("Nível-1"),
            SetModel3("Nível-2"),
            SetModel3("Nível-3"),
            SetModel3("Nível-4"),
            SetModel3("Nível-5"),
            SetModel3("Nível-6"),
            SetModel3("Nível-7"),
            SetModel3("Nível-8"),
            SetModel3("Nível-9"),
            SetModel3("Nível-10"),
        )

        binding.setsRecy3.layoutManager=LinearLayoutManager(this)

        val adapter3 = SetAdapter3(this,list)
        binding.setsRecy3.adapter=adapter3
    }
}