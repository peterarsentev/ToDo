package ru.job4j.todo

import java.util.*

data class Item(var name: String,
                val created: Calendar = Calendar.getInstance(),
                var done: Boolean = false,
                var desc: String = "")
