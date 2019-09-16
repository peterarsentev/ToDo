package ru.job4j.todo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
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
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(context, ItemActivity.class);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
                }
        );
        TextView name = holder.itemView.findViewById(R.id.name);
        TextView created = holder.itemView.findViewById(R.id.created);
        Item item = Store.getStore().get(index);
        name.setText(String.format("%s. %s", index, item.getName()));
        created.setText(format(item.getCreated()));
        CheckBox done = holder.itemView.findViewById(R.id.done);
        done.setOnCheckedChangeListener((view, checked) -> item.setDone(checked));
    }

    private String format(Calendar cal) {
        return String.format(
                Locale.getDefault(), "%02d.%02d.%d",
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)
        );
    }

    @Override
    public int getItemCount() {
        return Store.getStore().size();
    }


}
