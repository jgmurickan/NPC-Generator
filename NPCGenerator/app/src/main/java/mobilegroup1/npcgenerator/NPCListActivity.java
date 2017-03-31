package mobilegroup1.npcgenerator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NPCListActivity extends AppCompatActivity {

    ArrayList<NPC> npcsList;
    Singleton instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = Singleton.getInstance();

        //set up the adapter with the list view
        NPCAdapter adapter = new NPCAdapter(this, instance.getNPCS());

        ListView theList = (ListView) findViewById(R.id.npcList);
        theList.setAdapter(adapter);

    }

    //Used when generate New NPC is pressed
    public void generateNewNPC(View v)
    {
        //go to the NPC Activity
        Intent intent = new Intent(this, NPCActivity.class);
        intent.putExtra("newNPC", true);
        startActivity(intent);
    }

    public void generateOldNPC(NPC npc)
    {
        Intent intent = new Intent(this, NPCActivity.class);
        intent.putExtra("newNPC", false);
        intent.putExtra("npcAll", npc.toString());
        startActivity(intent);
    }


    public class NPCAdapter extends ArrayAdapter<NPC>
    {

        public NPCAdapter(Context context, ArrayList<NPC> npcs)
        {
            super(context, 0, npcs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            NPC npc = getItem(position);

            //check if we are reusing
            if(convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.npc_list_layout, parent, false);
            }

            //set the view up
            Button btView = (Button) convertView.findViewById(R.id.npcListItem);

            btView.setText(npc.getFirstName() + " " + npc.getLastName());

            btView.setTag(position);
            // Attach the click event handler
            btView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    NPC npcTemp = getItem(position);

                    generateOldNPC(npcTemp);
                }
            });


            return convertView;
        }
    }
}
