package com.appmerise.foodsy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText
            et_password;
    EditText et_emailid;
    Button btn_signin;
    TextView tv_gotosignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    View V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_password = findViewById(R.id.tiet_password);
        et_emailid = findViewById(R.id.et_emailid);
        btn_signin = findViewById(R.id.btn_signin);
        //tv_gotosignup = findViewById(R.id.tv_gotosignup);

        firebaseAuth = FirebaseAuth.getInstance();

        V = findViewById(R.id.viewSignIn);
        btn_signin.setOnClickListener(this);
        tv_gotosignup.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_signin) {

            userLogin();

        }
        if (view == tv_gotosignup) {
            finish();

            Intent i1 = new Intent(getApplicationContext(), SignUp.class);

            startActivity(i1);

        }
    }

    private void userLogin() {
        String email = et_emailid.getText().toString().trim();
        String pwd = et_password.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {


            Snackbar.make(V, "Email ID Required", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {

            Snackbar.make(V, "Password Required", Snackbar.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Logging you in...");
        progressDialog.show();


        //logging in the user
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (email.equals("testuser@gmail.com") && pwd.equals("test")) {
            progressDialog.dismiss();
            finish();

            Intent i1 = new Intent(getApplicationContext(), HomeActivity.class);

            startActivity(i1);

        } else {

            Snackbar.make(V, "Email-ID or Password is Incorrect", Snackbar.LENGTH_LONG).show();


        }


      /*  firebaseAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        //if the task is successfull
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            finish();

                            Intent i1 = new Intent(getApplicationContext(), HomeActivity.class);

                            startActivity(i1);
                            finish();
                        } else {

                            Snackbar.make(V, "Email-ID or Password is Incorrect", Snackbar.LENGTH_LONG).show();


                        }
                    }
                });
*/
    }
}
