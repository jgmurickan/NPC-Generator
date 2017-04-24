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
import android.widget.Button;
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

        Button generateButton = (Button) view.findViewById(R.id.newButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveActivity(v);
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }



    public void moveActivity(View view)
    {
        Intent temp = new Intent(getActivity(), EnemyActivity.class);
        temp.putExtra("type", (String)typePicker.getItemAtPosition(typePos).toString());
        temp.putExtra("cRate", (String)challengePicker.getItemAtPosition(ratePos).toString());
        startActivity(temp);
        dismiss();
    }

    public void dismissFrag(View view)
    {
        dismiss();
    }


}
