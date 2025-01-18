package com.matrix4.app.domain.model

data class Task(
    val section: Section = DoNow,
    val status: TaskStatus = TaskStatus.NOT_YET,
    val content: String = "",
)

enum class TaskStatus {
    NOT_YET, IN_PROGRESS, DONE;
}
