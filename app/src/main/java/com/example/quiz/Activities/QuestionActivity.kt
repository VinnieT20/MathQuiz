package com.example.quiz.Activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.R
import com.example.quiz.databinding.ActivityQuestionBinding
import com.example.quiz.models.QuestionModel

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private var list = ArrayList<QuestionModel>()
    private var count = 0
    private var position = 0
    private var score = 0
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        resetTimer();
        timer?.start()

        val setName = intent.getStringExtra("set")
        if (setName == "Nível-1") {
            setOne()
        } else if (setName == "Nível-2") {
            setTwo()
        } else if (setName == "Nível-3") {
            setThree()
        } else if (setName == "Nível-4") {
            setFour()
        } else if (setName == "Nível-5") {
            setFive()
        } else if (setName == "Nível-6") {
            setSix()
        } else if (setName == "Nível-7") {
            setSeven()
        } else if (setName == "Nível-8") {
            setEight()
        } else if (setName == "Nível-9") {
            setNine()
        } else if (setName == "Nível-10") {
            setTen()
        }

        for (i in 0 until binding.optionContainer.childCount) {
            val child = binding.optionContainer.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener { view ->
                    checkAnswer(view as Button)
                }
            }
        }


        playAnimation(binding.question, 0, list[position].question)


        binding.btnNext.setOnClickListener {
            timer?.cancel()
            timer?.start()

            binding.btnNext.isEnabled = false
            binding.btnNext.alpha = 0.3f
            enableOption(true)
            position++
            if (position == list.size) {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", list.size)
                startActivity(intent)
                finish()
                return@setOnClickListener
            }

            count = 0
            playAnimation(binding.question, 0, list[position].question)
        }
    }



    private fun resetTimer() {
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                binding.timer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {

                val dialog = Dialog(this@QuestionActivity)
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.timeout_dialog)


                dialog.findViewById<View>(R.id.tryAgain).setOnClickListener {
                    val intent = Intent(this@QuestionActivity, SetsActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                dialog.show()
            }
        }
        timer?.start()
    }


    private fun playAnimation(view: View, value: Int, data: String) {
        view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat()).setDuration(500).setStartDelay(100)
            .setInterpolator(DecelerateInterpolator()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {


                    if (value == 0 && count < 4) {
                        var option = ""
                        when (count) {
                            0 -> option = list[position].optionA
                            1 -> option = list[position].optionB
                            2 -> option = list[position].optionC
                            3 -> option = list[position].optionD
                        }
                        playAnimation(binding.optionContainer.getChildAt(count), 0, option)
                        count++
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    if (value == 0) {
                        try {
                            (view as TextView).text = data
                            binding.totalQuestion.text = "${position + 1}/${list.size}"
                        } catch (e: Exception) {
                            (view as Button).text = data

                        }
                        view.tag = data

                        playAnimation(view, 1, data)
                    }
                }
            })
    }

    private fun enableOption(enable: Boolean) {
        for (i in 0 until 4) {
            binding.optionContainer.getChildAt(i).isEnabled = enable
            if (enable) {
                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.btn_opt)
            }
        }
    }

    private fun checkAnswer(selectedOption: Button) {

        timer?.cancel()

        binding.btnNext.isEnabled = true
        binding.btnNext.alpha = 1f

        if (selectedOption.text.toString() == list[position].correctAnswer) {
            score++
            selectedOption.setBackgroundResource(R.drawable.right_answ)
        } else {
            selectedOption.setBackgroundResource(R.drawable.wrong_answ)
            val correctOption = binding.optionContainer.findViewWithTag<Button>(list[position].correctAnswer)
            correctOption?.setBackgroundResource(R.drawable.right_answ)
        }
    }



    private fun setTen() {
        list.add(QuestionModel("Qual é o valor de x em x³ = 27?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de y em y³ - 8 = 0?", "2", "3", "4", "5", "2"))
        list.add(QuestionModel("Qual é o valor de z em z³ + 64 = 0?", "-4", "0", "4", "8", "-4"))
        list.add(QuestionModel("Qual é o valor de x em x³ - 6x² + 11x - 6 = 0?", "1", "2", "3", "4", "1"))
        list.add(QuestionModel("Qual é o valor de y em y³ - 9y² + 26y - 24 = 0?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de x em x³ - 12x² + 47x - 60 = 0?", "3", "4", "5", "6", "4"))
        list.add(QuestionModel("Qual é o valor de y em y³ - 15y² + 74y - 120 = 0?", "4", "5", "6", "7", "5"))
        list.add(QuestionModel("Qual é o valor de z em z³ - 18z² + 107z - 210 = 0?", "5", "6", "7", "8", "6"))
        list.add(QuestionModel("Qual é o valor de x em x³ - 21x² + 146x - 336 = 0?", "6", "7", "8", "9", "7"))
        list.add(QuestionModel("Qual é o valor de y em y³ - 24y² + 191y - 504 = 0?", "7", "8", "9", "10", "8"))
    }



    private fun setNine() {
        list.add(QuestionModel("Qual é o valor de x em x² + 5x + 6 = 0?", "-2", "-3", "2", "3", "-2"))
        list.add(QuestionModel("Qual é o valor de y em y² - 7y + 10 = 0?", "2", "5", "7", "10", "5"))
        list.add(QuestionModel("Qual é o valor de z em z² - 9z + 20 = 0?", "4", "5", "6", "7", "5"))
        list.add(QuestionModel("Qual é o valor de x em x² + 8x + 16 = 0?", "-4", "0", "4", "8", "-4"))
        list.add(QuestionModel("Qual é o valor de y em y² - 10y + 25 = 0?", "5", "10", "15", "20", "5"))
        list.add(QuestionModel("Qual é o valor de x em x² - 6x + 9 = 0?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de y em y² + 7y + 12 = 0?", "-3", "-4", "3", "4", "-3"))
        list.add(QuestionModel("Qual é o valor de z em z² - 8z + 15 = 0?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de x em x² + 9x + 20 = 0?", "-4", "-5", "4", "5", "-4"))
        list.add(QuestionModel("Qual é o valor de y em y² - 11y + 30 = 0?", "5", "6", "7", "8", "5"))
    }



    private fun setEight() {
        list.add(QuestionModel("Qual é o valor de x em x² = 16?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Qual é o valor de y em y² - 9 = 0?", "3", "6", "9", "12", "3"))
        list.add(QuestionModel("Qual é o valor de z em z² + 5 = 30?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de x em x² - 4x = 0?", "0", "2", "4", "6", "4"))
        list.add(QuestionModel("Qual é o valor de y em y² + 6y + 9 = 0?", "-3", "0", "3", "6", "-3"))
        list.add(QuestionModel("Qual é o valor de x em x² - 16 = 0?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Qual é o valor de y em y² - 25 = 0?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de z em z² + 8z + 16 = 0?", "-4", "0", "4", "8", "-4"))
        list.add(QuestionModel("Qual é o valor de x em x² - 10x + 25 = 0?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de y em y² - 12y + 36 = 0?", "4", "6", "8", "10", "6"))
    }



    private fun setSeven() {
        list.add(QuestionModel("Se x + y = 10 e x - y = 2, qual é o valor de x?", "4", "5", "6", "7", "6"))
        list.add(QuestionModel("Se 2x + y = 15 e x - y = 3, qual é o valor de y?", "3", "4", "5", "6", "3"))
        list.add(QuestionModel("Se 3x + 2y = 20 e x = 4, qual é o valor de y?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Se x + 2y = 12 e 2x - y = 6, qual é o valor de x?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Se 4x + 3y = 25 e x = 2, qual é o valor de y?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Se 5x + 2y = 24 e y = 3, qual é o valor de x?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Se 3x + 4y = 26 e x = 2, qual é o valor de y?", "4", "5", "6", "7", "5"))
        list.add(QuestionModel("Se 6x + 5y = 32 e y = 4, qual é o valor de x?", "2", "3", "4", "5", "2"))
        list.add(QuestionModel("Se 7x + 3y = 29 e x = 2, qual é o valor de y?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Se 8x + 2y = 34 e y = 5, qual é o valor de x?", "2", "3", "4", "5", "3"))
    }




    private fun setSix() {
        list.add(QuestionModel("Qual é o valor de x em 2(x + 3) = 16?", "2", "4", "5", "7", "5"))
        list.add(QuestionModel("Qual é o valor de y em 3(y - 2) = 12?", "4", "6", "8", "10", "6"))
        list.add(QuestionModel("Qual é o valor de z em 4(z + 1) = 20?", "3", "4", "5", "6", "4"))
        list.add(QuestionModel("Qual é o valor de x em 5(x - 3) = 10?", "2", "4", "5", "7", "5"))
        list.add(QuestionModel("Qual é o valor de y em 2(y + 5) = 24?", "6", "7", "8", "9", "7"))
        list.add(QuestionModel("Qual é o valor de x em 3(x + 4) = 21?", "3", "5", "7", "9", "3"))
        list.add(QuestionModel("Qual é o valor de y em 4(y - 1) = 12?", "2", "3", "4", "5", "4"))
        list.add(QuestionModel("Qual é o valor de z em 5(z + 2) = 25?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de x em 6(x - 3) = 18?", "4", "5", "6", "7", "6"))
        list.add(QuestionModel("Qual é o valor de y em 7(y + 1) = 28?", "3", "4", "5", "6", "3"))
    }




    private fun setFive() {
        list.add(QuestionModel("Qual é o valor de x em x/2 + 3 = 7?", "4", "6", "8", "10", "8"))
        list.add(QuestionModel("Qual é o valor de y em 2y/3 = 6?", "6", "9", "12", "15", "9"))
        list.add(QuestionModel("Qual é o valor de z em 0.5z + 2 = 6?", "4", "6", "8", "10", "8"))
        list.add(QuestionModel("Qual é o valor de x em 1.5x - 3 = 6?", "4", "6", "8", "10", "6"))
        list.add(QuestionModel("Qual é o valor de y em y/4 + 1 = 3?", "4", "6", "8", "10", "8"))
        list.add(QuestionModel("Qual é o valor de x em x/3 + 2 = 5?", "3", "6", "9", "12", "9"))
        list.add(QuestionModel("Qual é o valor de y em 0.25y + 1 = 2?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Qual é o valor de z em 1.2z - 0.6 = 1.8?", "1", "2", "3", "4", "2"))
        list.add(QuestionModel("Qual é o valor de x em x/5 + 3 = 7?", "10", "15", "20", "25", "20"))
        list.add(QuestionModel("Qual é o valor de y em 0.75y - 1.5 = 3?", "4", "6", "8", "10", "6"))
    }



    private fun setFour() {
        list.add(QuestionModel("Qual é o valor de x em 2x + 3x = 25?", "2", "5", "7", "10", "5"))
        list.add(QuestionModel("Qual é o valor de y em 4y - 2y = 16?", "4", "6", "8", "10", "8"))
        list.add(QuestionModel("Qual é o valor de z em 3z + 5 = 2z + 10?", "2", "5", "7", "10", "5"))
        list.add(QuestionModel("Qual é o valor de x em 6x - 4 = 4x + 8?", "2", "4", "6", "8", "6"))
        list.add(QuestionModel("Qual é o valor de y em 5y + 10 = 3y + 20?", "5", "10", "15", "20", "5"))
        list.add(QuestionModel("Qual é o valor de x em 7x - 3 = 4x + 9?", "2", "3", "4", "5", "4"))
        list.add(QuestionModel("Qual é o valor de y em 8y - 5 = 3y + 20?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de z em 9z - 6 = 6z + 12?", "2", "4", "6", "8", "6"))
        list.add(QuestionModel("Qual é o valor de x em 10x - 7 = 5x + 18?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de y em 11y - 8 = 7y + 16?", "4", "6", "8", "10", "6"))
    }


    private fun setThree() {
        list.add(QuestionModel("Qual é o valor de x em 4x - 5 = 11?", "2", "4", "5", "6", "4"))
        list.add(QuestionModel("Qual é o valor de y em 2y + 8 = 24?", "6", "8", "10", "12", "8"))
        list.add(QuestionModel("Qual é o valor de z em z/3 + 4 = 10?", "6", "12", "18", "24", "18"))
        list.add(QuestionModel("Qual é o valor de x em 5x + 10 = 35?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de y em 3y - 6 = 12?", "4", "6", "8", "10", "6"))
        list.add(QuestionModel("Qual é o valor de x em 6x - 4 = 14?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de y em y/2 + 5 = 10?", "5", "10", "15", "20", "10"))
        list.add(QuestionModel("Qual é o valor de z em 7z - 14 = 21?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de x em 8x + 16 = 40?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel("Qual é o valor de y em 9y - 18 = 27?", "3", "5", "7", "9", "5"))
    }

    private fun setTwo() {
        list.add(QuestionModel("Qual é o valor de x em 2x + 3 = 11?", "2", "4", "5", "7", "4"))
        list.add(QuestionModel("Qual é o valor de y em y/4 = 3?", "3", "6", "12", "15", "12"))
        list.add(QuestionModel("Qual é o valor de z em 5z - 10 = 15?", "3", "5", "7", "10", "5"))
        list.add(QuestionModel("Qual é o valor de x em 3x + 2 = 14?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Qual é o valor de y em y + 7 = 21?", "7", "14", "21", "28", "14"))
        list.add(QuestionModel("Qual é o valor de x em 4x - 5 = 11?", "2", "4", "5", "6", "4"))
        list.add(QuestionModel("Qual é o valor de y em 2y + 8 = 24?", "6", "8", "10", "12", "8"))
        list.add(QuestionModel("Qual é o valor de z em z/3 + 4 = 10?", "6", "12", "18", "24", "18"))
        list.add(QuestionModel("Qual é o valor de x em 5x + 10 = 35?", "3", "5", "7", "9", "5"))
        list.add(QuestionModel("Qual é o valor de y em 3y - 6 = 12?", "4", "6", "8", "10", "6"))
    }

    private fun setOne() {
        list = arrayListOf()
        list.add(QuestionModel("Qual é o valor de x em x + 5 = 10?", "2", "5", "10", "15", "5"))
        list.add(QuestionModel("Qual é o valor de y em y - 3 = 7?", "4", "7", "10", "14", "10"))
        list.add(QuestionModel("Qual é o valor de z em 2z = 8?", "2", "4", "6", "8", "4"))
        list.add(QuestionModel("Qual é o valor de x em x / 2 = 5?", "2", "5", "10", "20", "10"))
        list.add(QuestionModel("Qual é o valor de y em 3y = 15?", "3", "5", "10", "15", "5"))
        list.add(QuestionModel("Qual é o valor de x em x + 7 = 12?", "3", "5", "7", "12", "5"))
        list.add(QuestionModel("Qual é o valor de y em y - 4 = 8?", "4", "8", "12", "16", "12"))
        list.add(QuestionModel("Qual é o valor de z em 4z = 20?", "4", "5", "10", "20", "5"))
        list.add(QuestionModel("Qual é o valor de x em x / 3 = 4?", "3", "4", "12", "16", "12"))
        list.add(QuestionModel("Qual é o valor de y em 5y = 25?", "3", "5", "10", "25", "5"))
    }
}