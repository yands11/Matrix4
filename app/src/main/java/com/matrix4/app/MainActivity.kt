package com.matrix4.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix4.app.domain.model.Delegate
import com.matrix4.app.domain.model.DoNow
import com.matrix4.app.domain.model.Eliminate
import com.matrix4.app.domain.model.Schedule
import com.matrix4.app.domain.model.Task
import com.matrix4.app.domain.model.TaskStatus
import com.matrix4.app.ui.theme.Matrix4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Matrix4Theme {
                val doNowTasks = listOf(
                    Task(
                        DoNow,
                        TaskStatus.IN_PROGRESS,
                        "Complete report Complete reportss Complete reportComplete reportj"
                    ),
                    Task(DoNow, TaskStatus.DONE, "Prepare presentation")
                )
                val scheduleTasks = listOf(
                    Task(Schedule, TaskStatus.NOT_YET, "Plan vacation"),
                    Task(Schedule, TaskStatus.DONE, "Doctor appointment")
                )
                val delegateTasks = listOf(
                    Task(Delegate, TaskStatus.NOT_YET, "Send emails"),
                    Task(Delegate, TaskStatus.DONE, "Follow up with team")
                )
                val eliminateTasks = listOf(
                    Task(Eliminate, TaskStatus.NOT_YET, "Scroll social media"),
                    Task(Eliminate, TaskStatus.DONE, "Watch unnecessary videos")
                )

                EisenhowerMatrix(
                    doNowTasks = doNowTasks,
                    scheduleTasks = scheduleTasks,
                    delegateTasks = delegateTasks,
                    eliminateTasks = eliminateTasks
                )
            }
        }
    }
}

@Composable
fun EisenhowerMatrix(
    doNowTasks: List<Task>,
    scheduleTasks: List<Task>,
    delegateTasks: List<Task>,
    eliminateTasks: List<Task>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Do Now
                TaskSection(
                    title = "Do Now",
                    tasks = doNowTasks,
                    textColor = Color(0xFF663333),
                    backgroundColor = Color(0xFFFFD9D9),
                    modifier = Modifier.weight(1f)
                )
                // Schedule
                TaskSection(
                    title = "Schedule",
                    tasks = scheduleTasks,
                    textColor = Color(0xFF334466),
                    backgroundColor = Color(0xFFD9EAFD),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Delegate
                TaskSection(
                    title = "Delegate",
                    tasks = delegateTasks,
                    textColor = Color(0xFF336644),
                    backgroundColor = Color(0xFFD9FDE2),
                    modifier = Modifier.weight(1f)
                )
                // Eliminate
                TaskSection(
                    title = "Eliminate",
                    tasks = eliminateTasks,
                    textColor = Color(0xFF665533),
                    backgroundColor = Color(0xFFFDEFD9),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun TaskSection(
    title: String,
    tasks: List<Task>,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = task.content,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = when (task.status) {
                TaskStatus.DONE -> "✅"
                TaskStatus.IN_PROGRESS -> "\uD83D\uDEA7"
                TaskStatus.NOT_YET -> "❌"
            },
            color = if (task.status == TaskStatus.DONE) Color.Green else Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EisenhowerMatrixPreview() {
    val doNowTasks = listOf(
        Task(
            DoNow,
            TaskStatus.IN_PROGRESS,
            "Complete report Complete reportss Complete reportComplete reportj"
        ),
        Task(DoNow, TaskStatus.DONE, "Prepare presentation")
    )
    val scheduleTasks = listOf(
        Task(Schedule, TaskStatus.NOT_YET, "Plan vacation"),
        Task(Schedule, TaskStatus.DONE, "Doctor appointment")
    )
    val delegateTasks = listOf(
        Task(Delegate, TaskStatus.NOT_YET, "Send emails"),
        Task(Delegate, TaskStatus.DONE, "Follow up with team")
    )
    val eliminateTasks = listOf(
        Task(Eliminate, TaskStatus.NOT_YET, "Scroll social media"),
        Task(Eliminate, TaskStatus.DONE, "Watch unnecessary videos")
    )

    EisenhowerMatrix(
        doNowTasks = doNowTasks,
        scheduleTasks = scheduleTasks,
        delegateTasks = delegateTasks,
        eliminateTasks = eliminateTasks
    )
}


