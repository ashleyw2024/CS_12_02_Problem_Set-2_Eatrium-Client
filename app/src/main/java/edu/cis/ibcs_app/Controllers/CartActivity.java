package edu.cis.ibcs_app.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

public class CartActivity extends AppCompatActivity {

    TextView cartView;
    EditText orderIDInput;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.cartView = (TextView) this.findViewById(R.id.orderList);
        this.orderIDInput = (EditText) this.findViewById(R.id.orderIDInput);
        this.userID = getIntent().getStringExtra("userID");
        try {
            Request getCartReq = new Request(CISConstants.GET_CART);
            getCartReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            String cartRes = SimpleClient.makeRequest(CISConstants.HOST, getCartReq);
            this.cartView.setText(cartRes);
        }
        catch (Exception err) {
            Toast userMessage = Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG);
            userMessage.show();
        }
    }

    public void deleteOrder(View v) {
        String orderID = this.orderIDInput.getText().toString();
        try {
            Request delOrderReq = new Request(CISConstants.DELETE_ORDER);
            delOrderReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            delOrderReq.addParam(CISConstants.ORDER_ID_PARAM, orderID);
            String deleteOrdersResult = SimpleClient.makeRequest(CISConstants.HOST, delOrderReq);
            Toast userMessage = Toast.makeText(this, deleteOrdersResult,Toast.LENGTH_LONG);
            userMessage.show();
            Request getCartReq = new Request(CISConstants.GET_CART);
            getCartReq.addParam(CISConstants.USER_ID_PARAM, this.userID);
            String cartRes = SimpleClient.makeRequest(CISConstants.HOST, getCartReq);
            this.cartView.setText(cartRes);
        }
        catch (Exception err) {
            Toast userMessage = Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG);
            userMessage.show();
        }
    }

    public void goBackToMenu(View v) {
        Intent swapToMenu = new Intent(CartActivity.this, CISUserActivity.class);
        swapToMenu.putExtra("userID", this.userID);
        startActivity(swapToMenu);
    }
}