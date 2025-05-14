package com.example.composition.presentation

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import com.example.composition.R
import com.example.composition.domain.entity.GameResult


interface OnOptionClickListener{

    fun onOptionClickListener(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindingRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count.toString()
    )
}

@BindingAdapter("scoreAnswers")
fun bindingScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count.toString()
    )
}

@BindingAdapter("requiredPercent")
fun bindingRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count.toString()
    )
}


@BindingAdapter("scorePercentage")
fun bindingScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult).toString()
    )
}

@BindingAdapter("getImageView")
fun bindingImageView(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

@BindingAdapter("percent", "minPercent")
fun bindProgressBar(progressBar: ProgressBar, percent: Int, minPercent: Int) {
    progressBar.setProgress(percent, true)
    progressBar.secondaryProgress = minPercent
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClickListener(textView.text.toString().toInt())
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult){
    if(countOfQuestion == 0){
        0
    }else{
        ((countOfRightAnswers/countOfQuestion.toDouble())*100).toInt()
    }
}

private fun getSmileResId(winner: Boolean):Int{
    return if(winner){
        R.drawable.ic_smile
    }else{
        R.drawable.ic_sad
    }
}