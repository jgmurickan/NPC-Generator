package mobilegroup1.npcgenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
/**
 * Created by jobinmurickan on 4/29/17.
 */

public class DeleteEnemyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_enemy_fragment)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do the deletion code
                                Bundle bundle = getArguments();
                                String type = bundle.getString("type", "");
                                String rate = bundle.getString("rate", "");
                                String weapon = bundle.getString("weapon", "");
                                String shield = bundle.getString("shield", "");
                                String armor = bundle.getString("armor", "");
                                String health = bundle.getString("health", "");
                                String armorClass = bundle.getString("armorClass", "");
                                String experience = bundle.getString("experience", "");
                                String proficiency = bundle.getString("proficiency", "");
                                File file = new File("/data/data/mobilegroup1.npcgenerator/databases/NPC_Generator");
                                if(file.exists() && !file.isDirectory()) {
                                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
                                    SQLiteStatement query = db.compileStatement("DELETE FROM Enemy WHERE Type = '" + type + "' AND Rate = '" + rate +
                                            "' AND Weapon = '" + weapon + "';");
                                    Cursor c = db.rawQuery("SELECT * FROM Enemy;", null);
                                    while(c.moveToNext())
                                        Log.d("TAG", c.getString(0) + c.getString(1) + c.getString(2) + c.getString(3) );
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
                                        Toast.makeText(getActivity().getApplicationContext(), "Enemy already deleted or never saved", Toast.LENGTH_SHORT).show();
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
