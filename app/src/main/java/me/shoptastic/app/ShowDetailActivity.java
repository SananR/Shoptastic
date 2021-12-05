package me.shoptastic.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.Adapter.ProductAdapter;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCardBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private int numberOrder = 1;

    private Order order;
    private Product aProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        String productName = "";
        String productPrice = "";
        String productDescription = "";

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            productName = extra.getString("productName");
            productPrice = extra.getString("productPrice");
            productDescription = extra.getString("description");
        }

        TextView pName = (TextView) findViewById(R.id.titleTxt);
        pName.setText(productName);

        TextView pPrice = (TextView) findViewById(R.id.feeTxt);
        pPrice.setText("$" + productPrice);

        TextView pDesc = (TextView) findViewById(R.id.descriptionTxt);
        pDesc.setText(productDescription);

        aProduct.setProduct_name(productName);
        aProduct.setDescription(productDescription);
        aProduct.setPrice(productPrice);
        order = new Order(this);

        initView();
        getBundle();
    }

    private void getBundle(){

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberOrder = numberOrder - 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }

        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                aProduct.setNumberInCart(numberOrder);
                order.insertProduct(aProduct);
            }
        });
    }

    private void initView(){
        addToCardBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.feeTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrdersTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);


    }

}
