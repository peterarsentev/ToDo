package ru.job4j.todo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import javax.inject.Inject;

import static ru.job4j.todo.DateFrm.format;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Inject
    Store store;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        App.getContext().injects(this);
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        TextView name = holder.itemView.findViewById(R.id.name);
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(name.getContext(), ViewItemActivity.class);
                    intent.putExtra("index", index);
                    name.getContext().startActivity(intent);
                }
        );
        TextView created = holder.itemView.findViewById(R.id.created);
        Item item = store.get(index);
        name.setText(String.format("%s. %s", index, item.getName()));
        created.setText(format(item.getCreated()));
        CheckBox done = holder.itemView.findViewById(R.id.done);
        done.setOnCheckedChangeListener((view, checked) -> item.setDone(checked));
    }



    @Override
    public int getItemCount() {
        return MemStore.getStore().size();
    }
}
