package uz.gita.memorygameapp_slp.data.model

import java.io.Serializable

enum class LevelEnum(val x: Int, val y: Int): Serializable{
    EASY(3, 4),
    MEDIUM(4, 6),
    HARD(6, 8)
}