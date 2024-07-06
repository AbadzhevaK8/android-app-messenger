package com.abadzheva.messenger.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abadzheva.messenger.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationViewModel extends ViewModel {

    private final FirebaseAuth auth;
    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference usersReference;

    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    public RegistrationViewModel() {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(firebaseAuth -> user.setValue(firebaseAuth.getCurrentUser()));
        firebaseDatabase = FirebaseDatabase.getInstance();
        usersReference = firebaseDatabase.getReference("Users");
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public void signUp(
            String email,
            String password,
            String name,
            String lastName,
            int age
    ) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser firebaseUser = authResult.getUser();
                    if (firebaseUser == null) {
                        error.setValue("User is null");
                        return;
                    }
                    User user = new User(firebaseUser.getUid(), name, lastName, age, false);
                    usersReference.child(user.getId()).setValue(user);
                })
                .addOnFailureListener(e -> {
            error.setValue(e.getMessage());
        });
    }
}
