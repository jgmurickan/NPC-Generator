package mobilegroup1.npcgenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

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
