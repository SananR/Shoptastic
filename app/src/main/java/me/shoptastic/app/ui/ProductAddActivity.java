package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.shoptastic.app.R;
import me.shoptastic.app.data.presenter.ProductPresenter;

public class ProductAddActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owner_post_dish);
        Button button = findViewById(R.id.post);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });

    }

    public String getProductName() {
        return ((TextView) findViewById(R.id.addProductName)).getText().toString();
    }

    public String getProductDescription() {
        return ((EditText) findViewById(R.id.addProductDescription)).getText().toString();
    }

    public Float getProductPrice() {
        return Float.valueOf(((EditText) findViewById(R.id.addProductPrice)).getText().toString());
    }

    public Integer getProductID() {
        return Integer.valueOf(((EditText) findViewById(R.id.addProductID)).getText().toString());
    }

    public String getStoreName() {
        return getIntent().getStringExtra("store");
    }

    public void clear() {
        ((EditText) findViewById(R.id.addProductName)).setText("");
        ((EditText) findViewById(R.id.addProductPrice)).setText("");
        ((EditText) findViewById(R.id.addProductID)).setText("");
        ((EditText) findViewById(R.id.addProductDescription)).setText("");
    }

    public void register(View v) {
        ProductPresenter presenter = new ProductPresenter(this);
        presenter.register();
    }

}
