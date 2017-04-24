package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnemyListActivity extends AppCompatActivity {


    Singleton instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_list);

        instance = Singleton.getInstance();

        //set up the adapter with the list view
        EnemyAdapter adapter = new EnemyAdapter(this, instance.getEnemies());

        ListView theList = (ListView) findViewById(R.id.enemyList);
        theList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.enemy_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newEnemy:
                generateNewEnemy();
                return true;
            case R.id.refreshEnemy:
                return true;
            case R.id.clearEnemy:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void generateNewEnemy()
    {
        FragmentManager fm = getFragmentManager();
        SelectEnemyDialogFragment sFrag = new SelectEnemyDialogFragment();
        sFrag.show(fm, "creating");
    }

    public void generateOldEnemy(Enemy enemy)
    {
        Intent intent = new Intent(this, EnemyActivity.class);
        intent.putExtra("newEnemy", false);
        intent.putExtra("type", "Imp");
        intent.putExtra("cRate", "0");
        intent.putExtra("enemyAll", enemy.toString());
        startActivity(intent);
    }

    public class EnemyAdapter extends ArrayAdapter<Enemy>
    {

        public EnemyAdapter(Context context, ArrayList<Enemy> enemies)
        {
            super(context, 0, enemies);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Enemy enemy = getItem(position);

            //check if we are reusing
            if(convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.enemy_list_layout, parent, false);
            }

            //set the view up
            TextView txView = (TextView) convertView.findViewById(R.id.enemyListType);

            txView.setText(enemy.getType());

            txView.setTag(position);

            TextView chView = (TextView) convertView.findViewById(R.id.enemyListChallenge);

            chView.setText("Challenge Rating: " + enemy.getChallengeRate());

            chView.setTag(position);
            // Attach the click event handler
            txView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Enemy enemyTemp = getItem(position);

                    generateOldEnemy(enemyTemp);
                }
            });

            chView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Enemy enemyTemp = getItem(position);

                    generateOldEnemy(enemyTemp);
                }
            });


            return convertView;
        }
    }
}
