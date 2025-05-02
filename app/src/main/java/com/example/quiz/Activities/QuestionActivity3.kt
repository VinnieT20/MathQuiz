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
import com.example.quiz.models.QuestionModel3
import com.example.quiz.R
import com.example.quiz.databinding.ActivityQuestion3Binding


class QuestionActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityQuestion3Binding
    private var list = ArrayList<QuestionModel3>()
    private var count = 0
    private var position = 0
    private var score = 0
    private var timer: CountDownTimer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityQuestion3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        resetTimer();
        timer?.start()


        val setName3 = intent.getStringExtra("set3")
        if (setName3 == "Nível-1") {
            setOne()
        } else if (setName3 == "Nível-2") {
            setTwo()
        } else if (setName3 == "Nível-3") {
            setThree()
        } else if (setName3 == "Nível-4") {
            setFour()
        } else if (setName3 == "Nível-5") {
            setFive()
        } else if (setName3 == "Nível-6") {
            setSix()
        } else if (setName3 == "Nível-7") {
            setSeven()
        } else if (setName3 == "Nível-8") {
            setEight()
        } else if (setName3 == "Nível-9") {
            setNine()
        } else if (setName3 == "Nível-10") {
            setTen()
        }

        for (i in 0 until binding.optionContainer3.childCount) {
            val child = binding.optionContainer3.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener { view ->
                    checkAnswer(view as Button)
                }
            }
        }
        playAnimation(binding.question3, 0, list[position].question3)

        binding.btnNext3.setOnClickListener {
            timer?.cancel()
            timer?.start()


            binding.btnNext3.isEnabled = false
            binding.btnNext3.alpha = 0.3f
            enableOption(true)
            position++
            if (position == list.size) {
                val intent = Intent(this, ScoreActivity3::class.java)
                intent.putExtra("score3", score)
                intent.putExtra("total3", list.size)
                startActivity(intent)
                finish()
                return@setOnClickListener

            }
            count = 0
            playAnimation(binding.question3, 0, list[position].question3)

        }
    }
    private fun resetTimer(){
        timer= object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timer3.text=(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                val dialog = Dialog(this@QuestionActivity3)
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.timeout_dialog)

                dialog.findViewById<View>(R.id.tryAgain).setOnClickListener{
                    val intent = Intent(this@QuestionActivity3,SetsActivity3::class.java)
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
            .setInterpolator(DecelerateInterpolator()).setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator) {
                    if (value==0&&count<4){
                        var option=""
                        when(count){
                            0->option=list[position].optionA3
                            1->option=list[position].optionB3
                            2->option=list[position].optionC3
                            3->option=list[position].optionD3
                        }
                        playAnimation(binding.optionContainer3.getChildAt(count),0,option)
                        count++
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    if (value==0){
                        try {
                            (view as TextView).text=data
                            binding.totalQuestion3.text="${position+1}/${list.size}"
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
            binding.optionContainer3.getChildAt(i).isEnabled=enable
            if (enable){
                binding.optionContainer3.getChildAt(i).setBackgroundResource(R.drawable.btn_opt)
            }
        }
    }



    private fun checkAnswer(selectedOption: Button) {
        timer?.cancel()

        binding.btnNext3.isEnabled=true
        binding.btnNext3.alpha=1f

        if(selectedOption.text.toString()==list[position].correctAnswer3){
            score++
            selectedOption.setBackgroundResource(R.drawable.right_answ)
        }else{
            selectedOption.setBackgroundResource(R.drawable.wrong_answ)
            val correctOption = binding.optionContainer3.findViewWithTag<Button>(list[position].correctAnswer3)
            correctOption?.setBackgroundResource(R.drawable.right_answ)
        }

    }


    private fun setTen() {
        list = arrayListOf()
        list.add(QuestionModel3("Volume de um cilindro com raio 4 e altura 5 (π=3):", "240", "250", "260", "270", "240"))
        list.add(QuestionModel3("Quantas faces tem um dodecaedro?", "10", "12", "14", "20", "12"))
        list.add(QuestionModel3("Área de uma pirâmide de base quadrada com aresta 6 e altura 4:", "96", "100", "108", "120", "96"))
        list.add(QuestionModel3("Volume de um cubo com diagonal 6√3:", "216", "324", "512", "729", "216"))
        list.add(QuestionModel3("Qual é o valor de π ao ser usado na área de um círculo exato?", "3", "3,12", "3,14", "3,16", "3,14"))
        list.add(QuestionModel3("Número de diagonais de um polígono de 15 lados:", "90", "95", "105", "110", "90"))
        list.add(QuestionModel3("Qual é a área lateral de um cone com geratriz 10 e raio 6 (π=3):", "180", "200", "220", "240", "180"))
        list.add(QuestionModel3("Quantas diagonais tem um polígono de 20 lados?", "160", "170", "180", "190", "170"))
        list.add(QuestionModel3("Qual é o apótema de um hexágono regular de lado 10?", "8,66", "9,50", "10", "12", "8,66"))
        list.add(QuestionModel3("Área de um losango com diagonais 10 e 24:", "100", "110", "120", "130", "120"))
    }


    private fun setNine() {
        list = arrayListOf()
        list.add(QuestionModel3("Quantas arestas tem um icosaedro?", "20", "30", "40", "60", "30"))
        list.add(QuestionModel3("Área de uma esfera com raio 5 (π=3):", "300", "314", "340", "360", "300"))
        list.add(QuestionModel3("Quantas diagonais tem um polígono de 12 lados?", "54", "60", "66", "72", "54"))
        list.add(QuestionModel3("Volume de um cone com raio 3 e altura 9 (π=3):", "81", "84", "85", "87", "84"))
        list.add(QuestionModel3("Qual é a medida de cada ângulo interno de um octógono regular?", "135°", "140°", "145°", "150°", "135°"))
        list.add(QuestionModel3("Uma pirâmide com base pentagonal tem quantas arestas?", "5", "8", "10", "12", "10"))
        list.add(QuestionModel3("A altura de um triângulo equilátero de lado 6 é:", "3√3", "4√2", "3", "6", "3√3"))
        list.add(QuestionModel3("Volume de uma esfera com raio 2 (π=3):", "30", "32", "33", "34", "32"))
        list.add(QuestionModel3("Área total de um cilindro com raio 2 e altura 3 (π=3):", "60", "62", "64", "66", "62"))
        list.add(QuestionModel3("Área de um triângulo equilátero de lado 10:", "43,3", "45", "47,4", "50", "43,3"))
    }


    private fun setEight() {
        list = arrayListOf()
        list.add(QuestionModel3("Área lateral de um cilindro é:", "2πr²", "πr²h", "2πrh", "πr³", "2πrh"))
        list.add(QuestionModel3("Volume de uma pirâmide é:", "Ab x h", "Ab x h / 3", "πr²h", "2Ab x h", "Ab x h / 3"))
        list.add(QuestionModel3("Qual é a área de um hexágono regular com lado 6 cm?", "93,5 cm²", "93,6 cm²", "93,7 cm²", "93,8 cm²", "93,5 cm²"))
        list.add(QuestionModel3("Quantas diagonais tem um decágono?", "35", "40", "45", "50", "35"))
        list.add(QuestionModel3("Qual figura tridimensional tem apenas uma base circular?", "Cubo", "Esfera", "Cone", "Cilindro", "Cone"))
        list.add(QuestionModel3("Quantos vértices tem uma pirâmide de base quadrada?", "4", "5", "6", "8", "5"))
        list.add(QuestionModel3("Número de lados de um polígono com soma dos ângulos internos igual a 1440°:", "9", "10", "11", "12", "10"))
        list.add(QuestionModel3("O que representa a constante π?", "Área", "Volume", "Proporção entre diâmetro e raio", "Razão entre circunferência e diâmetro", "Razão entre circunferência e diâmetro"))
        list.add(QuestionModel3("Um triângulo com um ângulo de 90° é chamado de:", "Isósceles", "Equilátero", "Escaleno", "Retângulo", "Retângulo"))
        list.add(QuestionModel3("Área total de um cubo com aresta 5 cm:", "100 cm²", "125 cm²", "150 cm²", "6 x 25 = 150 cm²", "150 cm²"))
    }


    private fun setSeven() {
        list = arrayListOf()
        list.add(QuestionModel3("Área de um círculo com raio 14 cm (π=3,14):", "615,44 cm²", "600 cm²", "650 cm²", "700 cm²", "615,44 cm²"))
        list.add(QuestionModel3("Quantos lados tem um polígono com 27 diagonais?", "7", "8", "9", "10", "9"))
        list.add(QuestionModel3("Um prisma triangular tem quantas faces?", "4", "5", "6", "7", "5"))
        list.add(QuestionModel3("O apótema é usado em qual fórmula?", "Área do quadrado", "Área do círculo", "Área do polígono regular", "Volume da esfera", "Área do polígono regular"))
        list.add(QuestionModel3("Qual figura tem todas as diagonais iguais?", "Triângulo", "Quadrado", "Losango", "Paralelogramo", "Quadrado"))
        list.add(QuestionModel3("Qual é o volume de uma esfera com raio 3? (π=3)", "108", "113", "115", "117", "113"))
        list.add(QuestionModel3("Um prisma hexagonal tem quantas arestas?", "12", "18", "20", "24", "18"))
        list.add(QuestionModel3("Polígono regular com ângulo interno de 120° possui quantos lados?", "4", "5", "6", "8", "6"))
        list.add(QuestionModel3("O que é uma figura côncava?", "Tem um ângulo maior que 180°", "Tem lados iguais", "Tem volume", "Tem apenas ângulos retos", "Tem um ângulo maior que 180°"))
        list.add(QuestionModel3("Um cone tem quantas superfícies?", "1", "2", "3", "4", "2"))
    }


    private fun setSix() {
        list = arrayListOf()
        list.add(QuestionModel3("Qual é a medida interna de cada ângulo de um quadrado?", "45°", "60°", "90°", "120°", "90°"))
        list.add(QuestionModel3("Número de diagonais de um octógono?", "20", "21", "22", "24", "20"))
        list.add(QuestionModel3("Área de um triângulo com base 10 cm e altura 8 cm:", "40 cm²", "45 cm²", "50 cm²", "60 cm²", "40 cm²"))
        list.add(QuestionModel3("Qual é a soma dos ângulos externos de qualquer polígono?", "180°", "270°", "360°", "540°", "360°"))
        list.add(QuestionModel3("Volume de um cilindro é dado por:", "πr²h", "πrh²", "2πrh", "πr³", "πr²h"))
        list.add(QuestionModel3("Um losango é um tipo de:", "Paralelogramo", "Trapézio", "Triângulo", "Hexágono", "Paralelogramo"))
        list.add(QuestionModel3("Um polígono com todos os lados e ângulos diferentes é chamado de:", "Regular", "Irregular", "Concêntrico", "Simétrico", "Irregular"))
        list.add(QuestionModel3("Qual é a fórmula da área do trapézio?", "b x h", "a x b / 2", "(B + b) x h / 2", "l x l", "(B + b) x h / 2"))
        list.add(QuestionModel3("Número de lados de um polígono com soma dos ângulos internos igual a 1080°:", "6", "7", "8", "9", "8"))
        list.add(QuestionModel3("Um cubo tem quantas faces?", "4", "6", "8", "10", "6"))
    }


    private fun setFive() {
        list = arrayListOf()
        list.add(QuestionModel3("Um círculo com raio 7 cm tem área aproximada de:", "154 cm²", "144 cm²", "164 cm²", "174 cm²", "154 cm²"))
        list.add(QuestionModel3("Qual o nome de um triângulo com todos os lados diferentes?", "Isósceles", "Equilátero", "Escaleno", "Retângulo", "Escaleno"))
        list.add(QuestionModel3("Quantas diagonais tem um hexágono?", "6", "7", "9", "12", "9"))
        list.add(QuestionModel3("Um ângulo de 180° é chamado de:", "Agudo", "Obtuso", "Reto", "Raso", "Raso"))
        list.add(QuestionModel3("Área de um quadrado com lado 12 cm:", "144 cm²", "124 cm²", "134 cm²", "154 cm²", "144 cm²"))
        list.add(QuestionModel3("Quantos lados tem um heptágono?", "6", "7", "8", "9", "7"))
        list.add(QuestionModel3("Qual o volume de um paralelepípedo com 2x3x4 cm?", "24 cm³", "20 cm³", "22 cm³", "26 cm³", "24 cm³"))
        list.add(QuestionModel3("Soma dos ângulos internos de um pentágono:", "360°", "540°", "600°", "720°", "540°"))
        list.add(QuestionModel3("O que define um poliedro?", "Lados curvos", "Faces planas", "Arestas curvas", "Volume irregular", "Faces planas"))
        list.add(QuestionModel3("O que é congruência em geometria?", "Igualdade de área", "Mesma forma e tamanho", "Mesmo perímetro", "Mesmo volume", "Mesma forma e tamanho"))
    }


    private fun setFour() {
        list = arrayListOf()
        list.add(QuestionModel3("Qual é o perímetro de um quadrado com lados de 5 cm?", "10 cm", "15 cm", "20 cm", "25 cm", "20 cm"))
        list.add(QuestionModel3("Quantos lados tem um eneágono?", "7", "8", "9", "10", "9"))
        list.add(QuestionModel3("Qual é a fórmula da área de um triângulo?", "(base x altura)/2", "base x altura", "lado²", "πr²", "(base x altura)/2"))
        list.add(QuestionModel3("Qual é o nome do polígono com 12 lados?", "Decágono", "Dodecágono", "Hexágono", "Octógono", "Dodecágono"))
        list.add(QuestionModel3("Qual é o volume de um cubo com aresta de 3 cm?", "27 cm³", "9 cm³", "18 cm³", "36 cm³", "27 cm³"))
        list.add(QuestionModel3("Qual é o nome do ângulo maior que 90° e menor que 180°?", "Agudo", "Reto", "Obtuso", "Raso", "Obtuso"))
        list.add(QuestionModel3("Quantos ângulos tem um octógono?", "6", "7", "8", "9", "8"))
        list.add(QuestionModel3("A figura que possui dois pares de lados paralelos é:", "Trapézio", "Triângulo", "Paralelogramo", "Pentágono", "Paralelogramo"))
        list.add(QuestionModel3("O que é uma diagonal?", "Linha entre dois lados adjacentes", "Linha que liga dois vértices não consecutivos", "Linha central", "Lado externo", "Linha que liga dois vértices não consecutivos"))
        list.add(QuestionModel3("Quantos lados tem um icoságono?", "18", "19", "20", "21", "20"))
    }


    private fun setThree() {
        list = arrayListOf()
        list.add(QuestionModel3("Qual é a fórmula da área de um retângulo?", "b + h", "2b + 2h", "b x h", "b²", "b x h"))
        list.add(QuestionModel3("Quantos lados tem um decágono?", "8", "9", "10", "12", "10"))
        list.add(QuestionModel3("Qual é a soma dos ângulos internos de um quadrado?", "180°", "270°", "360°", "400°", "360°"))
        list.add(QuestionModel3("Quantos lados tem um losango?", "3", "4", "5", "6", "4"))
        list.add(QuestionModel3("Um triângulo com dois lados iguais é chamado de:", "Escaleno", "Equilátero", "Isósceles", "Retângulo", "Isósceles"))
        list.add(QuestionModel3("Qual a fórmula da área de um círculo?", "πd", "πr²", "2πr", "r²", "πr²"))
        list.add(QuestionModel3("Quantos graus tem um ângulo reto?", "45°", "60°", "90°", "120°", "90°"))
        list.add(QuestionModel3("A base de um triângulo mede 10 cm e a altura 4 cm. Qual é a área?", "20 cm²", "30 cm²", "40 cm²", "50 cm²", "20 cm²"))
        list.add(QuestionModel3("Qual figura tem exatamente 5 lados?", "Quadrado", "Pentágono", "Hexágono", "Trapézio", "Pentágono"))
        list.add(QuestionModel3("A soma dos ângulos internos de um hexágono é:", "360°", "540°", "720°", "900°", "720°"))
    }


    private fun setTwo() {
        list = arrayListOf()
        list.add(QuestionModel3("Qual é a soma dos ângulos internos de um triângulo?", "90°", "180°", "270°", "360°", "180°"))
        list.add(QuestionModel3("Quantos lados tem um quadrado?", "3", "4", "5", "6", "4"))
        list.add(QuestionModel3("Quantos vértices tem um cubo?", "6", "8", "10", "12", "8"))
        list.add(QuestionModel3("Um retângulo tem quantos ângulos retos?", "1", "2", "3", "4", "4"))
        list.add(QuestionModel3("Qual figura tem todos os lados iguais e ângulos de 60°?", "Quadrado", "Triângulo Equilátero", "Trapézio", "Losango", "Triângulo Equilátero"))
        list.add(QuestionModel3("Quantos lados tem um pentágono?", "4", "5", "6", "7", "5"))
        list.add(QuestionModel3("Qual é a forma de uma bola de futebol?", "Cilindro", "Cubo", "Esfera", "Cone", "Esfera"))
        list.add(QuestionModel3("Quantos lados tem um hexágono?", "5", "6", "7", "8", "6"))
        list.add(QuestionModel3("Qual é o nome de um polígono com 8 lados?", "Hexágono", "Pentágono", "Octógono", "Decágono", "Octógono"))
        list.add(QuestionModel3("Qual figura tem dois lados paralelos?", "Trapézio", "Quadrado", "Círculo", "Triângulo", "Trapézio"))
    }


    private fun setOne() {
        list = arrayListOf()
        list.add(QuestionModel3("Quantos lados tem um triângulo?", "2", "3", "4", "5", "3"))
        list.add(QuestionModel3("Qual é a soma dos ângulos internos de um triângulo?", "90°", "180°", "270°", "360°", "180°"))
        list.add(QuestionModel3("Como se chama um polígono de quatro lados?", "Pentágono", "Quadrado", "Hexágono", "Quadrilátero", "Quadrilátero"))
        list.add(QuestionModel3("Quantos graus tem um ângulo reto?", "45°", "60°", "90°", "180°", "90°"))
        list.add(QuestionModel3("O que é um círculo?", "Polígono", "Curva fechada", "Reta", "Trapézio", "Curva fechada"))
        list.add(QuestionModel3("Qual é a fórmula da área de um quadrado?", "L + L", "L x L", "2L", "L² + L", "L x L"))
        list.add(QuestionModel3("Um retângulo tem lados 4cm e 6cm. Qual é a área?", "10cm²", "20cm²", "24cm²", "30cm²", "24cm²"))
        list.add(QuestionModel3("Quantos lados tem um hexágono?", "5", "6", "7", "8", "6"))
        list.add(QuestionModel3("Qual é a fórmula da área do triângulo?", "(base + altura)/2", "base x altura", "(base x altura)/2", "base/altura", "(base x altura)/2"))
        list.add(QuestionModel3("O que é um ângulo obtuso?", "Menor que 90°", "Igual a 90°", "Maior que 90°", "Maior que 180°", "Maior que 90°"))
    }

}