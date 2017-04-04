package mobilegroup1.npcgenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by herol on 4/3/2017.
 */

public class DeleteNPCDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_npc_fragment)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do the deletion code
                                Bundle bundle = getArguments();
                                String name = bundle.getString("name", "");
                                String gender = bundle.getString("gender", "");
                                String race = bundle.getString("race", "");
                                String top = bundle.getString("top", "");
                                String bottom = bundle.getString("bottom", "");
                                File file = new File("/data/data/mobilegroup1.npcgenerator/databases/NPC_Generator");
                                if(file.exists() && !file.isDirectory()) {
                                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
                                    SQLiteStatement query = db.compileStatement("DELETE FROM NPC WHERE Name = '" + name + "' AND Race = '" + race + "' AND Top = '" + top + "' AND Bottoms = '" + bottom + "';");
                                    int num = query.executeUpdateDelete();
                                    if(num > 0) {
                                        Toast.makeText(getActivity().getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
//                                        String qy = "SELECT * FROM NPC";
//                                        Cursor cursor = db.rawQuery(qy, null);
//                                        while(cursor.moveToNext()) {
//                                            Log.d("TAG", cursor.getString(3));
//                                        }
                                        Toast.makeText(getActivity().getApplicationContext(), "NPC already deleted or never saved", Toast.LENGTH_SHORT).show();
                                    }
                                    db.close();
                                }
                                dismiss();
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dismiss();
                            }
                        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
