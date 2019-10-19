package ru.job4j.todo

import java.util.ArrayList
import java.util.Calendar

object Store {

    private val items = ArrayList<Item>()

    init {
        items.add(Item("text"))
    }

    fun getAll() = items

    fun add(item: Item) = this.items.add(item)

    fun size() = this.items.size

    fun get(index: Int) = this.items[index]
}
