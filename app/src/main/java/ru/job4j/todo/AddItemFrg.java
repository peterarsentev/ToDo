package ru.job4j.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddItemFrg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);
        Button saved = view.findViewById(R.id.add);
        saved.setOnClickListener(btn -> save(view));
        return view;
    }

    public void save(View view) {
        EditText edit = view.findViewById(R.id.name);
        EditText desc = view.findViewById(R.id.desc);
        Item item = new Item(edit.getText().toString(), Calendar.getInstance(), false, "");
        item.setDesc(desc.getText().toString());
        Store.INSTANCE.add(item);
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        startActivity(intent);
    }
}
