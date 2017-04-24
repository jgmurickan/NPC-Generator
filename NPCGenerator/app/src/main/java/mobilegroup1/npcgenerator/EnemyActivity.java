package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class EnemyActivity extends AppCompatActivity {

    private Intent intent;
    private Enemy dude;
    private Random rand;

    //barf
    private TextView enemyType;
    private TextView enemyRate;
    private TextView enemyWeapon;
    private TextView enemyShield;
    private TextView enemyArmor;
    private TextView health;
    private TextView armorClass;
    private TextView experience;
    private TextView proficiency;
    private TextView conBase;
    private TextView strBase;
    private TextView dexBase;
    private TextView chrBase;
    private TextView wisBase;
    private TextView intBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy);

        //dear god, please android studio, give me sections so i can collapse this
        enemyType = (TextView)findViewById(R.id.enemyType);
        enemyRate = (TextView)findViewById(R.id.enemyRate);
        enemyWeapon = (TextView)findViewById(R.id.enemyWeapon);
        enemyShield = (TextView)findViewById(R.id.enemyShield);
        enemyArmor = (TextView)findViewById(R.id.enemyArmor);
        health = (TextView)findViewById(R.id.hp);
        armorClass = (TextView)findViewById(R.id.armorClass);
        experience = (TextView)findViewById(R.id.exp);
        proficiency = (TextView)findViewById(R.id.proficiency);
        conBase = (TextView)findViewById(R.id.constitutionBase);
        strBase = (TextView)findViewById(R.id.strengthBase);
        dexBase = (TextView)findViewById(R.id.dexterityBase);
        chrBase = (TextView)findViewById(R.id.charismaBase);
        wisBase = (TextView)findViewById(R.id.wisdomBase);
        intBase = (TextView)findViewById(R.id.intelligenceBase);

        intent = getIntent();

        rand = new Random();
        String[] temp = getResources().getStringArray(R.array.types);
        dude = new Enemy(temp[rand.nextInt(temp.length)], rand.nextInt(31));

        if(intent.getBooleanExtra("newEnemy", true) == false)
        {
            dude.createFromString(intent.getStringExtra("enemyAll"));
        }


        populateViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.enemy_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.saveEnemy:

                return true;
            case R.id.deleteEnemy:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateViews()
    {
        enemyType.setText(dude.getType());
        enemyRate.setText("Challenge Rating: " + dude.getChallengeRate());
        enemyWeapon.setText(dude.getWeapon());
        enemyShield.setText(dude.getShield());
        enemyArmor.setText(dude.getArmor());
        health.setText("Health Points: " + dude.getHealth());
        armorClass.setText("Armor Class: " + dude.getArmorClass());
        experience.setText(dude.getExperience() + " Experience Points");
        proficiency.setText("+" + dude.getProficiency() + " Proficiency Bonus");
        conBase.setText(dude.getConstitution() + "");
        //calculate the ability score
        ((TextView)findViewById(R.id.constitutionAbility)).setText(dude.getConAbility() + "");
        strBase.setText(dude.getStrength() + "");
        ((TextView)findViewById(R.id.strengthAbility)).setText(dude.getStrAbility() + "");
        dexBase.setText(dude.getDexterity() + "");
        ((TextView)findViewById(R.id.dexterityAbility)).setText(dude.getDexAbility() + "");
        chrBase.setText(dude.getCharisma() + "");
        ((TextView)findViewById(R.id.charismaAbility)).setText(dude.getChrAbility() + "");
        wisBase.setText(dude.getWisdom() + "");
        ((TextView)findViewById(R.id.wisdomAbility)).setText(dude.getWisAbility() + "");
        intBase.setText(dude.getIntelligence() + "");
        ((TextView)findViewById(R.id.intelligenceAbility)).setText(dude.getIntAbility() + "");
    }
}
