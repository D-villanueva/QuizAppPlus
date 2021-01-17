package com.fiuady.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import com.fiuady.quizappplus.db.Questions

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
    var questions = mutableListOf<Questions>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val dbBuilder: DbBuilder by viewModels()
        val db = dbBuilder.buildBd(this)
        val userid = 2
        val memoryactual = db.questionmemoryDao().getpending(userid)
        val arreglopreg = memoryactual.questionAry?.filterNot { filter.contains(it) }
        val arreglopregaux = arreglopreg?.split(" ")?.map { it.toInt() }?.toTypedArray()
        questions = arreglopregaux?.let { db.questionsDao().getQuestionsbyids(it) } as MutableList<Questions>


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

        questionText.setText(currentQuestion.question_text)

        nextButton.setOnClickListener {
            nextQuestion()
            questionText.setText(currentQuestion.question_text)
        }
    }

    var currentQuestionIndex = 0

    val currentQuestion: Questions
        get() = questions[currentQuestionIndex]
    val questionNumber: Int
        get() = questions.size

    fun nextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
    }

    fun prevQuestion() {
        currentQuestionIndex = (questions.size + currentQuestionIndex - 1) % questions.size
    }
}