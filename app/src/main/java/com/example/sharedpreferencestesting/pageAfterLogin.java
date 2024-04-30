package com.example.sharedpreferencestesting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pageAfterLogin extends AppCompatActivity {

    TextView usernameTV, passwordTV;
    Button btnLogOut;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page_after_login);

        usernameTV = findViewById(R.id.usernameTV);
        passwordTV =  findViewById(R.id.passwordTV);
        btnLogOut = findViewById(R.id.btnLogOut);

        preferences = getSharedPreferences("SHARED_PREF",  MODE_PRIVATE);
        String username = preferences.getString("USERNAME", "");
        usernameTV.setText(username);
        String password = preferences.getString("PASSWORD", "");
        passwordTV.setText(password);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(pageAfterLogin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}