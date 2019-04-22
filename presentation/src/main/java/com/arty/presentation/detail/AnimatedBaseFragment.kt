package com.arty.presentation.detail

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet

open class AnimatedBaseFragment: Fragment() {

    val transitionSet = TransitionSet()

    open var viewsToSlideFromRight = listOf<Int>()
    open var viewsToSlideFromBottom = listOf<Int>()
    open var viewsToFade = listOf<Int>()

    private fun setupRightSliding(views: List<Int>) {
        views.forEachIndexed {i, v ->
            with(Slide(Gravity.END)) {
                startDelay = 50L * i
                addTarget(v)
                transitionSet.addTransition(this)
            }
        }
    }

    private fun setupFade(views: List<Int>) {
        views.forEachIndexed { _, v ->
            with(Fade()) {
                startDelay = 100L
                addTarget(v)
                transitionSet.addTransition(this)
            }
        }
    }

    private fun setupBottomSlide(views: List<Int>) {
        views.forEachIndexed { _, v ->
            with (Slide(Gravity.BOTTOM)) {
                addTarget(v)
                transitionSet.addTransition(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRightSliding(viewsToSlideFromRight)
        setupBottomSlide(viewsToSlideFromBottom)
        setupFade(viewsToFade)

        // explicitly prohibit return animation
        returnTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.no_transition)
        enterTransition = transitionSet
    }

}