package com.abadzheva.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.Contract;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button buttonSighUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ----------------------- onCreate -----------------------
        initViews();
        buttonSighUp.setOnClickListener(v -> {
            String email = getTrimmedValue(editTextEmail);
            String password = getTrimmedValue(editTextPassword);
            String name = getTrimmedValue(editTextName);
            String lastName = getTrimmedValue(editTextLastName);
            String age = getTrimmedValue(editTextAge);
            // TODO sigh up
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSighUp = findViewById(R.id.buttonSighUp);
    }

    @NonNull
    private String getTrimmedValue(@NonNull EditText editText) {
        return editText.getText().toString().trim();
    }

    @NonNull
    @Contract("_ -> new")
    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}

