package mobilegroup1.npcgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //handle things
        try {
            handleSetUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleSetUp() throws InterruptedException {
        Intent intent = new Intent(this, HubActivity.class);
        //probably should be multithreaded and make the database population here, but we'll handle that later, for now, wait
        Singleton.getInstance();
        wait(5000);
        startActivity(intent);
    }
}
