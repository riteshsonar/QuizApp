package com.example.quizapp.model

data class Question(
    val id: Int,
    val index: Int,
    val categoryId: Int,
    val description: String,
    val allAnswers: List<Answer>,
    val choseAnswer: Answer? = null
){
    companion object {
        val mockQuestions = listOf(
            Question(
                id = 0,
                index = 0,
                categoryId = 9,
                description = "Two angles are complementary, if the sum of their measures is",
                allAnswers = Answer.getFirstQuestionMockAnswers()
            ),
            Question(
                id = 1,
                index = 1,
                categoryId = 9,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
            Question(
                id = 2,
                index = 2,
                categoryId = 9,
                description = "In what year was the first \"Mass Effect\" game released?",
                allAnswers = Answer.getThirdQuestionMockAnswers()
            ),
            Question(
                id = 3,
                index = 3,
                categoryId = 10,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
            Question(
                id = 4,
                index = 4,
                categoryId = 11,
                description = "In what year was the first \"Mass Effect\" game released?",
                allAnswers = Answer.getThirdQuestionMockAnswers()
            )
        )
    }

}