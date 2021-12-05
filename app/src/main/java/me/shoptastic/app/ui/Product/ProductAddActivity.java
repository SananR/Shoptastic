package me.shoptastic.app.ui.Product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.shoptastic.app.R;
import me.shoptastic.app.data.register.presenter.ProductPresenter;
import me.shoptastic.app.ui.Activity;

public class ProductAddActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addproduct);
        Button button = findViewById(R.id.post);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });

    }

    public String getProductName() {
        return ((EditText) findViewById(R.id.qt2)).getText().toString();
    }

    public String getProductDescription() {
        return ((EditText) findViewById(R.id.Description)).getText().toString();
    }

    public Float getProductPrice() {
        return Float.valueOf(((EditText) findViewById(R.id.qt4)).getText().toString());
    }

    public Integer getProductID() {
        return Integer.valueOf(((EditText) findViewById(R.id.qt)).getText().toString());
    }

    public void register(View v) {
        ProductPresenter presenter = new ProductPresenter(this);
        presenter.register();
    }

}
