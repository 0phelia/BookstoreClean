package com.arty.presentation

import android.os.Bundle
import android.view.View
import com.arty.presentation.browse.BrowseFragment
import com.arty.presentation.detail.DetailFragment
import com.arty.presentation.model.BookView

const val CLICKED_BOOK = "CLICKED_BOOK"

class HomeActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView(savedInstanceState)
    }

    private fun setupView(savedInstanceState: Bundle?) {
        if (findViewById<View>(R.id.home_fragment_container) != null) {
            if (savedInstanceState != null) return
            openBrowseFragment()
        }
    }

    fun openBrowseFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_fragment_container, BrowseFragment().apply { arguments = intent.extras })
            .commit()
    }

    fun openDetailFragment(book: BookView, sharedView: View) {
        animateHamburgerIcon(true)

        val detailArg = Bundle().apply {
            putParcelable(CLICKED_BOOK, book)
        }

        supportFragmentManager.beginTransaction()
            .addSharedElement(sharedView, sharedView.transitionName)
            .addToBackStack(null)
            .replace(R.id.home_fragment_container, DetailFragment().apply { arguments = detailArg})
            .commit()
    }

}
