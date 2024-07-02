package com.abadzheva.messenger.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordViewModel extends ViewModel {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public LiveData<Boolean> isSuccess() {
        return success;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void resetPassword(String email) {
        if (!email.isEmpty()) {
            auth.sendPasswordResetEmail(email).addOnSuccessListener(unused -> {
                success.setValue(true);
            }).addOnFailureListener(e -> {
                error.setValue(e.getMessage());
            });
        } else {
            error.setValue("Email is empty");
        }
    }
}
