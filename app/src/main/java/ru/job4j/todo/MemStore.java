package ru.job4j.todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemStore implements Store {
    private static final Store INST = new MemStore();

    private final List<Item> items = new ArrayList<>();

    private MemStore() {
        items.add(new Item("text", Calendar.getInstance()));
    }

    public static Store getStore() {
        return INST;
    }

    @Override
    public void add(Item item) {
        this.items.add(item);
    }

    @Override
    public List<Item> getAll() {
        return this.items;
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public Item get(int index) {
        return this.items.get(index);
    }
}
