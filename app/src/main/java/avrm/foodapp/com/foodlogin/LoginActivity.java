package avrm.foodapp.com.foodlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout email, pass;
    private Button btnSignIn;
    private TextView tvSignUp;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.text_input_email);
        pass = findViewById(R.id.text_input_pass);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvSignUp = findViewById(R.id.tvSignUp);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (authStateListener != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(LoginActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getEditText().getText().toString().trim();
                String passStr = pass.getEditText().getText().toString().trim();

                // TODO Needs to be implemented in a method
                if(emailStr.isEmpty()) {
                    email.setError("Please enter your email address");
                    email.requestFocus();

                } else if(passStr.isEmpty()) {
                    pass.setError("Please enter your email password");
                    pass .requestFocus();

                }else if(emailStr.isEmpty() && passStr.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Field can't be empty",
                            Toast.LENGTH_SHORT).show();
                }else if(!(emailStr.isEmpty() && passStr.isEmpty() ) ) {
                    firebaseAuth.signInWithEmailAndPassword(emailStr, passStr)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Log In error. Please try it again!", Toast.LENGTH_SHORT).show();
                                    } else{
                                        Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intToHome);
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
