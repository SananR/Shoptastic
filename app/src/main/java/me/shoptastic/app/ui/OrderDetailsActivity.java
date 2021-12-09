package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import me.shoptastic.app.R;

public class OrderDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ((TextView)findViewById(R.id.orderDetailsTitle)).setText(getIntent().getStringExtra("user") + "'s Order");
        TextView list = (TextView)findViewById(R.id.orderDetailsList);
        for (String s : getIntent().getStringArrayListExtra("details")) {
            list.append(s + "\n");
        }

        findViewById(R.id.pickup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}