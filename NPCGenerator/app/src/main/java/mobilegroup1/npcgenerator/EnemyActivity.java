package mobilegroup1.npcgenerator;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        dude = new Enemy(intent.getStringExtra("type"), Integer.parseInt(intent.getStringExtra("cRate")));

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
                saveEnemy();
                return true;
            case R.id.deleteEnemy:
                Bundle bundle = new Bundle();
                bundle.putString("type", dude.getType());
                bundle.putString("rate", dude.getChallengeRate() + "");
                bundle.putString("weapon", dude.getWeapon());
                bundle.putString("shield", dude.getShield());
                bundle.putString("armor", dude.getArmor());
                bundle.putString("health", dude.getHealth()+"");
                bundle.putString("armorClass", dude.getArmorClass()+"");
                bundle.putString("experience", dude.getExperience()+"");
                bundle.putString("proficiency", dude.getProficiency()+"");
                DeleteEnemyDialogFragment deleteFrag = new DeleteEnemyDialogFragment();
                deleteFrag.setArguments(bundle);
                deleteFrag.show(fm, "Deleting");
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
    public void saveEnemy()
    {
        SQLiteDatabase db = openOrCreateDatabase("NPC_Generator", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Enemy(Type VARCHAR, Rate INTEGER, Weapon VARCHAR, Shield VARCHAR, Armor VARCHAR, " +
                "Health INTEGER, ArmorClass INTEGER, Experience INTEGER, Proficiency INTEGER, ConBase INTEGER, " +
                "StrBase INTEGER, DexBase INTEGER, ChrBase INTEGER, WisBase INTEGER, " +
                "IntBase INTEGER);");

        String type = dude.getType();
        String rate = dude.getChallengeRate() + "";
        String weapon = dude.getWeapon();
        String shield = dude.getShield();
        String armor = dude.getArmor();
        String health = dude.getHealth() + "";
        String armorClass = dude.getArmorClass() + "";
        String experience = dude.getExperience() + "";
        String proficiency = dude.getProficiency() + "";
        String conbase = dude.getConstitution() + "";
//        String conability = dude.getConAbility() + "";
        String strbase = dude.getStrength() + "";
//        String strability = dude.getStrAbility() + "";
        String dexbase = dude.getDexterity() + "";
//        String dexability = dude.getDexAbility() + "";
        String chrbase = dude.getCharisma() + "";
//        String chrability = dude.getChrAbility() + "";
        String wisbase = dude.getWisdom() + "";
//        String wisability = dude.getWisAbility() + "";
        String intbase = dude.getIntelligence() + "";
//        String intability = dude.getIntAbility() + "";


        String query = "SELECT * FROM Enemy WHERE Type='" + type + "' AND Rate='" + rate + "' AND Weapon='" + weapon + "' AND Shield='" + shield +
                "' AND Armor='" + armor + "' AND Health='" + health + "' AND ArmorClass='" + armorClass + "' AND Experience='" + experience +
                "' AND Proficiency='" + proficiency + "' AND ConBase='" + conbase + "' AND StrBase='" + strbase +
                "' AND DexBase='" + dexbase + "' AND ChrBase='" + chrbase +
                "' AND WisBase='" + wisbase + "' AND IntBase='" + intbase + "';";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0 || intent.getBooleanExtra("newEnemy", true) == false) {
            Toast.makeText(this, "Already saved this Enemy!", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        else {

            db.execSQL("INSERT INTO Enemy (Type, Rate, Weapon, Shield, Armor, Health, ArmorClass, Experience, Proficiency, ConBase, StrBase, DexBase, ChrBase, WisBase, IntBase) VALUES ('" + type + "', '" + rate + "', '" + weapon + "', '" +
                    shield + "', '" + armor + "', '" + health + "', '" + armorClass + "', '" + experience + "', '" + proficiency + "', '" + conbase  + "', '" + strbase +
                    "', '" + dexbase + "', '" + chrbase + "', '" + wisbase + "', '" + intbase + "');");

            cursor = db.rawQuery(query, null);
            if(cursor.getCount() > 0) {
                cursor.close();

                Toast.makeText(this, "Your Enemy has been saved.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Enemy not saved.", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
