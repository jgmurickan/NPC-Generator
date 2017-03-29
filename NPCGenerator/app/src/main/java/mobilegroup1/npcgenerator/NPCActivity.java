package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NPCActivity extends AppCompatActivity {

    private Intent intent;
    private NPC dude;

    private TextView npcName;
    private TextView npcGender;
    private TextView npcTop;
    private TextView npcBottoms;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npc);

        npcName = (TextView)findViewById(R.id.npcName);
        npcGender = (TextView)findViewById(R.id.npcGender);
        npcTop = (TextView)findViewById(R.id.npcTop);
        npcBottoms = (TextView)findViewById(R.id.npcBottoms);
        save = (Button)findViewById(R.id.saveNPC);

        intent = getIntent();

        dude = new NPC();

        if(intent.getBooleanExtra("newNPC", false)) {
            dude.setFromString(intent.getStringExtra("npcAll"));
        }

        populateViews();
    }

    private void populateViews()
    {
        npcName.setText(dude.getFirstName() + " " + dude.getLastName());
        npcGender.setText(dude.getGender());
        npcTop.setText(dude.getColor() + " " + dude.getTop());
        npcBottoms.setText(dude.getBottom());
    }

    public void saveNPC(View view)
    {
        SQLiteDatabase db = openOrCreateDatabase("NPC_Generator", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NPC(Name VARCHAR, Gender VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
        String name = dude.getFirstName() + " " + dude.getLastName();
        String top = dude.getColor() + " " + dude.getTop();
        String query = "SELECT * FROM NPC WHERE Name='" + name + "' AND Gender='" + dude.getGender() + "' AND Top='" + top + "' AND Bottoms='" + dude.getBottom() + "';";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0) {
            Toast.makeText(this, "Already saved this NPC!", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        else {
            db.execSQL("INSERT INTO NPC (Name, Gender, Top, Bottoms) VALUES ('" + name + "', '" + dude.getGender() + "', '" + top + "', '" + dude.getBottom() + "');");
            cursor = db.rawQuery("SELECT * FROM NPC WHERE Name='" + name + "' AND Gender='" + dude.getGender() + "' AND Top='" + top + "' AND Bottoms='" + dude.getBottom() + "';", null);
            if(cursor.getCount() > 0) {
                cursor.close();
                Toast.makeText(this, "Totally Saved the NPC", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }
}
