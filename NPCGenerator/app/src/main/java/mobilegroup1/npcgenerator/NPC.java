package mobilegroup1.npcgenerator;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

import static org.xmlpull.v1.XmlPullParser.TYPES;

/**
 * Created by herol on 3/14/2017.
 */

public class NPC {

    Random rand;
    String[] firstNames = {"William", "Kennedy", "Gordon", "Eyasu", "Zanta", "Mikaes", "Nicolaas", "Quin", "Elco", "Iolas",
                                "Jassin", "Ryfona", "Sizad", "Bodmonlir", "Thomlin", "Eva", "Oudet", "Hamlyn", "Jessie", "Arnold"};
    String[] lastNames = {"Varty", "Ganjoo", "Daerel", "Shathana", "Treehelm", "Deadcutter", "Noboa", "Morillo", "Sainz", "Dulal",
                                "Tripolis", "Macris", "Gikas", "Spiro", "Montgomery", "Baird", "Toule", "Murphy", "Tlehas", "Jax"};
    String[] races = {"Human", "Elf", "Dwarf", "Halfling", "Half-Elf", "Drow"};
    String[] colors = {"Red", "Yellow", "Blue", "Orange", "Green", "Purple", "Gold", "Silver", "Black", "White"};
    String[] tops = {"Tunic", "Jerkin", "Waistcoat", "Jacket", "Shirt", "Blouse", "Tatters"};
    String[] bottoms = {"Tights", "Skirt", "Shorts", "Leathers", "Chaps"};

    //Get 6 values. one that's missing is gender
    int[] values = new int[7];

    public NPC()
    {
        rand = new Random();
        values[0] = rand.nextInt(firstNames.length);
        values[1] = rand.nextInt(lastNames.length);
        values[2] = rand.nextInt(2); //gender
        values[3] = rand.nextInt(races.length);
        values[4] = rand.nextInt(colors.length);
        values[5] = rand.nextInt(tops.length);
        values[6] = rand.nextInt(bottoms.length);
    }

    public NPC(String name, String gender, String race, String top, String bottom) {
        String[] names = name.split(" ");
        String first = names[0];
        String second = names[1];
        String[] tps = top.split(" ");
        String color = tps[0];
        String tp = tps[1];

        for(int i = 0; i < firstNames.length; i++) {
            if(firstNames[i].equals(first))
                values[0] = i;
        }
        for(int i = 0; i < lastNames.length; i++) {
            if(lastNames[i].equals(second))
                values[1] = i;
        }
        if(gender.equals("Male"))
            values[2] = 0;
        else
            values[2] = 1;

        for(int i = 0; i < races.length; i++)
        {
            if(races[i].equals(race))
            {
                values[3] = i;
            }
        }
        for(int i = 0; i < colors.length; i++) {
            if(colors[i].equals(color))
                values[4] = i;
        }
        for(int i = 0; i < tops.length; i++) {
            if(tops[i].equals(tp))
                values[5] = i;
        }
        for(int i = 0; i < bottoms.length; i++) {
            if(bottoms[i].equals(bottom))
                values[6] = i;
        }
    }

    public String getFirstName()
    {
        return firstNames[values[0]];
    }

    public String getLastName()
    {
        return lastNames[values[1]];
    }

    public String getGender()
    {
        if(values[2] == 0)
        {
            return "Male";
        }
        else
        {
            return "Female";
        }
    }

    public String getRace() { return races[values[3]]; }

    public String getColor()
    {
        return colors[values[4]];
    }

    public String getTop()
    {
        return tops[values[5]];
    }

    public String getBottom()
    {
        return bottoms[values[6]];
    }

    public String toString()
    {
        return "" + values[0] + " " + values[1] + " " + values[2] + " " +
                values[3] + " " + values[4] + " " + values[5] + " " + values[6];
    }

    public void setFromString(String str)
    {
        String[] temp = str.split(" ");
        for(int i = 0 ; i < temp.length; i++)
        {
            values[i] = Integer.parseInt(temp[i]);
        }
    }

}
