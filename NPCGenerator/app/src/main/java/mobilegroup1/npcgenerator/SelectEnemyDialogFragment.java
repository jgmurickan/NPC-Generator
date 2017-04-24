package mobilegroup1.npcgenerator;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by herol on 4/24/2017.
 */

public class SelectEnemyDialogFragment extends DialogFragment {

    Spinner typePicker;
    Spinner challengePicker;
    int typePos = 0;
    int ratePos = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.type_picker_layout, null);

        builder.setTitle("Select Your Enemy").setView(view)
            .setPositiveButton("Generate The Enemy",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveActivity();
                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dismiss();
                                }
                            });

        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.type_picker_layout, null);
        typePicker = (Spinner) view.findViewById(R.id.typePicker);
        typePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
                typePos = i;
            }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
        challengePicker = (Spinner) view.findViewById(R.id.challengePicker);
        challengePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
                ratePos = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        return view;
    }



    private void moveActivity()
    {

        Intent temp = new Intent(getActivity(), EnemyActivity.class);
        temp.putExtra("type", (String)typePicker.getItemAtPosition(typePos).toString());
        temp.putExtra("cRate", (String)challengePicker.getItemAtPosition(ratePos).toString());
        startActivity(temp);
        dismiss();
    }
}
