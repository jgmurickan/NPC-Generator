package mobilegroup1.npcgenerator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by herol on 3/29/2017.
 */

public class Singleton {

    private static Singleton instance = null;
    private ArrayList<NPC> npcsList;
    private ArrayList<Enemy> enemiesList;
    private SQLiteDatabase db;
    private File file;

    protected Singleton()
    {
        file = new File("/data/data/mobilegroup1.npcgenerator/databases/NPC_Generator");
        handleLists();
    }

    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    public void handleLists()
    {
        //test
        enemiesList = new ArrayList<Enemy>();
//        enemiesList.add(new Enemy("Goblin", 3));
//        enemiesList.add(new Enemy("Ogre", 6));
        //this is where the database will be called from
        //currently example
        npcsList = new ArrayList<NPC>();
        if(file.exists() && !file.isDirectory()) {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
            //db.execSQL("DROP TABLE IF EXISTS NPC(Name VARCHAR, Gender VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
            db.execSQL("CREATE TABLE IF NOT EXISTS NPC(Name VARCHAR, Gender VARCHAR,Race VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
            String query = "SELECT * FROM NPC";
            Cursor cursor = db.rawQuery(query, null);
            //Log.d("TAG", cursor.getColumnCount() + "");
            npcsList.clear();
            while (cursor.moveToNext()) {
                //Log.d("TAG", cursor.getString(3));
                npcsList.add(new NPC(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4) ));
            }

            db.execSQL("CREATE TABLE IF NOT EXISTS Enemy(Type VARCHAR, Rate INTEGER, Weapon VARCHAR, Shield VARCHAR, Armor VARCHAR, " +
                    "Health INTEGER, ArmorClass INTEGER, Experience INTEGER, Proficiency INTEGER, ConBase INTEGER, StrBase INTEGER, " +
                    "DexBase INTEGER, ChrBase INTEGER, WisBase INTEGER, IntBase INTEGER);");
            query = "SELECT * FROM Enemy";
            Cursor curs = db.rawQuery(query, null);
            enemiesList.clear();
            while (curs.moveToNext()) {
                Log.d("TAG", "handle lists: " + curs.getString(0) + curs.getString(1) + curs.getString(2) + curs.getString(3));
                enemiesList.add(new Enemy(curs.getString(0), curs.getInt(1)));
            }

            cursor.close();
            db.close();
        }
    }

    public void dropList(String list) {
        db = SQLiteDatabase.openOrCreateDatabase(file, null);
        if(list.equals("Enemy")) {
            db.execSQL("DROP TABLE IF EXISTS Enemy");
            enemiesList.clear();
        }
        else if(list.equals("NPC")) {
            db.execSQL("DROP TABLE IF EXISTS NPC;");
            npcsList.clear();
        }
        db.close();
    }

    public ArrayList<NPC> getNPCS()
    {
        return npcsList;
    }
    public ArrayList<Enemy> getEnemies() { return enemiesList; }
}
