package com.appmerise.foodsy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appmerise.foodsy.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 123;
    EditText et_fullname, et_email, et_mobilenumber;

    TextInputEditText et_password;
    Button btn_signup;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    View V;
    TextView sign_up;
    private StorageReference mStorageRef;

    Uri uri;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UsersNode = database.getReference("Users");

    String uname, uemail, upassword, umobilenumber;

    CircleImageView circleImageView;


    String MY_PREFS_NAME = "MYDB";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        V = findViewById(R.id.viewSignUp);
        Log.d("Chcek", "onCreate:In signUP");


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference();


        //checking if user has already entered details
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString(getString(R.string.username), null);


        editor = prefs.edit();


        if (name != null) {
            startActivity(new Intent(this, HomeActivity.class));
            Log.d("Chcek", "onCreate: Launching HomeActivity ");

            finish();
        }


        sign_up = findViewById(R.id.tv_signin);
        circleImageView = findViewById(R.id.circleImageView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/avenir roman.otf");
        sign_up.setTypeface(typeface);


        et_email = findViewById(R.id.et_email);
        et_fullname = findViewById(R.id.et_fullname);
        et_password = findViewById(R.id.tiet_password);
        et_mobilenumber = findViewById(R.id.et_mobile);

        et_email.setTypeface(typeface);
        et_fullname.setTypeface(typeface);
        et_password.setTypeface(typeface);
        et_mobilenumber.setTypeface(typeface);

        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setTypeface(typeface);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = nullcheck();
                if (a == -1)
                    return;
                doStoredetails();

            }
        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectimage();
            }
        });


    }

    private void selectimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void doStoredetails() {
        final ProgressDialog pd = new ProgressDialog(SignUp.this);
        pd.setMessage("Signing you up...");
        pd.show();


        mStorageRef.child("UserProfilePics").child(currentUser.getUid()).putFile(uri).addOnSuccessListener(new OnSuccessListener <UploadTask.TaskSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downoaduri = taskSnapshot.getDownloadUrl();

                User user = new User(uname, uemail, umobilenumber, upassword, downoaduri.toString());
                UsersNode.child(currentUser.getUid()).setValue(user);

                Gson gson = new Gson();
                final String userobjectasstring = gson.toJson(user);
                editor.putString(getString(R.string.userobj), userobjectasstring);
                editor.putString(getString(R.string.username), user.getName());
                editor.apply();

                Toast.makeText(SignUp.this, "Saved " + user.getName() + " success", Toast.LENGTH_SHORT).show();
                if (pd.isShowing()) {
                    pd.dismiss();

                    et_email.setVisibility(View.GONE);
                    et_fullname.setVisibility(View.GONE);
                    et_password.setVisibility(View.GONE);
                    et_mobilenumber.setVisibility(View.GONE);

                    btn_signup.setText("Lets Go!");
                    btn_signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            startActivity(new Intent(SignUp.this, HomeActivity.class));
                            finish();
                        }
                    });
                    //  startActivity(new Intent(SignUp.this, HomeActivity.class));
                }

                Log.d("Appmerise checks.....", "onSuccess: Starting activity");
                //startActivity(new Intent(SignUp.this, HomeActivity.class));
                Log.d("Appmerise checks.....", "Akshayaaaaaa only... ");
                //finish();

            }
        });
        //storing offline copy of UserObject in sharedpred under MYDB


    }

    private int nullcheck() {
        uname = et_fullname.getText().toString();
        uemail = et_email.getText().toString();
        upassword = et_password.getText().toString();
        umobilenumber = et_mobilenumber.getText().toString();


        if (TextUtils.isEmpty(uname)) {
            Snackbar.make(V, "Enter Username ", Snackbar.LENGTH_SHORT).show();

            return -1;
        }
        if (TextUtils.isEmpty(uemail)) {
            Snackbar.make(V, "Enter Email ", Snackbar.LENGTH_SHORT).show();

            return -1;
        }
        if (TextUtils.isEmpty(upassword)) {
            Snackbar.make(V, "Enter password ", Snackbar.LENGTH_SHORT).show();

            return -1;
        }

        if (TextUtils.isEmpty(umobilenumber)) {
            Snackbar.make(V, "Enter Mobile Number ", Snackbar.LENGTH_SHORT).show();

            return -1;
        }
        if (umobilenumber.length() != 10) {
            Snackbar.make(V, "Check Mobile Number ", Snackbar.LENGTH_SHORT).show();

            return -1;

        }

        if (circleImageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_menu_camera).getConstantState()) {
            Snackbar.make(V, "Please Choose a picture ", Snackbar.LENGTH_SHORT).show();

            return -1;

        }


        return 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.circleImageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
