package com.example.sharedpreferencestesting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    boolean isRemember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword =  findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id. checkBox);
        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        isRemember = sharedPreferences.getBoolean("CHECKBOX", false);
        if (isRemember){
            Intent intent = new Intent(MainActivity.this, pageAfterLogin.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().toString().equals("Rizal")&& txtPassword.getText().toString().equals("2707")){
                    Toast.makeText(MainActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                    String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    boolean checked = checkBox.isChecked();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USERNAME", username);
                    editor.putString("PASSWORD", password);
                    editor.putBoolean("CHECKBOX", checked);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, pageAfterLogin.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}