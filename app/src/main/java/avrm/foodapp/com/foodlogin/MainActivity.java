package avrm.foodapp.com.foodlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout email, pass;
    private Button btnSignUp;
    private TextView tvSignIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.text_input_email);
        pass = findViewById(R.id.text_input_pass);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvSignIn = findViewById(R.id.tvSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivity.this, "Field can't be empty",
                            Toast.LENGTH_SHORT).show();
                }else if(!(emailStr.isEmpty() && passStr.isEmpty() ) ) {
                    firebaseAuth.createUserWithEmailAndPassword(emailStr, passStr).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign Up unsuccessfull, please try it again",
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                //TODO add Toast message for successfull log
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}


//    private boolean validateEmail () {
//        String emailInput = email.getEditText().getText().toString().trim();
//        if (emailInput.isEmpty()) {
//            email.setError("Field can't be empty");
//            return false;
//        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//
//        }*/ else {
//            email.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validatePassword () {
//        String passInput = pass.getEditText().getText().toString().trim();
//
//        if (passInput.isEmpty()) {
//            pass.setError("Field can't be empty.");
//            return false;
//        } else if (passInput.length() < 5) {
//            pass.setError("Password to short.");
//        }
//
//        pass.setError(null);
//        return true;
//    }


//btnSignUp.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            firebaseAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString(),
//                    pass.getEditText().getText().toString())
//
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                String input = "Your email " + email.getEditText().getText().toString() + "\n was successfully registered !";
//                                Toast.makeText(MainActivity.this, input,
//                                        Toast.LENGTH_LONG).show();
//
//                            } else {
//                                Toast.makeText(MainActivity.this, task.getException().getMessage(),
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//        }
//    });




//public class MainActivity extends AppCompatActivity {
//
//    private TextInputLayout email;
//    private TextInputLayout pass;
//
////    FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        email = findViewById(R.id.text_input_email);
//        pass = findViewById(R.id.text_input_pass);
//
//    }
//    private boolean validateEmail() {
//        String emailInput = email.getEditText().getText().toString().trim();
//        if(emailInput.isEmpty()) {
//            email.setError("Field can't be empty");
//            return false;
//        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//
//        }*/ else {
//            email.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validatePassword() {
//        String passInput = pass.getEditText().getText().toString().trim();
//
//        if (passInput.isEmpty()) {
//            pass.setError("Field can't be empty.");
//            return false;
//        } else if(passInput.length() < 5) {
//            pass.setError("Password to short.");
//        }
//
//        pass.setError(null);
//        return true;
//    }
//    private void confirmInput(View v) {
//
//        if(!validateEmail() | !validatePassword()) {
//            return;
//        }
//        String input = "Your email " + email.getEditText().getText().toString() + "\n was successfully registered !";
//        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
//    }
//}





