package uz.gita.memorygameapp.utils

import android.view.View
import timber.log.Timber

fun <T> T.scope(block: T.() -> Unit) {
    block(this)
}

fun myLog(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun View.gone() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}