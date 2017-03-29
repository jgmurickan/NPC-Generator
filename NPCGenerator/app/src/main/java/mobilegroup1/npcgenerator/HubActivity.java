package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
    }

    public void navigateToNPC(View v)
    {
        Intent intent = new Intent(this, NPCListActivity.class);
        startActivity(intent);
    }

    public void navigateToEnemy(View v)
    {

    }
}
