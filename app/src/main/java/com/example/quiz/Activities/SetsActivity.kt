package com.example.quiz.Activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.MainActivity

import com.example.quiz.R
import com.example.quiz.adapters.SetAdapter
import com.example.quiz.databinding.ActivitySetsBinding
import com.example.quiz.models.SetModel


class
SetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetsBinding
    private lateinit var list: ArrayList<SetModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa o ViewBinding corretamente
        binding = ActivitySetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        val voltarBtn = findViewById<ImageView>(R.id.btnVoltar)

        voltarBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




        list = arrayListOf(
            SetModel("Nível-1"),
            SetModel("Nível-2"),
            SetModel("Nível-3"),
            SetModel("Nível-4"),
            SetModel("Nível-5"),
            SetModel("Nível-6"),
            SetModel("Nível-7"),
            SetModel("Nível-8"),
            SetModel("Nível-9"),
            SetModel("Nível-10")
        )

        binding.setsRecy.layoutManager = LinearLayoutManager(this)

        val adapter = SetAdapter(this, list)
        binding.setsRecy.adapter = adapter



    }

}
