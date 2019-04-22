package com.arty.presentation.browse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.arty.presentation.HomeActivity
import com.arty.presentation.R
import com.arty.presentation.databinding.FragmentBrowseBinding
import com.arty.presentation.di.ViewModelFactory
import com.arty.presentation.model.BookView
import com.arty.presentation.state.Resource
import com.arty.presentation.state.ResourceState
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BrowseFragment : Fragment() {

    // ViewModel stuff
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseBooksViewModel: BrowseBooksViewModel

    // UI stuff
    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var adapter: BrowseListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        returnTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.no_transition)
        browseBooksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowseBooksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: com.arty.presentation.databinding.FragmentBrowseBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_browse, container, false)
        setupBinding(binding)
        setupAdapter(binding)
        setupRecyclerView(binding)
        subscribeUi()
        return binding.root
    }

    private fun setupBinding(binding: FragmentBrowseBinding) {
        binding.apply {
            viewmodel = browseBooksViewModel
            lifecycleOwner = this@BrowseFragment
        }
    }

    private fun setupRecyclerView(binding: FragmentBrowseBinding) {
        lm = GridLayoutManager(this.context, 3)
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = lm
    }

    private fun setupAdapter(binding: FragmentBrowseBinding) {
        adapter = BrowseListAdapter(
            View.OnClickListener { clickedView ->
                val itemPosition = binding.rvBooks.getChildLayoutPosition(clickedView)
                val sharedView = (lm.findViewByPosition(itemPosition) as ConstraintLayout).getChildAt(0)
                val bookItem = adapter.getItem(itemPosition)
                (activity as HomeActivity).openDetailFragment(bookItem, sharedView)
            }
        )
    }

    private fun subscribeUi() {
        browseBooksViewModel.liveData.observe(this, Observer { booksRes ->
            when (booksRes.status) {
                ResourceState.SUCCESS -> updateBooks(booksRes.data)
                ResourceState.ERROR -> showErrorSnackBar(booksRes)
                else -> { /* do nothing */ }
            }
        })
        browseBooksViewModel.fetchBooks()
    }

    private fun showErrorSnackBar(booksRes: Resource<List<BookView>>) {
        Snackbar.make(
            this.view!!,
            booksRes.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun updateBooks(data: List<BookView>?) {
        if (data?.isNotEmpty() == true) adapter.updateBooks(data)
    }

    /**
     *      set up toolbar elevation animation (see animator/toolbar_anim.xml)
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.rv_books)
        val tb = activity!!.findViewById<Toolbar>(R.id.toolbar)
        rv.viewTreeObserver.addOnScrollChangedListener {
            tb?.isSelected = rv.canScrollVertically(-1)
        }
    }

}
