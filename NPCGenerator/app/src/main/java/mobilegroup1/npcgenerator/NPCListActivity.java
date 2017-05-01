package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NPCListActivity extends AppCompatActivity {

    Singleton instance;
    ListView theList;
    NPCAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = Singleton.getInstance();

        TextView previousTitle = (TextView)findViewById(R.id.previousTitle);
        if(instance.getNPCS().size() == 0)
        {
            previousTitle.setText("You have no saved NPCs.");
        }
        else
        {
            previousTitle.setText("Your previously saved NPCs:");
        }

        //set up the adapter with the list view
        adapter = new NPCAdapter(this, instance.getNPCS());

        theList = (ListView) findViewById(R.id.npcList);
        theList.setAdapter(adapter);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        instance.handleLists();
        adapter.updateNPCList(instance.getNPCS());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.npc_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newNPC:
                generateNewNPC(null);
                return true;
            case R.id.clear:
                clearList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void clearList() {
        FragmentManager fm = getFragmentManager();
        ClearNPCDialogFragment clearFrag = new ClearNPCDialogFragment();
        clearFrag.show(fm, "clearing");
    }

    public class NPCAdapter extends ArrayAdapter<NPC>
    {

        ArrayList<NPC> npcList;

        public NPCAdapter(Context context, ArrayList<NPC> npcs)
        {
            super(context, 0, npcs);
            npcList = npcs;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            NPC npc = npcList.get(position);

            //check if we are reusing
            if(convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.npc_list_layout, parent, false);
            }

            //set the view up
            TextView txView = (TextView) convertView.findViewById(R.id.npcListItem);

            txView.setText(npc.getFirstName() + " " + npc.getLastName());

            txView.setTag(position);
            // Attach the click event handler
            txView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    NPC npcTemp = npcList.get(position);

                    generateOldNPC(npcTemp);
                }
            });


            return convertView;
        }

        private void updateNPCList(ArrayList<NPC> newList)
        {
            npcList.clear();
            npcList.addAll(newList);
            this.notifyDataSetChanged();
        }
    }
}
