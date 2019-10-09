package ru.job4j.todo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewItemFrg extends Fragment {

    public static ViewItemFrg instOf(int index) {
        Bundle args = new Bundle();
        args.putInt("index", index);
        ViewItemFrg frg = new ViewItemFrg();
        frg.setArguments(args);
        return frg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, container, false);
//        int index = getActivity().getIntent().getIntExtra("index", 0);
        int index = getArguments().getInt("index", 0);
        Item item = MemStore.getStore().get(index);
        TextView name = view.findViewById(R.id.name);
        name.setText(item.getName());
        TextView desc = view.findViewById(R.id.desc);
        desc.setText(item.getDesc());
        return view;
    }
}
