package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnemyListActivity extends AppCompatActivity {


    Singleton instance;
    ListView theList;
    EnemyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_list);

        instance = Singleton.getInstance();

        //set up the adapter with the list view
        adapter = new EnemyAdapter(this, instance.getEnemies());
        adapter.notifyDataSetChanged();
        theList = (ListView) findViewById(R.id.enemyList);
        theList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        instance.handleLists();
        adapter.updateEnemyList(instance.getEnemies());
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
                refreshList();
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

    public void generateOldEnemy(Enemy enemy, int position)
    {
        Intent intent = new Intent(this, EnemyActivity.class);
        intent.putExtra("newEnemy", false);

        SQLiteDatabase db = openOrCreateDatabase("NPC_Generator", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Enemy", null);
        cursor.moveToPosition(position);
        String type = enemy.getIndex(0, cursor.getString(0)) + "";
        String weapon = enemy.getIndex(2, cursor.getString(2)) + "";;
        String shield = enemy.getIndex(3, cursor.getString(3)) + "";;
        String armor = enemy.getIndex(4, cursor.getString(4)) + "";;
        Log.d("TAG", "enemy.createFromString: " + type + " " + cursor.getString(1) + " " + weapon + " " + shield + " " + armor + " " + cursor.getString(5) + " " + cursor.getString(6)
                        + " " + cursor.getString(7) + " " + cursor.getString(8) + " " + cursor.getString(9) + " " + cursor.getString(10) + " " + cursor.getString(11) + " " + cursor.getString(12)
                        + " " + cursor.getString(13)  + " " + cursor.getString(14));

        intent.putExtra("type", type);
        intent.putExtra("cRate", cursor.getString(1));

        intent.putExtra("enemyAll", type + " " + cursor.getString(1) + " " + weapon + " " + shield + " " + armor + " "  + cursor.getString(5) + " " + cursor.getString(6)
                + " " + cursor.getString(7) + " " + cursor.getString(8) + " " + cursor.getString(9) + " " + cursor.getString(10) + " " + cursor.getString(11) + " " + cursor.getString(12)
                + " " + cursor.getString(13)  + " " + cursor.getString(14));

        cursor.close();
        db.close();
        startActivity(intent);
    }

    public void refreshList() {
        instance.handleLists();
        adapter.updateEnemyList(instance.getEnemies());
        //finish();
        //startActivity(getIntent());
    }

    public class EnemyAdapter extends ArrayAdapter<Enemy>
    {

        public ArrayList<Enemy> enemyList;

        public EnemyAdapter(Context context, ArrayList<Enemy> enemies)
        {
            super(context, 0, enemies);
            enemyList = enemies;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Enemy enemy = enemyList.get(position);

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
                    Enemy enemyTemp = enemyList.get(position);
                    Log.d("TAG", "enemy temp = " + enemyTemp.toString());
                    generateOldEnemy(enemyTemp, position);
                }
            });

            chView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Enemy enemyTemp = enemyList.get(position);
                    Log.d("TAG", "enemy temp = " + enemyTemp.toString());
                    generateOldEnemy(enemyTemp, position);
                }
            });


            return convertView;
        }

        private void updateEnemyList(ArrayList<Enemy> newList)
        {
            enemyList.clear();
            enemyList.addAll(newList);
            this.notifyDataSetChanged();
        }
    }
}
