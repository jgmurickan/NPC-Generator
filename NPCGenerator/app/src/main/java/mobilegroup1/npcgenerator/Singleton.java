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
    private SQLiteDatabase db;

    protected Singleton()
    {
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

    private void handleLists()
    {
        //this is where the database will be called from
        //currently example
        npcsList = new ArrayList<NPC>();
        File file = new File("/data/data/mobilegroup1.npcgenerator/databases/NPC_Generator");
        if(file.exists() && !file.isDirectory()) {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
            //db.execSQL("DROP TABLE IF EXISTS NPC(Name VARCHAR, Gender VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
            db.execSQL("CREATE TABLE IF NOT EXISTS NPC(Name VARCHAR, Gender VARCHAR,Race VARCHAR, Top VARCHAR, Bottoms VARCHAR);");
            String query = "SELECT * FROM NPC";
            Cursor cursor = db.rawQuery(query, null);
            //Log.d("TAG", cursor.getColumnCount() + "");
            while (cursor.moveToNext()) {
                //Log.d("TAG", cursor.getString(3));
                npcsList.add(new NPC(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4) ));
            }
            cursor.close();
            db.close();
        }
    }

    public ArrayList<NPC> getNPCS()
    {
        return npcsList;
    }
}
