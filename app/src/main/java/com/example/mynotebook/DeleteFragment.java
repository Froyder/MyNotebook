package com.example.mynotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DeleteFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final View contentView = requireActivity().getLayoutInflater().inflate(R.layout.delete_dialog, null);
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.delete_dialog)
                .setView(contentView)

                .setNegativeButton(R.string.nope,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "Заметка не будет удалена", Toast.LENGTH_SHORT).show();
                                TextFragment fragment = new TextFragment();
                                fragment.dontDelete();
                                dismiss();
                            }
                        })

                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextFragment fragment = new TextFragment();
                        fragment.doDelete();
                        Toast.makeText(getContext(), "Заметка была удалена", Toast.LENGTH_SHORT).show();

                        FragmentManager fM = getParentFragmentManager();
                        fM.beginTransaction()
                                .replace(R.id.container, new ListFragment())
                                .addToBackStack(null)
                                .commit();
                        //((MainActivity) requireActivity()).onDialogResult(answer);
                    }
                });
        return builder.create();
    }
}