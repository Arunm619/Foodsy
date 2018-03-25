package com.appmerise.foodsy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.appmerise.foodsy.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {


    String MY_PREFS_NAME = "MYDB";
    SharedPreferences prefs;
    EditText et_currentlocation;
    EditText et_destlocation;
    CheckBox cb_dinein, cb_takeaway;
    Button btn_go;
    TextView tvName;
    CircleImageView civ_userprofl;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UsersNode = database.getReference("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("Chcek", "onCreate: IN Home ");

        tvName = findViewById(R.id.tv_username);
        et_currentlocation = findViewById(R.id.et_current_loc);
        et_destlocation = findViewById(R.id.et_destination_loc);
        cb_dinein = findViewById(R.id.cb_dine);
        cb_takeaway = findViewById(R.id.cb_takeaway);
        btn_go = findViewById(R.id.btn_go);
        civ_userprofl = findViewById(R.id.civ_userprof);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        String name = prefs.getString(getString(R.string.username), null);


        if (name == null) {
            startActivity(new Intent(this, IntroActivity.class));
            Log.d("Chcek", "onCreate: Launching intro ");

            finish();
        }

        Gson gson = new Gson();
        String json = prefs.getString(getString(R.string.userobj), "");

        User obj = gson.fromJson(json, User.class);
        if (obj == null) {
            startActivity(new Intent(this, IntroActivity.class));

            finish();
        }


        tvName.setText(prefs.getString("Username", null));


        if (obj != null) {
            PicassoClient.downloadimg(this, obj.getImagepath(), civ_userprofl);
        }

        /*btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });*/

    }
}
