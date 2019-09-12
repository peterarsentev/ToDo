package ru.job4j.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.item);
        int index = getIntent().getIntExtra("index", 0);
        Item item = Store.getStore().get(index);
        TextView name = findViewById(R.id.name);
        name.setText(item.getName());
        TextView desc = findViewById(R.id.desc);
        desc.setText(item.getDesc());
    }
}
