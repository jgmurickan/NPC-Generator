package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Used when generate New NPC is pressed
    public void generateNPC(View view)
    {
        //go to the NPC Activity
        Intent intent = new Intent(this, NPCActivity.class);
        intent.putExtra("newNPC", true);
        startActivity(intent);
    }
}
