package ru.job4j.todo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class AddItemFrg extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView photo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);
        Button saved = view.findViewById(R.id.add);
        saved.setOnClickListener(btn -> save(view));
        photo = view.findViewById(R.id.photo);
        photo.setOnClickListener(btn -> {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }
        );
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
    }

    public void save(View view) {
        EditText edit = view.findViewById(R.id.name);
        EditText desc = view.findViewById(R.id.desc);
        Item item = new Item(edit.getText().toString(), Calendar.getInstance());
        item.setDesc(desc.getText().toString());
        FileStore.getInstance(this.getContext()).add(item);
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        startActivity(intent);
    }


}
