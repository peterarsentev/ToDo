package ru.job4j.todo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ItemsFrg extends Fragment {
    private Callbacks call;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Cursor cursor = this.getActivity().getContentResolver()
                .query(StoreContentProvider.CONTENT_URI, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d("ContentProvider", cursor.getString(1));
        }
        View view = inflater.inflate(R.layout.activity_main, container, false);
        Button added = view.findViewById(R.id.add);
        added.setOnClickListener(this::add);
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter = new ItemAdapter(call, this.getContext());
        recycler.setAdapter(adapter);
        return view;
    }


    public void add(View view) {
        Intent intent = new Intent(this.getActivity(), AddItemActivity.class);
        startActivity(intent);
    }

    public interface Callbacks {
        void onItemSelected(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        call = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        call = null;
    }
}
