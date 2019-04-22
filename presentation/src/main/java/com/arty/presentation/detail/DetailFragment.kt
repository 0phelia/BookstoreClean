package com.arty.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.arty.presentation.CLICKED_BOOK
import com.arty.presentation.R
import com.arty.presentation.di.ViewModelFactory
import com.arty.presentation.model.BookView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class DetailFragment : AnimatedBaseFragment() {

    // ViewModel stuff
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailViewModel: DetailViewModel

    // UI stuff
    private lateinit var binding: com.arty.presentation.databinding.FragmentDetailBinding
    private lateinit var book: BookView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        book = arguments?.getParcelable(CLICKED_BOOK)!!

        TransitionInflater.from(this.context).inflateTransition(R.transition.book_cover_simple_transition).let {
            this.sharedElementReturnTransition = null
            this.sharedElementEnterTransition = it
            this.allowEnterTransitionOverlap = true
        }

        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        detailViewModel.getBook(book.id)
        detailViewModel.liveData.observe(this, Observer { bookRes ->
            bookRes?.data?.let {
                binding.bookDetailView = it
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.apply {
            lifecycleOwner = this@DetailFragment
            viewModel = detailViewModel
            simpleTransitionName = book.id
            bookCoverUrl = book.coverImage
        }

        binding.btnLikeBook.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(activity, R.anim.anim_btn_bounce)
            binding.btnLikeBook.startAnimation(anim)
            detailViewModel.likeBook(book.id, binding.btnLikeBook.isChecked)
        }

        return binding.root
    }

    override var viewsToSlideFromRight: List<Int> = listOf()
        get() = listOf(R.id.tv_book_title, R.id.tv_book_author, R.id.tv_book_price)

    override var viewsToFade: List<Int> = listOf()
        get() = listOf(R.id.tv_book_pages_count, R.id.btn_buy_book, R.id.btn_like_book, R.id.scroll_description_holder)

    override var viewsToSlideFromBottom: List<Int> = listOf()
        get() = listOf(R.id.scroll_description_holder)

}
