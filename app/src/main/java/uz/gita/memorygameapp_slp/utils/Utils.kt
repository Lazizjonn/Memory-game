package uz.gita.memorygameapp_slp.utils

import android.view.View

fun <T> T.scope(block: T.() -> Unit) {
    block(this)
}

fun View.gone() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}