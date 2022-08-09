package com.bignerdranch.android.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.navigationcomponent.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment:Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        // Получаем
        val color = requireArguments().getInt(ARG_COLOR)
        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            // Закрываем экран
            findNavController().popBackStack()
        }

        binding.openSecretButton.setOnClickListener {
            findNavController().navigate(R.id.action_boxFragment_to_secretFragment)
        }

        binding.generateNumberButton.setOnClickListener {
            val number = Random.nextInt()
            // Возвращаем число другому фрагменту силами Navigation Component
            //findNavController().previousBackStackEntry?.savedStateHandle?.set(EXTRA_RANDOM_NUMBER,number)
            // Возвращаем число другому фрагменту силами Fragment Result Api
            parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(EXTRA_RANDOM_NUMBER to number))

            findNavController().popBackStack()
        }

    }

    companion object{
        const val ARG_COLOR = "color"
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
        const val REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"
    }

}