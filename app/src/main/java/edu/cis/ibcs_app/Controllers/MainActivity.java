package edu.cis.ibcs_app.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //This is not great, for extra credit you can fix this so that network calls happen on a different thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void pingTheServer(View V) {
        try {
            Request req = new Request("ping");
            String resultFromServer = SimpleClient.makeRequest(CISConstants.HOST, req);
            Toast message = Toast.makeText(this, resultFromServer, Toast.LENGTH_LONG);
            message.show();

        }
        catch (Exception err) {
            err.printStackTrace();
            Toast message = Toast.makeText(this, "Server Offline", Toast.LENGTH_LONG);
            message.show();
        }
    }

    public void toLogInActivity(View v) {
        Intent swapToUser = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(swapToUser);
    }

    public void toCreateUser(View v) {
        Intent swapToCreateUser = new Intent(MainActivity.this, CreateCISUsersActivity.class);
        startActivity(swapToCreateUser);
    }

    public void toCreateMenuItems(View v) {
        Intent swapToCreateMenuItems = new Intent(MainActivity.this, CreateMenuItemsActivity.class);
        startActivity(swapToCreateMenuItems);
    }

}