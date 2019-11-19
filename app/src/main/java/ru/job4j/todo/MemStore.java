package ru.job4j.todo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class MemStore implements IStore {

    private static IStore instance;
    private int counter = 0;
    private Context context;

    private MemStore(Context context) {
       this.context = context;
    }

    public static IStore getInstance(Context context) {
        if (instance == null) {
            instance = new MemStore(context);
        }
        return instance;
    }

    @Override
    public void add(Item item) {
        File file = new File(context.getFilesDir(), (counter++) + ".txt");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            out.println(item.getName());
            out.println(item.getCreated().getTimeInMillis());
            out.println(item.isDone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return context.getFilesDir().listFiles().length;
    }

    @Override
    public Item get(int index) {
        Item item = new Item(null, null);
        File file = new File(context.getFilesDir(), index + ".txt");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            item.setName(in.readLine());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(in.readLine()));
            item.setCreated(cal);
            item.setDone(Boolean.parseBoolean(in.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
