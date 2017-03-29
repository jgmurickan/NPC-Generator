package mobilegroup1.npcgenerator;

import java.util.ArrayList;

/**
 * Created by herol on 3/29/2017.
 */

public class Singleton {

    private static Singleton instance = null;
    private ArrayList<NPC> npcsList;

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
        npcsList.add(new NPC());
        npcsList.add(new NPC());
    }

    public ArrayList<NPC> getNPCS()
    {
        return npcsList;
    }
}
