package ru.job4j.todo;

interface IStore {
    void add(Item item);

    int size();

    Item get(int index);
}
