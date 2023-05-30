package edu.cis.ibcs_app.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

public class LogInActivity extends AppCompatActivity {

    EditText userIDInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        this.userIDInput = (EditText) this.findViewById(R.id.userIDInput);
    }

    public void logIn(View v) {
        try {
            String userID = this.userIDInput.getText().toString();
            if(userID.equals("")) {
                Toast message = Toast.makeText(this, "Please fill out all the fields",Toast.LENGTH_LONG);
                message.show();
            }
            else {
                Request logInReq = new Request(CISConstants.CHECK_USER);
                logInReq.addParam(CISConstants.USER_ID_PARAM, userID);
                String logInResponse = SimpleClient.makeRequest(CISConstants.HOST, logInReq);
                if (logInResponse.equals(CISConstants.SUCCESS)){
                    Intent swapToOptionsPage = new Intent(LogInActivity.this, CISUserActivity.class);
                    swapToOptionsPage.putExtra("userID", userID);
                    startActivity(swapToOptionsPage);
                }
                Toast message = Toast.makeText(this, logInResponse,Toast.LENGTH_LONG);
                message.show();
            }
        }
        catch (Exception err) {
            Toast message = Toast.makeText(this, err.getMessage(),Toast.LENGTH_LONG);
            message.show();
        }
    }

    public void toHome(View v) {
        Intent swapToMain = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(swapToMain);
    }
}