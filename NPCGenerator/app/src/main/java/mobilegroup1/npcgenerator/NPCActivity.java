package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NPCActivity extends AppCompatActivity {

    private Intent intent;
    private NPC dude;

    private TextView npcName;
    private TextView npcGender;
    private TextView npcRace;
    private TextView npcTop;
    private TextView npcBottoms;
    private boolean newNPC;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npc);

        npcName = (TextView)findViewById(R.id.npcName);
        npcGender = (TextView)findViewById(R.id.npcGender);
        npcRace = (TextView)findViewById(R.id.npcRace);
        npcTop = (TextView)findViewById(R.id.npcTop);
        npcBottoms = (TextView)findViewById(R.id.npcBottoms);
        save = (Button)findViewById(R.id.saveNPC);

        intent = getIntent();

        dude = new NPC();

        newNPC = intent.getBooleanExtra("newNPC", true);

        if(newNPC == false) {
            dude.setFromString(intent.getStringExtra("npcAll"));
        }

        populateViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.npc_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.redoNPC:
                makeAnother();
                return true;
            case R.id.saveNPC:
                saveNPC(null);
                return true;
            case R.id.deleteNPC:
                Bundle bundle = new Bundle();
                bundle.putString("name", dude.getFirstName() + " " + dude.getLastName());
                bundle.putString("gender", dude.getGender());
                bundle.putString("race", dude.getRace());
                bundle.putString("top", dude.getColor() + " " + dude.getTop());
                bundle.putString("bottom", dude.getBottom());
                DeleteNPCDialogFragment deleteFrag = new DeleteNPCDialogFragment();
                deleteFrag.setArguments(bundle);
                deleteFrag.show(fm, "Deleting");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateViews()
    {
        npcName.setText(dude.getFirstName() + " " + dude.getLastName());
        npcGender.setText(dude.getGender());
        npcRace.setText(dude.getRace());
        npcTop.setText(dude.getColor() + " " + dude.getTop());
        npcBottoms.setText(dude.getBottom());
    }

    public void saveNPC(View view)
    {
        SQLiteDatabase db = openOrCreateDatabase("NPC_Generator", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NPC(Name VARCHAR, Gender VARCHAR, Race VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
        String name = dude.getFirstName() + " " + dude.getLastName();
        String top = dude.getColor() + " " + dude.getTop();
        String race = dude.getRace();
        String query = "SELECT * FROM NPC WHERE Name='" + name + "' AND Gender='" + dude.getGender() + "' AND Race='" + race + "' AND Top='" + top + "' AND Bottoms='" + dude.getBottom() + "';";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0 || newNPC == false) {
            Toast.makeText(this, "Already saved this NPC!", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        else {
            db.execSQL("INSERT INTO NPC (Name, Gender, Race, Top, Bottoms) VALUES ('" + name + "', '" + dude.getGender() + "', '" + race + "', '" + top + "', '" + dude.getBottom() + "');");
            cursor = db.rawQuery("SELECT * FROM NPC WHERE Name='" + name + "' AND Gender='" + dude.getGender() + "' AND Race='" + race + "' AND Top='" + top + "' AND Bottoms='" + dude.getBottom() + "';", null);
            if(cursor.getCount() > 0) {
                cursor.close();
                Toast.makeText(this, "Your NPC has been saved.", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    public void makeAnother()
    {
        dude = new NPC();
        populateViews();
        newNPC = true;
    }
}
