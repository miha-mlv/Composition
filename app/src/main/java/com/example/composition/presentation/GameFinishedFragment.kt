package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("_binding is null")

    private val args by navArgs<GameFinishedFragmentArgs>()
    private lateinit var gameResult: GameResult

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameResult = args.gameResult
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    retryGame()
//                }
//            })
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        bindViews()
    }

    private fun bindViews(){
        binding.gameResult = args.gameResult
        with(binding){
            emojiResult.setImageResource(getSmileResId())
//            tvRequiredAnswers.text = String.format(
//                getString(R.string.required_score),
//                gameResult.gameSettings.minCountOfRightAnswers.toString()
//            )
//            tvScoreAnswers.text = String.format(
//                getString(R.string.score_answers),
//                gameResult.countOfRightAnswers.toString()
//            )
//            tvRequiredPercentage.text = String.format(
//                getString(R.string.required_percentage),
//                gameResult.gameSettings.minPercentOfRightAnswer.toString()
//            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers().toString()
            )
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult){
        if(countOfQuestion == 0){
            0
        }else{
            ((countOfRightAnswers/countOfQuestion.toDouble())*100).toInt()
        }
    }

    private fun getSmileResId():Int{
        return if(gameResult.winner){
            R.drawable.ic_smile
        }else{
            R.drawable.ic_sad
        }
    }

//    private fun parseArgs() {
//        requireArguments().getParcelable<GameResult>(KEY_RESULT)?.let {
//            gameResult = it
//        }
//    }

    private fun retryGame() {
//        requireActivity().supportFragmentManager.popBackStack(
//            GameFragment.NAME,
//            FragmentManager.POP_BACK_STACK_INCLUSIVE
//        )
        findNavController().popBackStack()
    }

    init {

    }

    companion object {
        const val KEY_RESULT = "result"


        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULT, gameResult)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}