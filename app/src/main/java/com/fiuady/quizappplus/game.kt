package com.fiuady.quizappplus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.fiuady.quizappplus.db.*
import java.text.SimpleDateFormat
import java.util.*

class game : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var pregunta: TextView
    private lateinit var pistas_text: TextView
    private lateinit var pistas: TextView
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var opcion1Button: Button
    private lateinit var opcion2Button: Button
    private lateinit var opcion3Button: Button
    private lateinit var opcion4Button: Button

    val filter = setOf('[', ']', ',')
    var questionsaux = mutableListOf<Questions>()
    var questions = mutableListOf<Questions>()
    var lsans = mutableListOf<Questions_Answers>()
    var currentQuestionIndex = 0
    var buttonArrayAux = arrayListOf<Button>()
    var puntos :Double=0.0
    var contestadas=0
    var correctas=0
    var cheats=0
    var finish =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)
        val activeuser = db.usersDao().getActiveUser()
        val memoryactual = db.questionmemoryDao().getpending(activeuser.id)
        val arreglopreg = memoryactual.questionAry?.filterNot { filter.contains(it) }
        val arreglopregaux = arreglopreg?.split(" ")?.map { it.toInt() }?.toTypedArray()
        val settings = db.settingsDao().getsettings(activeuser.id)
        questions.clear()
        questionsaux = arreglopregaux?.let {
            db.questionsDao().getQuestionsbyids(it)
        } as MutableList<Questions>

        for (indice in arreglopregaux) {
            for (question in questionsaux) {
                if (question.id == indice) {
                    questions.add(question)
                }
            }
        }

        currentQuestionIndex = memoryactual.currentquestion

        questionText = findViewById(R.id.question_text)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        opcion1Button = findViewById(R.id.opcion1_button)
        opcion2Button = findViewById(R.id.opcion2_button)
        opcion3Button = findViewById(R.id.opcion3_button)
        opcion4Button = findViewById(R.id.opcion4_button)
        pistas = findViewById(R.id.pistas)
        pistas_text = findViewById(R.id.Pistas_text)
        pregunta = findViewById(R.id.pregunta)

        val buttonArray =
            arrayListOf<Button>(opcion1Button, opcion2Button, opcion3Button, opcion4Button)

        if (settings.hints == 1) {
            pistas.setText("${memoryactual.cheats}/${settings.hintsquantity}")
        }
        pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")
        questionText.setText(currentQuestion.question_text)
        //Recuperando respuestas
        var memoria = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
        val arregloAns = memoria.answerstring!!
        val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
        val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
        var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
        lsans.clear()
        for (indice in arrayidAns) {
            for (answer in lsAnsaux) {
                if (answer.id == indice) {
                    lsans.add(answer)
                }
            }
        }

        if (settings.dificulty == 1) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.isVisible = false
            opcion4Button.isVisible = false
        } else if (settings.dificulty == 2) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.setText(lsans[2].answer_text)
            opcion4Button.isVisible = false
        } else if (settings.dificulty == 3) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.setText(lsans[2].answer_text)
            opcion4Button.setText(lsans[3].answer_text)
        }
        status(memoria, settings)

        nextButton.setOnClickListener {
            nextQuestion(db, memoryactual)
            buttonArrayAux.clear()
            questionText.setText(currentQuestion.question_text)
            pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")

            //Recuperando respuestas
            memoria = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
            val arregloAns = memoria.answerstring!!
            val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
            val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
            var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
            lsans.clear()
            for (indice in arrayidAns) {
                for (answer in lsAnsaux) {
                    if (answer.id == indice) {
                        lsans.add(answer)
                    }
                }
            }

            if (settings.dificulty == 1) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.isVisible = false
                opcion4Button.isVisible = false
            } else if (settings.dificulty == 2) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.isVisible = false
            } else if (settings.dificulty == 3) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.setText(lsans[3].answer_text)
            }
            status(memoria, settings)

            if(finish==false){
                puntuacion(settings, db)
            }else{

                val puntuaciones = Intent(this@game, final_score::class.java)
                startActivity(puntuaciones)
            }

        }

        prevButton.setOnClickListener {
            prevQuestion(db, memoryactual)
            questionText.setText(currentQuestion.question_text)
            pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")

            //Recuperando respuestas
            memoria = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
            val arregloAns = memoria.answerstring!!
            val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
            val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
            var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
            lsans.clear()
            for (indice in arrayidAns) {
                for (answer in lsAnsaux) {
                    if (answer.id == indice) {
                        lsans.add(answer)
                    }
                }
            }

            if (settings.dificulty == 1) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.isVisible = false
                opcion4Button.isVisible = false
            } else if (settings.dificulty == 2) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.isVisible = false
            } else if (settings.dificulty == 3) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.setText(lsans[3].answer_text)
            }
            status(memoria, settings)
            if(finish==false){
                puntuacion(settings, db)
           }else{
                val puntuaciones = Intent(this@game, final_score::class.java)
                startActivity(puntuaciones)
            }
        }
        if (settings.hints == 0) questionText.isClickable = false

        questionText.setOnClickListener {
            if (settings.hintsquantity== memoryactual.cheats){
                Toast.makeText(this, "NO more cheats", Toast.LENGTH_SHORT).show()
            } else {
                memoryactual.cheats++
                memoria.cheats++
                cheats(buttonArray, buttonArrayAux, settings, memoria, memoryactual, lsans)
                pistas.setText("${memoryactual.cheats}/${settings.hintsquantity}")
                db.questionmemoryDao().updatequestionmemory(memoryactual)
                db.answermemoryDao().updateAnsMem(memoria)
            }
        }

        opcion1Button.setOnClickListener {
            memoria.resp = 1

            if (lsans[0].answer == 1) {
                memoria.status = 1
            } else {
                memoria.status = 2
            }
            db.answermemoryDao().updateAnsMem(memoria)
            status(memoria, settings)
        }

        opcion2Button.setOnClickListener {
            memoria.resp = 2

            if (lsans[1].answer == 1) {
                memoria.status = 1
            } else {
                memoria.status = 2
            }
            db.answermemoryDao().updateAnsMem(memoria)
            status(memoria, settings)
        }

        opcion3Button.setOnClickListener {
            memoria.resp = 3

            if (lsans[1].answer == 1) {
                memoria.status = 1
            } else {
                memoria.status = 2
            }
            db.answermemoryDao().updateAnsMem(memoria)
            status(memoria, settings)
        }

        opcion4Button.setOnClickListener {
            memoria.resp = 4

            if (lsans[1].answer == 1) {
                memoria.status = 1
            } else {
                memoria.status = 2
            }
            db.answermemoryDao().updateAnsMem(memoria)
            status(memoria, settings)
        }

    }


    val currentQuestion: Questions
        get() = questions[currentQuestionIndex]
    val questionNumber: Int
        get() = questions.size

    fun nextQuestion(db: AppDatabase, memory: Questionmemory) {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        memory.currentquestion = currentQuestionIndex
        db.questionmemoryDao().updatequestionmemory(memory)
    }

    fun prevQuestion(db: AppDatabase, memory: Questionmemory) {
        currentQuestionIndex = (questions.size + currentQuestionIndex - 1) % questions.size
        memory.currentquestion = currentQuestionIndex
        db.questionmemoryDao().updatequestionmemory(memory)

    }

    fun status(memoria: Answersmemory, settings: Settings) {
        when (memoria.status) {
            0 -> {
                questionText.setTextColor(Color.parseColor("#000000"))
                when (settings.dificulty) {
                    1 -> {
                        opcion1Button.isEnabled = true
                        opcion2Button.isEnabled = true
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    2 -> {
                        opcion1Button.isEnabled = true
                        opcion2Button.isEnabled = true
                        opcion3Button.isEnabled = true
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    3 -> {
                        opcion1Button.isEnabled = true
                        opcion2Button.isEnabled = true
                        opcion3Button.isEnabled = true
                        opcion4Button.isEnabled = true
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion4Button.setBackgroundResource(android.R.drawable.btn_default)

                    }
                }
            }
            1 -> {
                questionText.setTextColor(Color.parseColor("#217922"))

                when (settings.dificulty) {
                    1 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    2 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion3Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    3 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion3Button.isEnabled = false
                        opcion4Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion4Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                }
                when (memoria.resp) {
                    1 -> {
                        opcion1Button.setBackgroundColor(Color.parseColor("#217922"))
                    }
                    2 -> {
                        opcion2Button.setBackgroundColor(Color.parseColor("#217922"))
                    }
                    3 -> {
                        opcion3Button.setBackgroundColor(Color.parseColor("#217922"))
                    }
                    4 -> {
                        opcion4Button.setBackgroundColor(Color.parseColor("#217922"))
                    }
                }

            }
            2 -> {
                questionText.setTextColor(Color.parseColor("#FF0000"))

                when (settings.dificulty) {
                    1 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    2 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion3Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                    3 -> {
                        opcion1Button.isEnabled = false
                        opcion2Button.isEnabled = false
                        opcion3Button.isEnabled = false
                        opcion4Button.isEnabled = false
                        opcion1Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion2Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion3Button.setBackgroundResource(android.R.drawable.btn_default)
                        opcion4Button.setBackgroundResource(android.R.drawable.btn_default)
                    }
                }
                when (memoria.resp) {
                    1 -> {
                        opcion1Button.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                    2 -> {
                        opcion2Button.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                    3 -> {
                        opcion3Button.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                    4 -> {
                        opcion4Button.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                }
            }
        }

    }

    fun cheats(
        buttonArray: ArrayList<Button>,
        buttonArrayAux: ArrayList<Button>,
        settings: Settings,
        answersmemory: Answersmemory,
        questionmemory: Questionmemory,
        lsans: MutableList<Questions_Answers>
    ) {
        buttonArrayAux.clear()
        if (answersmemory.status == 0 && questionmemory.cheats >= 0) {
            if (settings.dificulty == answersmemory.cheats) {
                for (i in 0 until lsans.size) {
                    if (lsans[i].answer == 1) {
                        answersmemory.resp = (i + 1)
                        answersmemory.status = 1
                        status(answersmemory, settings)
                        break
//                        buttonArrayAux[0].isEnabled = false
//                        buttonArrayAux[1].isEnabled = false
//                         buttonArrayAux[2].isEnabled = false
                    }
                }
//                if (settings.dificulty > 1)
            } else {

                for (i in 0 until lsans.size) {
                    if (lsans[i].answer == 1) {
                        buttonArray[i].isEnabled = true
                    } else if (lsans[i].answer == 0) {
                        buttonArrayAux.add(buttonArray[i])
                    }
                }
                for (i in 0 until buttonArrayAux.size) {
                    buttonArrayAux[i].isEnabled = false
                }
                for (j in 0 until (settings.dificulty - answersmemory.cheats)) {
                    buttonArrayAux[j].isEnabled = true
                }
            }


        }


    }

    fun puntuacion(settings: Settings, db: AppDatabase) {
        var contestadas=0
        var cheats=0
        var correctas=0

       val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val fecha = simpleDateFormat.format(Date()).toString()

        var usuario_activo=db.usersDao().getActiveUser()
        var memory= db.answermemoryDao().getAnswerarraybyid(settings.userid)
        for(i in 0 until memory.size){
            if(memory[i].status==1)correctas++
            if(memory[i].status!=0)contestadas++
            if(memory[i].cheats!=0)cheats++
        }
        if (contestadas == questions.size) {
            finish=true

            if(settings.dificulty==3){
                puntos=((correctas-cheats)/questions.size.toDouble())*100
            }
            if(settings.dificulty==1 || settings.dificulty ==2)
            {
                puntos=((correctas-cheats)/questions.size.toDouble())*100
            }

            db.scoresDao().InsertScoreManual(
                fecha,
                settings.userid,
                usuario_activo.name,
                settings.dificulty,
                memory.size,
                correctas,
                cheats,
                puntos
            )
        }

    }

}