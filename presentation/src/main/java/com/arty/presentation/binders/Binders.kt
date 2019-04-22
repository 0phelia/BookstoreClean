package com.arty.presentation.binders

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arty.presentation.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("customImageUrl")
fun loadCustomImageUrl(imageView: ImageView, url: String?) {
    val options = RequestOptions().dontTransform()
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.book_cover_placeholder)
        .apply(options)
        .into(imageView)
}

@BindingAdapter("customViewVisible")
fun setCustomViewVisible(view: View, isVisible: Boolean?) {
    view.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}
