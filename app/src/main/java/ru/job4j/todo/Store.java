package ru.job4j.todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Store {
    private static final Store INST = new Store();

    private final List<Item> items = new ArrayList<>();

    private Store() {
        items.add(new Item("text", Calendar.getInstance()));
    }

    public static Store getStore() {
        return INST;
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public List<Item> getAll() {
        return this.items;
    }

    public int size() {
        return this.items.size();
    }

    public Item get(int index) {
        return this.items.get(index);
    }
}
