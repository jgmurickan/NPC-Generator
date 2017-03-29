package mobilegroup1.npcgenerator;

import java.util.Random;

/**
 * Created by herol on 3/14/2017.
 */

public class NPC {

    Random rand;
    String[] firstNames = {"Steven", "Robert", "Michael"};
    String[] lastNames = {"Stevenson", "Robertson", "Michaelson"};
    String[] colors = {"Red", "Yellow", "Blue", "Orange", "Green", "Purple", "Gold", "Silver", "Black", "White"};
    String[] tops = {"Tunic", "Jerkin", "Waistcoat", "Jacket", "Loose Shirt", "Blouse", "Tatters"};
    String[] bottoms = {"Tights", "Skirt", "Shorts", "Leathers", "Chaps"};

    //Get 6 values. one that's missing is gender
    int[] values = new int[6];

    public NPC()
    {
        rand = new Random();
        values[0] = rand.nextInt(firstNames.length);
        values[1] = rand.nextInt(lastNames.length);
        values[2] = rand.nextInt(2); //gender
        values[3] = rand.nextInt(colors.length);
        values[4] = rand.nextInt(tops.length);
        values[5] = rand.nextInt(bottoms.length);
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

    public String getColor()
    {
        return colors[values[3]];
    }

    public String getTop()
    {
        return tops[values[4]];
    }

    public String getBottom()
    {
        return bottoms[values[5]];
    }

    public String toString()
    {
        return "" + values[0] + values[1] + values[2] + values[3] + values[4] + values[5];
    }

    public void setFromString(String str)
    {
        for(int i = 0 ; i < values.length; i++)
        {
            values[i] = str.charAt(i);
        }
    }

}