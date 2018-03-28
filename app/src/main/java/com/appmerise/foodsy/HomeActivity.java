package com.appmerise.foodsy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class HomeActivity extends AppCompatActivity {


    String MY_PREFS_NAME = "MYDB";
    SharedPreferences prefs;
    EditText et_currentlocation;
    EditText et_destlocation;
    RadioGroup rg_type;
    RadioButton rb_dinein, rb_takeaway;
    Button btn_go;
    TextView tvName;
    CircleImageView civ_userprofl;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UsersNode = database.getReference("Users");
    String currloc, destloc;
    boolean type;
    View rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("Chcek", "onCreate: IN Home ");

        tvName = findViewById(R.id.tv_username);
        et_currentlocation = findViewById(R.id.et_current_loc);
        et_destlocation = findViewById(R.id.et_destination_loc);
        rg_type = findViewById(R.id.rgtype);
        rb_dinein = findViewById(R.id.cb_dine);
        rb_takeaway = findViewById(R.id.cb_takeaway);
        btn_go = findViewById(R.id.btn_go);
        civ_userprofl = findViewById(R.id.civ_userprof);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        rl = findViewById(R.id.rl);

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


        //get user location (Current)


        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int a;
                a = nullcheck();
                if (a != -1) {
                    doIt();
                }

            }
        });

    }

    private void doIt() {

        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(getString(R.string.CurrentLocation), currloc);
        intent.putExtra(getString(R.string.DestinationLocation), destloc);
        intent.putExtra(getString(R.string.Type), type);

        startActivity(intent);
    }

    private int nullcheck() {
        currloc = et_currentlocation.getText().toString();
        destloc = et_destlocation.getText().toString();

        if (TextUtils.isEmpty(currloc)) {

            Snackbar snackbar = Snackbar
                    .make(rl, "Please Enter Current Location.", Snackbar.LENGTH_LONG);

            snackbar.show();
            //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return -1;

        }

        if (TextUtils.isEmpty(destloc)) {

            Snackbar snackbar = Snackbar
                    .make(rl, "Please Enter Destination Location.", Snackbar.LENGTH_LONG);

            snackbar.show();
            //Toast.makeText(this, "Please Enter Destination Location", Toast.LENGTH_SHORT).show();
            return -1;

        }

        if (rg_type.getCheckedRadioButtonId() == -1) {
            Snackbar snackbar = Snackbar
                    .make(rl, "Please Select Type.", Snackbar.LENGTH_LONG);

            snackbar.show();


            // Toast.makeText(this, "Please Select Type", Toast.LENGTH_SHORT).show();
            return -1;

        }


        type = (rg_type.getCheckedRadioButtonId() == R.id.cb_dine) ? FALSE : TRUE;
        //False for Dine and True for Take Away


        return 0;
    }
}
