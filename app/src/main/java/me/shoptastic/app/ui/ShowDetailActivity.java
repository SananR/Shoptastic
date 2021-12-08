package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;

public class ShowDetailActivity extends Activity {
    private final CartRepository repository = CartRepository.getInstance();
    private final Order order = repository.getOrder();
    private int numberOrder = 1;
    private TextView addToCardBtn, numberOrderTxt;
    private ImageView plusBtn, minusBtn;
    private Product aProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            aProduct = new Product(extra.getString(ProductsActivity.productName),
                    extra.getString(ProductsActivity.productDescription),
                    extra.getFloat(ProductsActivity.productPrice), extra.getInt(ProductsActivity.productID),
                    extra.getString(ProductsActivity.productStore));
        }

        TextView pName = findViewById(R.id.titleTxt);
        pName.setText(aProduct.getName());

        TextView pPrice = findViewById(R.id.feeTxt);
        pPrice.setText("$" + aProduct.getPrice());

        TextView pDesc = findViewById(R.id.descriptionTxt);
        pDesc.setText(aProduct.getDescription());

        intiView();
    }


    private void intiView() {
        addToCardBtn = findViewById(R.id.addToCartBtn);
        numberOrderTxt = findViewById(R.id.numberOrdersTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);

        ShowDetailActivity activity = this;
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            public void onClick(View v) {
                if (order != null && !order.getStoreName().equals(aProduct.getStoreName())) {
                    Toast.makeText(activity, "There already exists a current order from a different store.", Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i = 0; i < numberOrder; i++) {
                    repository.addProduct(aProduct);
                }
            }
        });
    }

}
