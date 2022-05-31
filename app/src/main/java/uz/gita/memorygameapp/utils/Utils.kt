package uz.gita.memorygameapp.utils

import android.view.View
import timber.log.Timber

fun <T> T.scope(block: T.() -> Unit) {
    block(this)
}

fun View.gone() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}