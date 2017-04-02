package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashscreenActivity extends AppCompatActivity {

    Boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //handle things
        handleSetUp();
    }

    public void handleSetUp() {
        while(done == false)
        {
            new SetUpClass().execute();
        }
        Intent intent = new Intent(this, HubActivity.class);
        startActivity(intent);
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
