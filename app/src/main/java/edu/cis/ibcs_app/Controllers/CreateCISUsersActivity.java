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

public class CreateCISUsersActivity extends AppCompatActivity {

    EditText userID;
    EditText yearGroup;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cisusers);

        this.userID = (EditText) this.findViewById(R.id.userID);
        this.name = (EditText) this.findViewById(R.id.name);
        this.yearGroup = (EditText) this.findViewById(R.id.yearGroup);
    }

    public void createUser(View v) {
        try {
            String userID = this.userID.getText().toString();
            String name = this.name.getText().toString();
            String yearGroup = this.yearGroup.getText().toString();
            if(userID.equals("") || name.equals("") || yearGroup.equals("")) {
                Toast message = Toast.makeText(this, "Please Fill all the information", Toast.LENGTH_LONG);
                message.show();
            }
            else {
                Request createUserReq = new Request(CISConstants.CREATE_USER);
                createUserReq.addParam(CISConstants.USER_ID_PARAM, userID);
                createUserReq.addParam(CISConstants.USER_NAME_PARAM, name);
                createUserReq.addParam(CISConstants.YEAR_LEVEL_PARAM, yearGroup);
                String createUserResponse = SimpleClient.makeRequest(CISConstants.HOST, createUserReq);
                Toast message = Toast.makeText(this, createUserResponse, Toast.LENGTH_LONG);
                message.show();
            }
        }
        catch (Exception err) {
            Toast message = Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG);
            message.show();
        }
    }

    public void backToMainActivity(View v) {
        Intent swapWithMain = new Intent(CreateCISUsersActivity.this, MainActivity.class);
        startActivity(swapWithMain);
    }

    public void logInActivity(View V) {
        Intent swapWithMain = new Intent(CreateCISUsersActivity.this, LogInActivity.class);
        startActivity(swapWithMain);
    }
}