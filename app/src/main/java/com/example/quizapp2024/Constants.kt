package com.example.quizapp2024

object Constants {
    fun getQuestions(): ArrayList<QuestionClass> {
        val questionsList = ArrayList<QuestionClass>()
        val que1 = QuestionClass(
            1,
            "What country flag is this ?",
            R.drawable.australia,
            "Australia",
            "Argentina",
            "Armenia",
            "USA",
            1
        )
        val que2 = QuestionClass(2,
            "What country flag is this", R.drawable.mexico,
            "Australia",
            "Argentina",
            "Mexico",
            "USA",
            3)


        val que3 = QuestionClass(3,
            "What country flag is this", R.drawable.france,
            "Mexico",
            "France",
            "Africa",
            "USA",
            2)


        val que4 = QuestionClass(4,
            "What country flag is this", R.drawable.turkey,
            "Kazakistan",
            "Ukraine",
            "Turkey",
            "USA",
            3)


        val que5 = QuestionClass(5,
            "What country flag is this", R.drawable.us,
            "USA",
            "Argentina",
            "Armenia",
            "South America",
            1)


        val que6 = QuestionClass(6,
            "What country flag is this", R.drawable.uk,
            "Australia",
            "Argentina",
            "UK",
            "USA",
            3)


        val que7 = QuestionClass(7,
            "What country flag is this", R.drawable.european,
            "Scotland",
            "European Union",
            "Armenia",
            "USA",
            2)


        val que8 = QuestionClass(8,
            "What country flag is this", R.drawable.germany,
            "Netherlands",
            "Spain",
            "Belgium",
            "Germany",
            4)


        val que9 = QuestionClass(9,
            "What country flag is this", R.drawable.canada,
            "Denmark",
            "Argentina",
            "Canada",
            "USA",
            3)


        val que10 = QuestionClass(10,
            "What country flag is this", R.drawable.india,
            "India",
            "Iran",
            "Ireland",
            "USA",
            1)


        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)

        return questionsList
    }
}