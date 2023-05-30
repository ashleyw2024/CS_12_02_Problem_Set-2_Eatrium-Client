package edu.cis.ibcs_app.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

public class CISUserActivity extends AppCompatActivity {

    String userID;
    TextView userNameText;
    TextView remainingValueText;
    RecyclerView menuItems;
    ArrayList<String> names;
    ArrayList<String> descriptions;
    ArrayList<String> types;
    ArrayList<String> prices;
    ArrayList<String> itemIds;
    EditText itemIDInput;

    int orderID = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cisuser);
        this.userID = getIntent().getStringExtra("userID");
        this.userNameText = (TextView) this.findViewById(R.id.userNameText);
        this.remainingValueText = (TextView) this.findViewById(R.id.remainingValueText);
        this.menuItems = (RecyclerView) this.findViewById(R.id.menuItems);
        this.itemIDInput = (EditText) this.findViewById(R.id.itemIDInput);
        this.names = new ArrayList<String>();
        this.descriptions = new ArrayList<String>();
        this.types = new ArrayList<String>();
        this.prices = new ArrayList<String>();
        this.itemIds = new ArrayList<String>();
        try {
            Request getUserReq = new Request(CISConstants.GET_USER);
            getUserReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            String userInfo = SimpleClient.makeRequest(CISConstants.HOST, getUserReq);
            if(userInfo.equals(CISConstants.USER_INVALID_ERR)) {
                Toast message = Toast.makeText(this, userInfo,Toast.LENGTH_LONG);
                message.show();
            }
            else {
                String name = "";
                String remainingValue = "";

                int nameStartIndex = userInfo.indexOf("name=") + 6;
                int nameEndIndex = userInfo.indexOf("'", nameStartIndex);
                name = userInfo.substring(nameStartIndex, nameEndIndex);

                int moneyStartIndex = userInfo.indexOf("money=") + 6;
                int moneyEndIndex = userInfo.indexOf("}", moneyStartIndex);
                remainingValue = userInfo.substring(moneyStartIndex, moneyEndIndex);

                this.userNameText.setText("Welcome " + name + "!");
                this.remainingValueText.setText("Remaining Value: $" + remainingValue);
            }
            // Adding menu items to the recycler view
            Request getMenuNames = new Request(CISConstants.GET_MENU_NAMES);
            Request getMenuDescriptions = new Request(CISConstants.GET_MENU_DESCRIPTION);
            Request getMenuTypes = new Request(CISConstants.GET_MENU_TYPES);
            Request getMenuPrices = new Request(CISConstants.GET_MENU_PRICES);
            Request getMenuItemIds = new Request(CISConstants.GET_MENU_ITEM_IDS);
            String namesStr = SimpleClient.makeRequest(CISConstants.HOST, getMenuNames);
            String descriptionsStr = SimpleClient.makeRequest(CISConstants.HOST, getMenuDescriptions);
            String typesStr = SimpleClient.makeRequest(CISConstants.HOST, getMenuTypes);
            String pricesStr = SimpleClient.makeRequest(CISConstants.HOST, getMenuPrices);
            String itemIdsStr = SimpleClient.makeRequest(CISConstants.HOST, getMenuItemIds);
            String[] namesArray = namesStr.split("#");
            String[] descriptionsArray = descriptionsStr.split("#");
            String[] typesArray = typesStr.split("#");
            String[] pricesArray = pricesStr.split("#");
            String[] itemIdsArray = itemIdsStr.split("#");
            names.addAll(Arrays.asList(namesArray));
            descriptions.addAll(Arrays.asList(descriptionsArray));
            types.addAll(Arrays.asList(typesArray));
            prices.addAll(Arrays.asList(pricesArray));
            itemIds.addAll(Arrays.asList(itemIdsArray));
        }
        catch (Exception err) {
            Toast message = Toast.makeText(this, err.getMessage(),Toast.LENGTH_LONG);
            message.show();
        }
        MenuAdapter adapter = new MenuAdapter(names, descriptions, types, prices, itemIds);
        menuItems.setAdapter(adapter);
        menuItems.setLayoutManager(new LinearLayoutManager(this));
    }

    public void placeOrder(View v) {
        try {
            String orderID = Integer.toString(this.orderID);
            String itemID = itemIDInput.getText().toString();
            Request getTypeReq = new Request(CISConstants.GET_TYPE);
            getTypeReq.addParam(CISConstants.ITEM_ID_PARAM, itemID);
            String orderType = SimpleClient.makeRequest(CISConstants.HOST, getTypeReq);
            Request placeOrderReq = new Request(CISConstants.PLACE_ORDER);
            placeOrderReq.addParam(CISConstants.ORDER_ID_PARAM, orderID);
            placeOrderReq.addParam(CISConstants.ITEM_ID_PARAM, itemID);
            placeOrderReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            placeOrderReq.addParam(CISConstants.ORDER_TYPE_PARAM, orderType);
            String placeOrderResult = SimpleClient.makeRequest(CISConstants.HOST, placeOrderReq);
            Toast userMessage = Toast.makeText(this, placeOrderResult,Toast.LENGTH_LONG);
            userMessage.show();
            this.orderID++;
            Request getUserReq = new Request(CISConstants.GET_USER);
            getUserReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            String userInfo = SimpleClient.makeRequest(CISConstants.HOST, getUserReq);
            String remainingValue = "";
            int moneyStartIndex = userInfo.indexOf("money=") + 6;
            int moneyEndIndex = userInfo.indexOf("}", moneyStartIndex);
            remainingValue = userInfo.substring(moneyStartIndex, moneyEndIndex);
            this.remainingValueText.setText("Remaining Value: $" + remainingValue);
        }
        catch (Exception err){
            Toast userMessage = Toast.makeText(this, err.toString(), Toast.LENGTH_LONG);
            userMessage.show();
        }
    }

    public void goToHome(View v) {
        Intent swapToMain = new Intent(CISUserActivity.this, MainActivity.class);
        startActivity(swapToMain);
    }

    public void goToCart(View v) {
        Intent swapToCart = new Intent(CISUserActivity.this, CartActivity.class);
        swapToCart.putExtra("userID", this.userID);
        startActivity(swapToCart);
    }
}