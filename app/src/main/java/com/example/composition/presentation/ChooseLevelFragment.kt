package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level
import com.example.composition.presentation.GameFragment.Companion.KEY_LEVEL


class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("_binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonLevelEasy.setOnClickListener {
            launchGame(it.id, Level.EASY)
        }
        binding.buttonLevelNormal.setOnClickListener {
            launchGame(it.id, Level.NORMAL)
        }
        binding.buttonLevelHard.setOnClickListener {
            launchGame(it.id, Level.HARD)
        }
        binding.buttonLevelTest.setOnClickListener {
            launchGame(it.id, Level.TEST)
        }
    }

    init {

    }

    private fun launchGame(btnId: Int, level: Level) {
//        when(btnId){
//            binding.buttonLevelEasy.id -> {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, GameFragment.newInstance(Level.EASY))
//                    .addToBackStack(GameFragment.NAME)
//                    .commit()
//            }
//            binding.buttonLevelNormal.id -> {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, GameFragment.newInstance(Level.NORMAL))
//                    .addToBackStack(GameFragment.NAME)
//                    .commit()
//            }
//            binding.buttonLevelHard.id -> {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, GameFragment.newInstance(Level.HARD))
//                    .addToBackStack(GameFragment.NAME)
//                    .commit()
//            }
//            binding.buttonLevelTest.id -> {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, GameFragment.newInstance(Level.TEST))
//                    .addToBackStack(GameFragment.NAME)
//                    .commit()
//            }
//        }

        findNavController().navigate(
            ChooseLevelFragmentDirections
                .actionChooseLevelFragmentToGameFragment(level)
        )

    }

    companion object {
        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}