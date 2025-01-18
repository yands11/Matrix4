package com.matrix4.app.domain.repository

import com.matrix4.app.domain.model.Task

interface MatrixRepository {

    fun getDoNowList(): List<Task>
    fun getScheduleList(): List<Task>
    fun getDelegateList(): List<Task>
    fun getEliminateList(): List<Task>

}