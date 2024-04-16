package com.example.quizapp.UI

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.Utils
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.model.Answer
import com.example.quizapp.model.Question
import com.example.quizapp.model.Question.Companion.mockQuestions
import com.example.quizapp.repository.HistoryRepository
import com.example.quizapp.room.AppDatabase
import com.example.quizapp.room.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private var selectedContinent: Int = 0

    private lateinit var question: MutableList<Question>
    private lateinit var currentQuestion: Question
    private var index: Int = 0
    private var submitQuestionAnswer: Boolean = false
    private val numQuestion = 5


    //Answer
    private lateinit var answers: MutableList<Answer>
    private lateinit var answersView: MutableList<TextView>
    private var indexAnswer: Int = 0
    private var correctAnswers: Int = 0

    //lives count
    var lives: Int = 3

    private lateinit var allQuestionAnswerSave : LiveData<List<Result>>
    private lateinit var repository : HistoryRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get Room Database
        val historyDao = AppDatabase.getInstance(requireContext()).quizDao()
        repository = HistoryRepository(historyDao)
        allQuestionAnswerSave = repository.allResult

    }

    //launching a new coroutine to insert the data in a non-blocking way.
    private fun insert(result: Result) = lifecycleScope.launch(Dispatchers.IO) {
        repository.insert(result)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(layoutInflater, container, false)


        binding.btnSubmit.setOnClickListener { submitAnswer() }

        question = Question.mockQuestions.toMutableList()

        answersView = mutableListOf(
            binding.tvAnswerOne,
            binding.tvAnswerTwo,
            binding.tvAnswerThree,
            binding.tvAnswerFour
        )


        //set Question from selected continent
        setQuestions()
        //update answer view an user selection
        selectedAnswerView()
        return binding.root
    }

    private fun setQuestions() {

        //set random question and answer
        randomQuestionAndAnswer()
        //reset the view to default
        defaultAnswerView()

        binding.progressBar.progress = index.plus(1)
        binding.progressBar.max = numQuestion
        binding.tvProgressBar.text = getString(R.string.quiz_progress, index.plus(1), numQuestion)
        binding.tvQuestion.text = currentQuestion.description
        for ((index, answer) in answersView.withIndex()) {
            answer.text = answers[index].description
            answer.isClickable = true
        }
        binding.btnSubmit.text = "Submit"

        //cannot submit Question if a answer is not selected
        submitQuestionAnswer = false
    }

    private fun defaultAnswerView() {
        for (answer in answersView) {
            answer.setTextColor(ContextCompat.getColor(answer.context, R.color.secondaryTextColor))
            answer.typeface = Typeface.DEFAULT
            answer.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_default_answer_border)

        }
    }

    private fun randomQuestionAndAnswer() {
        //set random order for questions
        question.shuffle()
        //update current Question
        currentQuestion = question[index]
        //set answers into a copy of the mutableList
        answers = currentQuestion.allAnswers.toMutableList()
        // Set random order for answers.
        answers.shuffle()

    }

    //get the selected index answer and set the view
    private fun selectedAnswerView() {
        for ((index, answer) in answersView.withIndex()) {
            answer.setOnClickListener { defaultAnswerView() }

            //set the answer view to selected
            answer.setTypeface(answer.typeface, Typeface.BOLD)
            answer.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_default_answer_border)

            //Get the selected answer
            indexAnswer = index
            //Enable to select an answer
            answer.isClickable = true
            //can pass to another flag
            submitQuestionAnswer = true
        }
    }

    //Highlight the answer selected for wrong or right
    private fun highlightAnswerView(answer: Answer, drawableView: Int) {
        for ((index) in answersView.withIndex()) {
            when (answer) {
                answers[index] -> {
                    //set text color
                    answersView[index].setTextColor(
                        ContextCompat.getColor(
                            answersView[index].context,
                            R.color.primaryTextColor
                        )
                    )
                    //set backGround
                    answersView[index].background =
                        ContextCompat.getDrawable(requireContext(), drawableView)
                }
            }
        }
    }

    private fun submitAnswer() {
        val args = QuizFragmentArgs.fromBundle(requireArguments())

        when {
            lives <= 0 -> {
                val resultLose = Result("You Lose!", correctAnswers, numQuestion, args.continent)
                insert(resultLose)
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToQuizWonFragment())

            }
            //show a toast
            !submitQuestionAnswer -> Utils.shareText(requireContext(), "Please select an answer.")

            //when pass to another Questions
            indexAnswer == 5  -> { //the number 5 have a value, is only to continue the quiz.
                //update index to pass another question
                index++
                when {
                    //if number of questions answer nto meet the total questions, continue the quiz
                    index < numQuestion -> {
                        //set a new Question
                        setQuestions()
                        //update selected answer view
                        selectedAnswerView()
                    }

                    else -> {
                        //Insert data a database
                        val historyWon = Result(
                            "You Won!",
                            correctAnswers, numQuestion, args.continent
                        )
                        insert(historyWon)
                        // Go to QuizWonFragment when finish the quiz and pass arguments.
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToQuizWonFragment(
                                //correctAnswers, numQuestion, args.continent
                            )
                        )
                    }
                }
            }
            //when answer selected check for current or wrong answer
            else -> {
                //if answer wrong
                if (answers[indexAnswer] != currentQuestion.choseAnswer) {
                    //mark selected view to wrong
                    highlightAnswerView(answers[indexAnswer], R.drawable.bg_wrong_answer_border)

                    //Decrease lives
                    lives--

                    when (lives) {
                        2 -> binding.ivLiveOne.visibility = View.GONE
                        1 -> binding.ivLiveTwo.visibility = View.GONE
                        0 -> binding.ivLiveThree.visibility = View.GONE
                    }
                } else {
                    correctAnswers++
                }

                //Always check for correct answer
                highlightAnswerView(
                    currentQuestion.allAnswers[0],
                    R.drawable.bg_correct_answer_border
                )

                //when answer is sumbit , user cannot change the answer
                for (answer in answersView) {
                    answer.isClickable = false
                }
                when{
                    //when the questions are over bottom submit change to finish
                    index + 1 == numQuestion ->
                        binding.btnSubmit.text = "Finish"
                    //when the user lose button submit change to finish
                    lives == 0 -> binding.btnSubmit.text = "Finish"
                    //Or update button to next question
                    else -> binding.btnSubmit.text = "Next Flag"

                }
                //Reset selected answer for pass to another question
                indexAnswer = 5
            }
        }

    }

}