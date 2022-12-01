package com.example.authapplicationv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.regex.Pattern;

public class activity_signup extends AppCompatActivity  {

    TextInputLayout firstName;
    TextInputEditText fname;
    TextInputEditText lname;
    TextInputEditText mobile;
    TextInputEditText email;
    AutoCompleteTextView dob;
    AutoCompleteTextView language;
    TextInputEditText password;
    TextInputEditText confirmPassword;

    MaterialButton signup;
    MaterialTextView signin;

    private static final Pattern PATTERN_PATTERN = Pattern.compile("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$");

    // Array of Languages
    String[] languages = {"English", "Marathi", "Hindi", "Spanish", "Russian", "Japanese", "German", "Chinese"};
    private String selectedLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstName = findViewById(R.id.textInput_firstname);
        fname = findViewById(R.id.edittext_firstname);
        lname = findViewById(R.id.edittext_lastname);
        email   = findViewById(R.id.edittext_emailid);
        mobile = findViewById(R.id.edittext_mobileno);
        dob = findViewById(R.id.edittext_dateofbirth);
        language = findViewById(R.id.spinner_language);
        password = findViewById(R.id.edittext_password);
        confirmPassword = findViewById(R.id.edittext_confirmpassword);
        signup = findViewById(R.id.button_signup);
        signin = findViewById(R.id.textview_signin);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.drop_down_item,
                languages
        );

        language.setAdapter(adapter);

        language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLang = language.getText().toString();
            }
        });

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        dob.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    submitUserData();
//                if (confirmInput()) {
//                    submitUserData();
//                }

                // Intent for swiching to Imageview Activity
                Intent intent = new Intent(getApplicationContext(),ImageviewActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private boolean validateText(String text, EditText textComponent) {
        if(text.isEmpty()) {
            textComponent.setError(textComponent.getHint()+" can't be Empty");
            return false;
        }
        else {
            textComponent.setError(null);
            return true;
        }
    }

    private boolean validatePassword(String textPassword) {
        if(textPassword.isEmpty()) {
            password.setError("Password Can't be empty");
            return false;
        }
        else if (!PATTERN_PATTERN.matcher(textPassword).matches()){
            password.setError("Weak Passsword!, Please use alphanumeric Password");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }

    public Boolean confirmInput() {
        String firstName = fname.getText().toString();
        String lastName = lname.getText().toString();
        String email_Id = email.getText().toString();
        String mobile_No = mobile.getText().toString();
        String Password = password.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if (!validateText(firstName, fname) | !validateText(lastName, lname) | !validateText(email_Id, email) | !validateText(mobile_No, mobile) | !validatePassword(Password) | !validatePassword(confirmPass)) {
            return false;
        }
        else if(!Password.equals(confirmPass)) {
            confirmPassword.setError("Password & Confirm Password must be Same");
            return false;
        }
        else{
            return true;
        }
    }

    private void submitUserData() {
        String firstName = fname.getText().toString();
        String lastName = lname.getText().toString();
        String email_Id = email.getText().toString();
        String mobile_No = mobile.getText().toString();
        String Password = password.getText().toString();

        String msg = "First Name: "+firstName+"\nLastName: "+lastName+"\nEmail Id: "+email_Id+"\nMobile No: "+mobile_No+"\nLanguage:"+selectedLang+"\nPassword: "+Password;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), firstName, Toast.LENGTH_SHORT).show();
    }


}