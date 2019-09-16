package ru.job4j.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import static ru.job4j.todo.DateFrm.format;

public class AddItemFrg extends Fragment {
    private Button deadline;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);
        Button saved = view.findViewById(R.id.add);
        saved.setOnClickListener(btn -> save(view));
        deadline = view.findViewById(R.id.date);
        FragmentManager fm = getFragmentManager();
        deadline.setOnClickListener(
                btn -> {
                    DatePickerFrg dialog = new DatePickerFrg();
                    dialog.setTargetFragment(AddItemFrg.this, 0);
                    dialog.show(fm, "date");
                }
        );
        return view;
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        if (result == 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getLongExtra("deadline", 0));
            deadline.setText(format(cal));
        }
    }

    public void save(View view) {
        EditText edit = view.findViewById(R.id.name);
        EditText desc = view.findViewById(R.id.desc);
        Item item = new Item(edit.getText().toString(), Calendar.getInstance());
        item.setDesc(desc.getText().toString());
        Store.getStore().add(item);
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        startActivity(intent);
    }
}
