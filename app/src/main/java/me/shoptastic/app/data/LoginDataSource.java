package me.shoptastic.app.data;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import me.shoptastic.app.R;
import me.shoptastic.app.SignIn;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private final FirebaseAuth fAuth;

    public LoginDataSource() {
        fAuth = FirebaseAuth.getInstance();
    }

    public Result<User> login(String email, String password) {
        final boolean[] value = {false};
        try {
            fAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Test", "signInWithEmail:success");
                                //FirebaseUser user = fAuth.getCurrentUser();
                                value[0] = true;
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Test", "signInWithEmail:failure", task.getException());
                                value[0] = false;
                            }
                        }
                    });
            // get the values from the firebase check if that user is store ownwer as they will have a key of "store"
            // then pass on these values in the user
            FirebaseUser user = fAuth.getCurrentUser();
            // create presenter and then presenter will take them to the sign in page
            if (value[0]) return new Result.Success<>(user);
            else return new Result.Error(new IOException("Error signing in the user"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error signing in", e));
        }
    }

        public void logout(){
            // TODO: revoke authentication
            FirebaseAuth.getInstance().signOut();
        }
    }
}