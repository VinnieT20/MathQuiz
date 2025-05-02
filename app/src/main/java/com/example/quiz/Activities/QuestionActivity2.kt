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
import com.example.quiz.models.QuestionModel2
import com.example.quiz.R
import com.example.quiz.databinding.ActivityQuestion2Binding

class QuestionActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivityQuestion2Binding
    private var list = ArrayList<QuestionModel2>()
    private var count = 0
    private var position = 0
    private var score = 0
    private var timer: CountDownTimer? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityQuestion2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        resetTimer();
        timer?.start()




        val setName2 = intent.getStringExtra("set2")
        if (setName2 == "Nível-1") {
            setOne()
        } else if (setName2 == "Nível-2") {
            setTwo()
        } else if (setName2 == "Nível-3") {
            setThree()
        } else if (setName2 == "Nível-4") {
            setFour()
        } else if (setName2 == "Nível-5") {
            setFive()
        } else if (setName2 == "Nível-6") {
            setSix()
        } else if (setName2 == "Nível-7") {
            setSeven()
        } else if (setName2 == "Nível-8") {
            setEight()
        } else if (setName2 == "Nível-9") {
            setNine()
        } else if (setName2 == "Nível-10") {
            setTen()
        }

        for (i in 0 until binding.optionContainer2.childCount) {
            val child = binding.optionContainer2.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener { view ->
                    checkAnswer(view as Button)
                }
            }
        }

        playAnimation(binding.question2,0,list[position].question2)
        
        
        binding.btnNext2.setOnClickListener{
            timer?.cancel()
            timer?.start()

            
            binding.btnNext2.isEnabled=false
            binding.btnNext2.alpha=0.3f
            enableOption(true)
            position++
            if (position==list.size){
                val intent = Intent(this,ScoreActivity2::class.java)
                intent.putExtra("score2",score)
                intent.putExtra("total2",list.size)
                startActivity(intent)
                finish()
                return@setOnClickListener

            }
            count = 0
            playAnimation(binding.question2,0,list[position].question2)
            
        }
        
    }

    private fun resetTimer(){
        timer= object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timer2.text=(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                val dialog = Dialog(this@QuestionActivity2)
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.timeout_dialog)

                dialog.findViewById<View>(R.id.tryAgain).setOnClickListener{
                    val intent = Intent(this@QuestionActivity2,SetsActivity2::class.java)
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
            .setInterpolator(DecelerateInterpolator()).setListener(object :AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator) {
                    if (value==0&&count<4){
                        var option=""
                        when(count){
                            0->option=list[position].optionA2
                            1->option=list[position].optionB2
                            2->option=list[position].optionC2
                            3->option=list[position].optionD2
                        }
                        playAnimation(binding.optionContainer2.getChildAt(count),0,option)
                        count++
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    if (value==0){
                        try {
                            (view as TextView).text=data
                            binding.totalQuestion2.text="${position+1}/${list.size}"
                        }catch (e:Exception){
                            (view as Button).text=data
                        }
                        view.tag=data
                        playAnimation(view,1,data)
                    }
                }
            })

    }

    private fun enableOption(enable: Boolean) {
        for (i in 0 until 4){
            binding.optionContainer2.getChildAt(i).isEnabled=enable
            if (enable){
                binding.optionContainer2.getChildAt(i).setBackgroundResource(R.drawable.btn_opt)
            }
        }
    }






    private fun checkAnswer(selectedOption: Button) {
        timer?.cancel()

        binding.btnNext2.isEnabled=true
        binding.btnNext2.alpha=1f

        if(selectedOption.text.toString()==list[position].correctAnswer2){
            score++
            selectedOption.setBackgroundResource(R.drawable.right_answ)
        }else{
            selectedOption.setBackgroundResource(R.drawable.wrong_answ)
            val correctOption = binding.optionContainer2.findViewWithTag<Button>(list[position].correctAnswer2)
            correctOption?.setBackgroundResource(R.drawable.right_answ)
        }

    }

    private fun setTen() {
        list = arrayListOf()
        list.add(QuestionModel2("Qual é 40% de 750?", "280", "290", "300", "310", "300"))
        list.add(QuestionModel2("Quanto é 7⁵?", "16000", "16807", "17000", "17500", "16807"))
        list.add(QuestionModel2("Quanto é a raiz cúbica de 512?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel2("Quanto é 1000 ÷ 25?", "30", "35", "40", "45", "40"))
        list.add(QuestionModel2("Quanto é 54 x 23?", "1200", "1242", "1300", "1350", "1242"))
        list.add(QuestionModel2("Quanto é 20³?", "7800", "8000", "8200", "8400", "8000"))
        list.add(QuestionModel2("Quanto é 60% de 1800?", "1050", "1080", "1100", "1150", "1080"))
        list.add(QuestionModel2("Quanto é 625 ÷ 25?", "23", "24", "25", "26", "25"))
        list.add(QuestionModel2("Quanto é 33 x 19?", "620", "627", "637", "645", "627"))
        list.add(QuestionModel2("Quanto é 500 ÷ 20?", "22", "24", "25", "26", "25"))
    }


    private fun setNine() {
        list = arrayListOf()
        list.add(QuestionModel2("Qual é 30% de 600?", "160", "170", "180", "190", "180"))
        list.add(QuestionModel2("Quanto é 6⁴?", "1200", "1250", "1296", "1400", "1296"))
        list.add(QuestionModel2("Quanto é a raiz quadrada de 256?", "14", "15", "16", "17", "16"))
        list.add(QuestionModel2("Quanto é 980 ÷ 14?", "68", "69", "70", "71", "70"))
        list.add(QuestionModel2("Quanto é 44 x 16?", "680", "700", "704", "720", "704"))
        list.add(QuestionModel2("Quanto é 15³?", "3200", "3375", "3500", "3600", "3375"))
        list.add(QuestionModel2("Quanto é 45% de 1200?", "520", "530", "540", "550", "540"))
        list.add(QuestionModel2("Quanto é 256 ÷ 8?", "30", "31", "32", "33", "32"))
        list.add(QuestionModel2("Quanto é 29 x 21?", "598", "599", "609", "619", "609"))
        list.add(QuestionModel2("Quanto é 400 ÷ 25?", "12", "13", "14", "16", "16"))
    }


    private fun setEight() {
        list = arrayListOf()
        list.add(QuestionModel2("Qual é 20% de 450?", "75", "80", "85", "90", "90"))
        list.add(QuestionModel2("Quanto é 2⁵?", "30", "31", "32", "33", "32"))
        list.add(QuestionModel2("Quanto é a raiz cúbica de 125?", "3", "4", "5", "6", "5"))
        list.add(QuestionModel2("Quanto é 980 ÷ 20?", "45", "48", "49", "50", "49"))
        list.add(QuestionModel2("Quanto é 33 x 12?", "380", "390", "396", "400", "396"))
        list.add(QuestionModel2("Quanto é 10³?", "800", "900", "1000", "1100", "1000"))
        list.add(QuestionModel2("Quanto é 25% de 800?", "180", "190", "200", "210", "200"))
        list.add(QuestionModel2("Quanto é 144 ÷ 9?", "14", "15", "16", "18", "16"))
        list.add(QuestionModel2("Quanto é 19 x 17?", "315", "320", "323", "330", "323"))
        list.add(QuestionModel2("Quanto é 225 ÷ 15?", "13", "14", "15", "16", "15"))
    }


    private fun setSeven() {
        list = arrayListOf()
        list.add(QuestionModel2("Qual é 15% de 200?", "25", "30", "35", "40", "30"))
        list.add(QuestionModel2("Quanto é 3⁴?", "72", "81", "96", "100", "81"))
        list.add(QuestionModel2("Quanto é 125 ÷ 5?", "20", "22", "24", "25", "25"))
        list.add(QuestionModel2("Quanto é a raiz quadrada de 121?", "9", "10", "11", "12", "11"))
        list.add(QuestionModel2("Quanto é 500 ÷ 4?", "120", "125", "130", "135", "125"))
        list.add(QuestionModel2("Quanto é 22 x 14?", "280", "300", "308", "320", "308"))
        list.add(QuestionModel2("Quanto é 7³?", "320", "330", "343", "350", "343"))
        list.add(QuestionModel2("Quanto é 84 ÷ 7?", "10", "11", "12", "13", "12"))
        list.add(QuestionModel2("Quanto é 23 x 19?", "420", "430", "437", "445", "437"))
        list.add(QuestionModel2("Quanto é 144 ÷ 16?", "6", "7", "8", "9", "9"))
    }


    private fun setSix() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 13²?", "149", "150", "169", "170", "169"))
        list.add(QuestionModel2("Quanto é 144 ÷ 6?", "22", "24", "26", "28", "24"))
        list.add(QuestionModel2("Quanto é 18 x 7?", "120", "126", "132", "140", "126"))
        list.add(QuestionModel2("Quanto é 400 ÷ 8?", "45", "48", "50", "52", "50"))
        list.add(QuestionModel2("Quanto é 5³?", "100", "115", "125", "135", "125"))
        list.add(QuestionModel2("Quanto é 21 x 11?", "221", "231", "241", "251", "231"))
        list.add(QuestionModel2("Quanto é 900 ÷ 15?", "55", "58", "60", "62", "60"))
        list.add(QuestionModel2("Quanto é 16³?", "4000", "4096", "4200", "4300", "4096"))
        list.add(QuestionModel2("Quanto é 625 ÷ 25?", "22", "24", "25", "26", "25"))
        list.add(QuestionModel2("Quanto é 19 x 12?", "222", "224", "228", "232", "228"))
    }


    private fun setFive() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 3³?", "18", "24", "27", "30", "27"))
        list.add(QuestionModel2("Quanto é 256 ÷ 16?", "12", "14", "16", "18", "16"))
        list.add(QuestionModel2("Quanto é 25 x 4?", "85", "90", "100", "110", "100"))
        list.add(QuestionModel2("Quanto é 100 ÷ 5?", "18", "19", "20", "21", "20"))
        list.add(QuestionModel2("Quanto é 45 x 3?", "130", "135", "140", "145", "135"))
        list.add(QuestionModel2("Quanto é 900 ÷ 30?", "20", "25", "30", "35", "30"))
        list.add(QuestionModel2("Quanto é 19 x 6?", "110", "114", "118", "120", "114"))
        list.add(QuestionModel2("Quanto é 144 ÷ 9?", "14", "15", "16", "18", "16"))
        list.add(QuestionModel2("Quanto é 50 x 5?", "225", "240", "250", "260", "250"))
        list.add(QuestionModel2("Quanto é 625 ÷ 25?", "20", "23", "25", "28", "25"))
    }


    private fun setFour() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 24 ÷ 3?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel2("Quanto é 8 x 8?", "56", "60", "64", "72", "64"))
        list.add(QuestionModel2("Quanto é 49 ÷ 7?", "5", "6", "7", "8", "7"))
        list.add(QuestionModel2("Quanto é 100 - 37?", "59", "61", "63", "67", "63"))
        list.add(QuestionModel2("Quanto é 5³?", "100", "110", "125", "150", "125"))
        list.add(QuestionModel2("Quanto é 7²?", "40", "48", "49", "56", "49"))
        list.add(QuestionModel2("Quanto é 144 ÷ 12?", "10", "11", "12", "13", "12"))
        list.add(QuestionModel2("Quanto é 11 x 9?", "88", "95", "99", "108", "99"))
        list.add(QuestionModel2("Quanto é 15²?", "220", "225", "230", "240", "225"))
        list.add(QuestionModel2("Quanto é 200 ÷ 8?", "22", "24", "25", "26", "25"))
    }


    private fun setThree() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 15 x 2?", "25", "28", "30", "32", "30"))
        list.add(QuestionModel2("Quanto é 36 ÷ 6?", "4", "5", "6", "7", "6"))
        list.add(QuestionModel2("Quanto é 21 + 14?", "33", "34", "35", "36", "35"))
        list.add(QuestionModel2("Quanto é 40 - 18?", "20", "21", "22", "23", "22"))
        list.add(QuestionModel2("Quanto é 7 x 3?", "18", "19", "20", "21", "21"))
        list.add(QuestionModel2("Quanto é 48 ÷ 8?", "4", "5", "6", "7", "6"))
        list.add(QuestionModel2("Quanto é 100 - 40?", "55", "58", "60", "62", "60"))
        list.add(QuestionModel2("Quanto é 17 + 24?", "38", "39", "40", "41", "41"))
        list.add(QuestionModel2("Quanto é 6 x 9?", "52", "53", "54", "55", "54"))
        list.add(QuestionModel2("Quanto é 81 ÷ 9?", "7", "8", "9", "10", "9"))
    }


    private fun setTwo() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 12 + 8?", "18", "19", "20", "21", "20"))
        list.add(QuestionModel2("Quanto é 15 - 7?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel2("Quanto é 9 + 9?", "16", "17", "18", "19", "18"))
        list.add(QuestionModel2("Quanto é 20 - 4?", "14", "15", "16", "17", "16"))
        list.add(QuestionModel2("Quanto é 11 + 11?", "20", "21", "22", "23", "22"))
        list.add(QuestionModel2("Quanto é 30 - 12?", "16", "17", "18", "19", "18"))
        list.add(QuestionModel2("Quanto é 14 + 5?", "17", "18", "19", "20", "19"))
        list.add(QuestionModel2("Quanto é 25 - 9?", "14", "15", "16", "17", "16"))
        list.add(QuestionModel2("Quanto é 18 + 7?", "23", "24", "25", "26", "25"))
        list.add(QuestionModel2("Quanto é 50 - 20?", "28", "29", "30", "31", "30"))
    }


    private fun setOne() {
        list = arrayListOf()
        list.add(QuestionModel2("Quanto é 2 + 3?", "3", "5", "6", "7", "5"))
        list.add(QuestionModel2("Quanto é 7 - 4?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel2("Quanto é 6 + 2?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel2("Quanto é 10 - 5?", "3", "4", "5", "6", "5"))
        list.add(QuestionModel2("Quanto é 4 + 4?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel2("Quanto é 9 - 3?", "5", "6", "7", "8", "6"))
        list.add(QuestionModel2("Quanto é 5 + 5?", "8", "9", "10", "11", "10"))
        list.add(QuestionModel2("Quanto é 8 - 2?", "4", "5", "6", "7", "6"))
        list.add(QuestionModel2("Quanto é 3 + 6?", "6", "8", "9", "10", "9"))
        list.add(QuestionModel2("Quanto é 7 - 1?", "5", "6", "7", "8", "6"))
    }
}
