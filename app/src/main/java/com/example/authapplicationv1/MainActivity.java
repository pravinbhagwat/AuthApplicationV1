package com.example.authapplicationv1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText editText_username;
    EditText editText_password;
    MaterialButton loginbtn;
    TextView text_signup;

//    private static final Pattern PATTERN_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[0-9])" + "(?=.*[a-zA-Z]" + "(?=\\S+$" + ".{6,}" + "$");

    private static final Pattern PATTERN_PATTERN = Pattern.compile("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_username = findViewById(R.id.edittext_username);
        editText_password = findViewById(R.id.edittext_password);
        loginbtn = findViewById(R.id.button_login);
        text_signup = findViewById(R.id.textview_signup);

        loginbtn.setOnClickListener(view -> {
            if(confirmInput()) {
                checkLoginCredentials();
            }

        });

        text_signup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),activity_signup.class);
            startActivity(intent);
        });
    }

    private boolean validateUsername(String username) {
        if(username.isEmpty()) {
            editText_username.setError("Username can't be Empty");
            return false;
        }
//        else if (){
//
//        }
        else {
            editText_username.setError(null);
            return true;
        }
    }

    private boolean validatePassword(String password) {
        if(password.isEmpty()) {
            editText_password.setError("Password can't be Empty");
            return false;
        }
        else if (!PATTERN_PATTERN.matcher(password).matches()){
            editText_password.setError("Weak Passsword!, Please use alphanumeric Password");
            return false;
        }
        else {
            editText_password.setError(null);
            return true;
        }
    }

    public Boolean confirmInput() {
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();

        if (!validateUsername(username) | !validatePassword(password)) {
            return false;
        }
        else {
//            String msg = "Username: "+username + "\n" + "Password: " + password;
//
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void checkLoginCredentials() {

        String user = editText_username.getText().toString();
        String pass = editText_password.getText().toString();

        if (user.equals("Admin") && pass.equals("Admin@123")) {
            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }


}