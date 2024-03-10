package com.example.quizapp2024.screens.titles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quizapp2024.R
import com.example.quizapp2024.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    lateinit var binding: FragmentTitleBinding
    lateinit var etName: EditText
    lateinit var btnStart: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.startBtn.setOnClickListener { v: View ->

            if (binding.etName.text!!.isNotEmpty()) {
                // takes us to the next Fragment to play game
                val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
                action.name = binding.etName.text.toString()
                findNavController().navigate(action)
            }
            else
            {
                Toast.makeText(context,"Enter your Name to Start ! ",Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }
}