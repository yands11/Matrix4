package com.matrix4.app.domain.model

sealed interface Section {
    val priority: Int
}

data object DoNow : Section {
    override val priority: Int = 1
}

data object Schedule : Section {
    override val priority: Int = 2
}

data object Delegate : Section {
    override val priority: Int = 3
}

data object Eliminate : Section {
    override val priority: Int = 1
}
