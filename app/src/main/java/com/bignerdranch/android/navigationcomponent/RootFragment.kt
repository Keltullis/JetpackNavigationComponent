package com.bignerdranch.android.navigationcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.navigationcomponent.databinding.FragmentRootBinding

class RootFragment:Fragment(R.layout.fragment_root) {

    private lateinit var binding:FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255,255,200))
        }
        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200,255,200))
        }

        // Принимаем возвращённое число из другого фрагмента силами Navigation Component
        //findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)?.observe(viewLifecycleOwner){
            //Toast.makeText(requireContext(), "Generated number: $it", Toast.LENGTH_SHORT).show()
        //}

        parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE,viewLifecycleOwner){ _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
        }
    }


    private fun openBox(color:Int){
        // Так осуществляется запуск следующего экрана с помощью Navigation component
        findNavController().navigate(R.id.action_rootFragment_to_boxFragment, bundleOf(BoxFragment.ARG_COLOR to color))
    }
}