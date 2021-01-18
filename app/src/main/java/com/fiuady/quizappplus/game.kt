package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.fiuady.quizappplus.db.AppDatabase
import com.fiuady.quizappplus.db.Questionmemory
import com.fiuady.quizappplus.db.Questions
import com.fiuady.quizappplus.db.Questions_Answers

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)
        val activeuser=db.usersDao().getActiveUser()
        val memoryactual = db.questionmemoryDao().getpending(activeuser.id)
        val arreglopreg = memoryactual.questionAry?.filterNot { filter.contains(it) }
        val arreglopregaux = arreglopreg?.split(" ")?.map { it.toInt() }?.toTypedArray()
        val settings = db.settingsDao().getsettings(activeuser.id )
        questions.clear()
        questionsaux = arreglopregaux?.let { db.questionsDao().getQuestionsbyids(it) } as MutableList<Questions>

        for (indice in arreglopregaux){
            for ( question in questionsaux){
                if (question.id == indice){
                    questions.add(question)
                }
            }
        }



        currentQuestionIndex=memoryactual.currentquestion

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

        pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")
        questionText.setText(currentQuestion.question_text)
        //Recuperando respuestas
        val arregloAns = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
        val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
        val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
        var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
        lsans.clear()
        for (indice in arrayidAns){
            for ( answer in lsAnsaux){
                if (answer.id == indice){
                    lsans.add(answer)
                }
            }
        }

        if (settings.dificulty==1) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.isVisible=false
            opcion4Button.isVisible=false
        }else if (settings.dificulty==2) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.setText(lsans[2].answer_text)
            opcion4Button.isVisible=false
        }
        else if (settings.dificulty==3) {
            opcion1Button.setText(lsans[0].answer_text)
            opcion2Button.setText(lsans[1].answer_text)
            opcion3Button.setText(lsans[2].answer_text)
            opcion4Button.setText(lsans[3].answer_text)
        }

        nextButton.setOnClickListener {
            nextQuestion(db, memoryactual)
            questionText.setText(currentQuestion.question_text)
            pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")

            //Recuperando respuestas
            val arregloAns = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
            val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
            val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
            var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
            lsans.clear()
            for (indice in arrayidAns){
                for ( answer in lsAnsaux){
                    if (answer.id == indice){
                        lsans.add(answer)
                    }
                }
            }

            if (settings.dificulty==1) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.isVisible=false
                opcion4Button.isVisible=false
            }else if (settings.dificulty==2) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.isVisible=false
            }
            else if (settings.dificulty==3) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.setText(lsans[3].answer_text)
            }
        }

        prevButton.setOnClickListener {
            prevQuestion(db, memoryactual)
            questionText.setText(currentQuestion.question_text)
            pregunta.text = ("${currentQuestionIndex + 1}/${questionNumber}")

            //Recuperando respuestas
            val arregloAns = db.answermemoryDao().getAnswersidbyQid(activeuser.id, currentQuestion.id)
            val arregloAnsAux = arregloAns.filterNot { filter.contains(it) }
            val arrayidAns = arregloAnsAux.split(" ")?.map { it.toInt() }?.toTypedArray()
            var lsAnsaux = db.questionanswerDao().getanswer(arrayidAns)
            lsans.clear()
            for (indice in arrayidAns){
                for ( answer in lsAnsaux){
                    if (answer.id == indice){
                        lsans.add(answer)
                    }
                }
            }

            if (settings.dificulty==1) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.isVisible=false
                opcion4Button.isVisible=false
            }else if (settings.dificulty==2) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.isVisible=false
            }
            else if (settings.dificulty==3) {
                opcion1Button.setText(lsans[0].answer_text)
                opcion2Button.setText(lsans[1].answer_text)
                opcion3Button.setText(lsans[2].answer_text)
                opcion4Button.setText(lsans[3].answer_text)
            }
        }
    }



    val currentQuestion: Questions
        get() = questions[currentQuestionIndex]
    val questionNumber: Int
        get() = questions.size

    fun nextQuestion(db:AppDatabase, memory:Questionmemory) {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        memory.currentquestion=currentQuestionIndex
        db.questionmemoryDao().updatequestionmemory(memory)
    }

    fun prevQuestion(db:AppDatabase, memory:Questionmemory) {
        currentQuestionIndex = (questions.size + currentQuestionIndex - 1) % questions.size
        memory.currentquestion=currentQuestionIndex
        db.questionmemoryDao().updatequestionmemory(memory)

    }
}