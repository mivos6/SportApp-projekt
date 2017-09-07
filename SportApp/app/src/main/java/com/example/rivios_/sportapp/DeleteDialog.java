package com.example.rivios_.sportapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Milan on 7.9.2017..
 */

public class DeleteDialog extends DialogFragment {

    public interface DeleteDialogListener {
        public void onDialogClick(boolean yesNo);
    }

    DeleteDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Želite li obrisati odabeanu stavku?")
                .setNegativeButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogClick(true);
                    }
                })
                .setPositiveButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogClick(false);
                    }
                });

        return builder.create();
    }

    public void setListener (DeleteDialogListener listener) {
        this.listener = listener;
    }
}
