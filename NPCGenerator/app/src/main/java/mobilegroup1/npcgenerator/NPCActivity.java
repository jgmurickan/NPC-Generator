package mobilegroup1.npcgenerator;

import android.content.Intent;
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

        if(intent.getBooleanExtra("newNPC", true))
        {
            dude = new NPC();
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
        Toast.makeText(this, "Totally Saved the NPC", Toast.LENGTH_SHORT).show();
    }
}
