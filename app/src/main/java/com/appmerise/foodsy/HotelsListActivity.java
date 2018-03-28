package com.appmerise.foodsy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class HotelsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get(getString(R.string.hotelname));

            if (j != null)
                Toast.makeText(this, "data " + j, Toast.LENGTH_SHORT).show();
          /*  switch (j)
            {


            }
          */
        }
    }
}
