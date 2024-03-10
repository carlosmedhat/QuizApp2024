package com.example.quizapp2024.screens.games

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizapp2024.Constants
import com.example.quizapp2024.QuestionClass
import com.example.quizapp2024.R
import com.example.quizapp2024.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentGameBinding
    lateinit var myQuestionsList: ArrayList<QuestionClass>
    private var mySelectedPosition: Int = 0
    private var myCorrectAnswer: Int = 0
    private var myCurrentPosition: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        // we have created a new arrayList and stored all our Questions and answer in it
        myQuestionsList = Constants.getQuestions()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        setQuestions()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions() {
        var question: QuestionClass = myQuestionsList[myCurrentPosition - 1]
        binding.tvQuestion.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = myCurrentPosition
        binding.tvProgress.text =
            "$myCurrentPosition" + "/" + binding.pb.max //display Question number

        // This resets the appearance for everytime a new question comes up
        defaultAppearance()

        //if all questions in the List are used
        if (myCurrentPosition == myQuestionsList.size) {
            binding.btnSubmit.text = "Quiz Finish"
        } else {
            binding.btnSubmit.text = "Submit"
        }
    }

    private fun defaultAppearance() {
        // controlling textView that share same behaviour
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            //default appearance
            option.typeface = Typeface.DEFAULT
            option.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.default_option_border_bg) }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optionOne -> {
                selectOptionView(binding.tvOptionOne, 1)
            }

            R.id.tv_optionTwo -> {
                selectOptionView(binding.tvOptionTwo, 2)
            }

            R.id.tv_optionThree -> {
                selectOptionView(binding.tvOptionThree, 3)
            }

            R.id.tv_optionFour -> {
                selectOptionView(binding.tvOptionFour, 4)
            }

            R.id.btnSubmit -> {
                // if user has not selected any Option
                if (mySelectedPosition == 0) {
                    myCurrentPosition++     // When current position is increasing means index is increasing
                    // if index is increasing that means we will get a new question from the question list
                    when {
                        myCurrentPosition <= myQuestionsList.size -> {
                            setQuestions()
                        }

                        else -> {
                            // go to Result
                            val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
                            val nameOfPlayer by navArgs<GameFragmentArgs>()
                            action.score = myCorrectAnswer
                            action.name = nameOfPlayer.name
                            findNavController().navigate(action)
                            Toast.makeText(context, "Quiz Finished !", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // if user selected an Option
                    // we will check if it's option is correct or inCorrect
                    val question: QuestionClass = myQuestionsList[myCurrentPosition - 1]

                    // if selected position 1..4 matches value at the correct answer
                    // correct answer values are 1..4 if the value matches or not matches
                    if (question.correctAnswer != mySelectedPosition) {
                        answerView(mySelectedPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        myCorrectAnswer++

                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                        if (myCurrentPosition == myQuestionsList.size) {
                            binding.btnSubmit.text = "Finished"
                        } else {
                            binding.btnSubmit.text = "Go to Next question"
                        }


                    }

                    mySelectedPosition = 0 // next question Options position should be zero
                }
            }
        }
    }

    private fun selectOptionView(tv: TextView, selectedPosition: Int) {
        // Reset text views
        defaultAppearance()
        mySelectedPosition = selectedPosition

        tv.setTextColor(Color.parseColor("#363A43"))
        //default appearance
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }

    }

    private fun answerView(mySelectedPosition: Int, drawableView: Int) {
        when (mySelectedPosition) {
            1 -> {
                binding.tvOptionOne.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            2 -> {
                binding.tvOptionTwo.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            3 -> {
                binding.tvOptionThree.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            4 -> {
                binding.tvOptionFour.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

        }
    }

}