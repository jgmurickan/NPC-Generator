package mobilegroup1.npcgenerator;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by herol on 4/19/2017.
 */

public class Enemy {

    String[] types = {"Imp", "Sprite", "Bandit", "Goblin", "Orc", "Werewolf",
            "Minotaur", "Ogre", "Fire Giant", "Treant", "Dragon", "Nightcrawler"};
    String[] weapons = {"No Weapon", "Dagger", "Shortsword", "Longsword", "Axe", "Spear", "Tree Branch"};
    String[] shields = {"No Shield", "Wooden Buckler", "Iron Shield", "Kite Shield", "Tower Shield"};
    String[] armors = {"No Armor", "Cloth", "Leather", "Chain Shirt", "Plate Mail"};

    int[] values = new int[15];
    Random rand;


    public Enemy(String type, int challengeRate)
    {
        rand = new Random();
        values[0] = Arrays.asList(types).indexOf(type);
        values[1] = challengeRate;
        values[2] = rand.nextInt(weapons.length);
        values[3] = rand.nextInt(shields.length);
        values[4] = rand.nextInt(armors.length);
        determineStats();
    }

    //calculate a bunch of stats. probably better to keep collapsed
    private void determineStats()
    {
        rand = new Random();
        //Stats and/or Ability scores
        values[5] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Constitution
        values[6] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Strength
        values[7] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Dexterity
        values[8] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Charisma
        values[9] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Wisdom
        values[10] = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);//Intelligence
        //Hit Points
        if(values[0] == 0 || values[0] == 1)
        {
            //size is tiny
            //Xd4 + Constitution Modifier * X where X is Challenge Rating
            values[11] = values[1] * (rand.nextInt(4) + 1) + values[1] * ((values[5] - 10) / 2);
            //armor class while in size
        }
        else if(values[0] == 2 || values[0] == 3)
        {
            //size is small
            values[11] = values[1] * (rand.nextInt(6) + 1) + values[1] * ((values[5] - 10) / 2);
        }
        else if(values[0] == 4 || values[0] == 5)
        {
            //size is medium
            values[11] = values[1] * (rand.nextInt(8) + 1) + values[1] * ((values[5] - 10) / 2);
        }
        else if(values[0] == 6 || values[0] == 7)
        {
            //size is large
            values[11] = values[1] * (rand.nextInt(10) + 1) + values[1] * ((values[5] - 10) / 2);
        }
        else if(values[0] == 8 || values[0] == 9)
        {
            //size is huge
            values[11] = values[1] * (rand.nextInt(12) + 1) + values[1] * ((values[5] - 10) / 2);
        }
        else if(values[0] == 10 || values[0] == 11)
        {
            //size is gargantuan
            values[11] = values[1] * (rand.nextInt(20) + 1) + values[1] * ((values[5] - 10) / 2);
        }
        //Proficiency bonus and experience respectively
        //all based on challenge rating, gonna be a huge block
        switch(values[1])
        {
            case 1 :
                values[12] = 2;
                values[13] = 200;
                break;
            case 2 :
                values[12] = 2;
                values[13] = 450;
                break;
            case 3 :
                values[12] = 2;
                values[13] = 700;
                break;
            case 4 :
                values[12] = 2;
                values[13] = 1100;
                break;
            case 5 :
                values[12] = 3;
                values[13] = 1800;
                break;
            case 6 :
                values[12] = 3;
                values[13] = 2300;
                break;
            case 7 :
                values[12] = 3;
                values[13] = 2900;
                break;
            case 8 :
                values[12] = 3;
                values[13] = 3900;
                break;
            case 9 :
                values[12] = 4;
                values[13] = 5000;
                break;
            case 10 :
                values[12] = 4;
                values[13] = 5900;
                break;
            case 11 :
                values[12] = 4;
                values[13] = 7200;
                break;
            case 12 :
                values[12] = 4;
                values[13] = 8400;
                break;
            case 13 :
                values[12] = 5;
                values[13] = 10000;
                break;
            case 14 :
                values[12] = 5;
                values[13] = 11500;
                break;
            case 15 :
                values[12] = 5;
                values[13] = 13000;
                break;
            case 16 :
                values[12] = 5;
                values[13] = 15000;
                break;
            case 17 :
                values[12] = 6;
                values[13] = 18000;
                break;
            case 18 :
                values[12] = 6;
                values[13] = 20000;
                break;
            case 19 :
                values[12] = 6;
                values[13] = 22000;
                break;
            case 20 :
                values[12] = 6;
                values[13] = 25000;
                break;
            case 21 :
                values[12] = 7;
                values[13] = 33000;
                break;
            case 22 :
                values[12] = 7;
                values[13] = 41000;
                break;
            case 23 :
                values[12] = 7;
                values[13] = 50000;
                break;
            case 24 :
                values[12] = 7;
                values[13] = 62000;
                break;
            case 25 :
                values[12] = 8;
                values[13] = 75000;
                break;
            case 26 :
                values[12] = 8;
                values[13] = 90000;
                break;
            case 27 :
                values[12] = 8;
                values[13] = 105000;
                break;
            case 28 :
                values[12] = 8;
                values[13] = 120000;
                break;
            case 29 :
                values[12] = 9;
                values[13] = 135000;
                break;
            case 30 :
                values[12] = 9;
                values[13] = 155000;
                break;
        }

        //armor class
        //10 + armor + shield + dex mod
        values[14] = 10 + ((values[7] - 10) / 2);
        //armors
        if(values[4] == 2)
        {
            values[14] += 1;
        }
        else if(values[4] == 3)
        {
            values[14] += 3;
        }
        else if(values[4] == 4)
        {
            values[14] += 8;
            values[14] -= ((values[7] - 10) / 2);
        }
        //shields
        if(values[3] == 1)
        {
            values[14] += 1;
        }
        else if(values[3] == 2)
        {
            values[14] += 2;
        }
        else if(values[3] == 3)
        {
            values[14] += 3;
        }
        else if(values[3] == 4)
        {
            values[14] += 5;
        }

    }


    public String getType()
    {
        return types[values[0]];
    }

    public int getChallengeRate()
    {
        return values[1];
    }

    public String getWeapon() { return weapons[values[2]]; }

    public String getShield() { return shields[values[3]]; }

    public String getArmor() { return armors[values[4]]; }

    public int getConstitution() { return values[5]; }
    public int getConAbility() { return ((values[5] - 10) / 2); }
    public int getStrength() { return values[6]; }
    public int getStrAbility() { return ((values[6] - 10) / 2); }
    public int getDexterity() { return values[7]; }
    public int getDexAbility() { return ((values[7] - 10) / 2); }
    public int getCharisma() { return values[8]; }
    public int getChrAbility() { return ((values[8] - 10) / 2); }
    public int getWisdom() { return values[9]; }
    public int getWisAbility() { return ((values[9] - 10) / 2); }
    public int getIntelligence() { return values[10]; }
    public int getIntAbility() { return ((values[10] - 10) / 2); }

    public int getHealth() { return values[11]; }

    public int getProficiency() { return values[12]; }

    public int getExperience() { return values[13]; }

    public int getArmorClass() { return values[14]; }

    public int getIndex(int list, String index) {
        switch(list) {
            case 0:
                return Arrays.asList(types).indexOf(index);
            case 2:
                return Arrays.asList(weapons).indexOf(index);
            case 3:
                return Arrays.asList(shields).indexOf(index);
            default:
                return Arrays.asList(armors).indexOf(index);
        }
    }

    public String toString()
    {
        return "" + values[0] + " " + values[1] + " " + values[2] + " " + values[3] + " " + values[4] + " " + values[5] + " " + values[6]
                + " " + values[7] + " " + values[8] + " " + values[9] + " " + values[10] + " " + values[11] + " " + values[12]
                + " " + values[13]  + " " + values[14];
    }

    public void createFromString(String str)
    {
        Log.d("TAG", "in createfromstring: " + str);
        String[] temp = str.split(" ");
        for(String s:temp)
            Log.d("TAG", s);
        for(int i = 0 ; i < temp.length; i++)
        {
            values[i] = Integer.parseInt(temp[i]);
            Log.d("TAG", "createfromstring: " + values[i]);
        }
    }
}
