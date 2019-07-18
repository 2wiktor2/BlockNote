package com.wiktor.blocknotische

enum class Importance(val color : String, val importance: Int) {

    UNIMPORTANT("Gray", 1),
    POORLY_IMPORTANT("Green",2),
    MEDIUM_IMPORTANT("Yellow",3),
    VERY_IMPORTANT("Red",4)
}