package com.example.repositorylistapp.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String?) {
    Glide.with(context)
            .load(uri)
            .into(this)

}

@BindingAdapter("android:imageUrl")
fun loadImageView(view: ImageView, url: String?) {
    view.loadImage(url)
}


@BindingAdapter("android:visibility")
fun changeVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}

 fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}