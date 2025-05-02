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
import com.example.quiz.adapters.SetAdapter2

import com.example.quiz.R
import com.example.quiz.databinding.ActivitySets2Binding
import com.example.quiz.models.SetModel2


class SetsActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySets2Binding
    private lateinit var list: ArrayList<SetModel2>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySets2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        val voltarBtn2 = findViewById<ImageView>(R.id.btnVoltar2)

        voltarBtn2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        list = arrayListOf(
            SetModel2("Nível-1"),
            SetModel2("Nível-2"),
            SetModel2("Nível-3"),
            SetModel2("Nível-4"),
            SetModel2("Nível-5"),
            SetModel2("Nível-6"),
            SetModel2("Nível-7"),
            SetModel2("Nível-8"),
            SetModel2("Nível-9"),
            SetModel2("Nível-10"),



        )
        binding.setsRecy2.layoutManager=LinearLayoutManager(this)

        val adapter2 = SetAdapter2(this,list)
        binding.setsRecy2.adapter=adapter2
    }
}