package com.example.metal_detector;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Alert_Dialog extends AppCompatDialogFragment {
    private EditText alert_adjusting;
    private Alert_DialogListener listener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view  = inflater.inflate(R.layout.layout_alert_adjust, null);

        builder.setView(view)
                .setTitle("경고 수치 설정")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String adjusted_rate = alert_adjusting.getText().toString();
                        listener.applyTexts(adjusted_rate);
                    }
                });
        alert_adjusting = view.findViewById(R.id.adjusting);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (Alert_DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement bitch");
        }
    }

    public interface Alert_DialogListener {
        void applyTexts(String adjusted_rate);
    }
}
