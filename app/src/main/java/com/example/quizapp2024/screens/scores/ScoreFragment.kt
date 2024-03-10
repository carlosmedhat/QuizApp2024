package com.example.quizapp2024.screens.scores

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizapp2024.R
import com.example.quizapp2024.databinding.FragmentGameBinding
import com.example.quizapp2024.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    lateinit var binding: FragmentScoreBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val scoreArgs by navArgs<ScoreFragmentArgs>()
        binding.displayResult.text =
            "${scoreArgs.name} ! you have scored ${scoreArgs.score} points out of 10 "


        binding.playAgain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            // handle back event
            view?.findNavController()?.navigate(R.id.action_scoreFragment_to_titleFragment)

        }

        return binding.root
    }
}