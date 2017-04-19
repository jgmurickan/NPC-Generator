package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EnemyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_list);
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
        Intent intent = new Intent(this, EnemyActivity.class);
        startActivity(intent);
    }
}