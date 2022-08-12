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
            openBox(Color.rgb(255,255,200),getString(R.string.yellow))
        }
        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200,255,200),"Green")
        }

        // Принимаем возвращённое число из другого фрагмента силами Navigation Component
        val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner){randomNumber ->
            if(randomNumber!=null){
                Toast.makeText(
                    requireContext(),
                    "Generated number: $randomNumber",
                    Toast.LENGTH_SHORT
                ).show()
                liveData.value = null
            }
        }

        // Было
        //findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)?.observe(viewLifecycleOwner){randomNumber ->
        //          Toast.makeText(requireContext(), "Generated number: $randomNumber", Toast.LENGTH_SHORT).show()
        //}

        // Принимаем возвращённое число из другого фрагмента силами Fragment Result Api
        //parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE,viewLifecycleOwner){ _, data ->
            //val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            //Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
        //}
    }


    private fun openBox(color:Int,colorName:String){

        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(colorName,color)

        // Так осуществляется запуск следующего экрана с помощью Navigation component
        // и отправка данных через дирэкшен
        findNavController().navigate(direction)
        // Так было
        //findNavController().navigate(R.id.action_rootFragment_to_boxFragment, bundleOf(BoxFragment.ARG_COLOR to color,BoxFragment.ARG_COLOR_NAME to colorName))
    }
}
