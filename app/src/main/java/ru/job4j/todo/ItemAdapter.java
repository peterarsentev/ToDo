package ru.job4j.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import static ru.job4j.todo.DateFrm.format;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ItemsFrg.Callbacks call;

    public ItemAdapter(ItemsFrg.Callbacks call) {
        this.call = call;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        Item item = Store.INSTANCE.get(index);
        TextView name = holder.itemView.findViewById(R.id.name);
        holder.itemView.setOnClickListener(
                view -> call.onItemSelected(index)
        );
        TextView created = holder.itemView.findViewById(R.id.created);
        name.setText(String.format("%s. %s", index, item.getName()));
        created.setText(format(item.getCreated()));
        CheckBox done = holder.itemView.findViewById(R.id.done);
        done.setOnCheckedChangeListener((view, checked) -> item.setDone(checked));
    }



    @Override
    public int getItemCount() {
        return Store.INSTANCE.size();
    }
}
