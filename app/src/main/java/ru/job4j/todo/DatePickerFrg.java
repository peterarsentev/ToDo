package ru.job4j.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.GregorianCalendar;

public class DatePickerFrg extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle saved) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);
        DatePicker picker = view.findViewById(R.id.dialog_date_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok,
                        (d, w) -> {
                            Intent intent = new Intent();
                            GregorianCalendar cal = new GregorianCalendar(
                                    picker.getYear(),
                                    picker.getMonth(),
                                    picker.getDayOfMonth()
                            );
                            intent.putExtra("deadline", cal.getTimeInMillis());
                            getTargetFragment().onActivityResult(
                                    getTargetRequestCode(), 0, intent
                            );
                        }
                )
                .create();
    }
}
