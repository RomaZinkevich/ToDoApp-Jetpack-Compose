package com.example.todoapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun ToDoComposable(item: ToDoItem) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = "${item.id}. ${item.title} ${item.completed}",
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            style = if (item.completed) {
                TextStyle(
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
            } else {
                LocalTextStyle.current
            }
        )

        // You can customize the appearance of the checkbox based on completion status
        Checkbox(
            checked = item.completed,
            onCheckedChange = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}