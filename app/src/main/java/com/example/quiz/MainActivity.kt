package com.example.quiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.Activities.SetsActivity
import com.example.quiz.Activities.SetsActivity2
import com.example.quiz.Activities.SetsActivity3


class MainActivity : AppCompatActivity() {
    private lateinit var algebra:CardView
    private lateinit var aritmetica:CardView
    private lateinit var geometria:CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        algebra = findViewById(R.id.algebra);
        aritmetica = findViewById(R.id.aritmetica);
        geometria = findViewById(R.id.geometria)


        algebra.setOnClickListener{
            val intent = Intent(this,SetsActivity::class.java)
            startActivity(intent)
        }


        aritmetica.setOnClickListener{
            val intent = Intent(this,SetsActivity2::class.java)
            startActivity(intent)
        }

        geometria.setOnClickListener{
            val intent =Intent(this,SetsActivity3::class.java)
            startActivity(intent)
        }

    }
}