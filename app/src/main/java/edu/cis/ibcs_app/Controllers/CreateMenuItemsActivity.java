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

public class CreateMenuItemsActivity extends AppCompatActivity {

    EditText itemIDText;
    EditText itemNameText;
    EditText itemDescriptionText;
    EditText itemPriceText;
    EditText itemTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_items);
        this.itemIDText = (EditText) this.findViewById(R.id.itemIDText);
        this.itemNameText = (EditText) this.findViewById(R.id.itemNameText);
        this.itemDescriptionText = (EditText) this.findViewById(R.id.itemDescriptionText);
        this.itemPriceText = (EditText) this.findViewById(R.id.itemPriceText);
        this.itemTypeText = (EditText) this.findViewById(R.id.itemTypeText);
    }

    public void addMenuItem(View v) {
        try {
            String id = this.itemIDText.getText().toString();
            String name = this.itemNameText.getText().toString();
            String description = this.itemDescriptionText.getText().toString();
            String price = this.itemPriceText.getText().toString();
            String type = this.itemTypeText.getText().toString();
            if(id.equals("")||name.equals("")||description.equals("")||type.equals("")||price.equals("")) {
                Toast message = Toast.makeText(this, "Please fill out all fields before proceeding",Toast.LENGTH_LONG);
                message.show();
            }
            else {
                Request addMenuItemReq = new Request(CISConstants.ADD_MENU_ITEM);
                addMenuItemReq.addParam(CISConstants.ITEM_ID_PARAM, id);
                addMenuItemReq.addParam(CISConstants.ITEM_NAME_PARAM, name);
                addMenuItemReq.addParam(CISConstants.DESC_PARAM, description);
                addMenuItemReq.addParam(CISConstants.ITEM_TYPE_PARAM, type);
                addMenuItemReq.addParam(CISConstants.PRICE_PARAM, price);
                String addMenuItemResult = SimpleClient.makeRequest(CISConstants.HOST, addMenuItemReq);
                Toast message = Toast.makeText(this, addMenuItemResult,Toast.LENGTH_LONG);
                message.show();
            }
        }
        catch(Exception err) {
            Toast message = Toast.makeText(this, err.getMessage(),Toast.LENGTH_LONG);
            message.show();
        }
    }

    public void goToMainActivity(View v) {
        Intent swapToMainActivity = new Intent(CreateMenuItemsActivity.this, MainActivity.class);
        startActivity(swapToMainActivity);
    }
}