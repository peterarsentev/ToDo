package ru.job4j.todo;

import java.util.List;

interface Store {
    void add(Item item);

    List<Item> getAll();

    int size();

    Item get(int index);
}
