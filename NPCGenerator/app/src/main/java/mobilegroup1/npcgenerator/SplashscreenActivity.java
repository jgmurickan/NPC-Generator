package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashscreenActivity extends AppCompatActivity {

    Boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //handle things
//        SQLiteDatabase.openOrCreateDatabase("NPC_Generator", null);
        handleSetUp();
    }

    public void handleSetUp() {
        new SetUpClass().execute();
        Handler handle = new Handler();
        handle.postDelayed(new Runnable(){
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), HubActivity.class);
                finish();
                startActivity(intent);
                done = true;
            }
        }, 3000);



    }


    private class SetUpClass extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            Singleton.getInstance();
            //do database things here
            done = true;
            return null;
        }
    }
}
