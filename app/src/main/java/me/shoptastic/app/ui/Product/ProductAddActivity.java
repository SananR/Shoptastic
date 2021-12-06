package me.shoptastic.app.ui.Product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.ProductPresenter;
import me.shoptastic.app.ui.register.RegisterActivity;

public class ProductAddActivity extends RegisterActivity {
    public static String ProductName;
    public static String ProductDescription;
    public static String ProductID;
    public static String ProductPrice;

    private ProductPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources.setContext(this);
        setContentView(R.layout.activity_addproduct);
        Button button = (Button) findViewById(R.id.post);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });

    }

    public void register(View v) {
        final EditText ID = findViewById(R.id.qt);
        final EditText Description = findViewById(R.id.Description);
        final EditText Price = findViewById(R.id.qt4);
        final EditText Name = findViewById(R.id.qt2);
        ProductName = Name.getText().toString();
        ProductDescription = Description.getText().toString();
        ProductID = ID.getText().toString() ;
        ProductPrice = Price.getText().toString();
        presenter = new ProductPresenter();
        Result r = presenter.register(ProductName, ProductDescription, ProductID, ProductPrice);
        if (r instanceof Result.Success) {
        } else {
            //TODO Update UI with error
        }
    }

}
