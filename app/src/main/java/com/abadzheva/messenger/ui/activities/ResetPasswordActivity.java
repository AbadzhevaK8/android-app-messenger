package com.abadzheva.messenger.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.abadzheva.messenger.R;
import com.abadzheva.messenger.ui.viewmodels.ResetPasswordViewModel;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";
    private EditText editTextEmail;
    private Button buttonResetPassword;

    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reset_password_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ----------------------- onCreate -----------------------
        initViews();
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextEmail.setText(email);
        buttonResetPassword.setOnClickListener(v -> {
            String gotEmail = editTextEmail.getText().toString().trim();
            viewModel.resetPassword(gotEmail);
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        errorMessage,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        viewModel.isSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        R.string.reset_link_sent,
                        Toast.LENGTH_SHORT
                ).show();
                Intent intent = LoginActivity.newIntent(this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}