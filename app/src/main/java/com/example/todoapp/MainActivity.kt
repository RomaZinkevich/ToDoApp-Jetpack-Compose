package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.ToDoAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

data class ToDoItem(var id: Int, var userId: Int, var title: String, var completed: Boolean)


interface ApiService {
    @GET("todos/1")
    suspend fun fetchTodo1(): ToDoItem

    @GET("todos")
    suspend fun fetchTodos(): List<ToDoItem>
}

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

@Composable
fun Greeting() {
    var todoItems = remember { mutableListOf<ToDoItem>(ToDoItem(1, 100, "Whatever", true))}
    var checked = remember { mutableStateOf(false)}
    var isLoading = remember { mutableStateOf(true)}
    LaunchedEffect(Unit){
        todoItems.removeAt(0)
//        todoItems.add = RetrofitInstance.apiService.fetchTodo1()
        RetrofitInstance.apiService.fetchTodos().forEach{ el ->
            println(el)
            todoItems.add(el)
        }
        isLoading.value = false
    }
    if (isLoading.value) {
        CircularProgressIndicator()
    }
    else{
        LazyColumn() {
            items(todoItems){
                todoItem -> ToDoComposable(todoItem)
            }
        }
    }
}