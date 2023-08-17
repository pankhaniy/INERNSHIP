package online.intership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;



    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                //startActivity(intent);
                new CommanMethod(MainActivity.this, SignupActivity.class);
            }
        });


        login = findViewById(R.id.main_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 Char Password Required");
                } else {
                    System.out.println("Login Successfully");
                    //Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    new CommanMethod(MainActivity.this, "Login Successfully");
                    //Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_SHORT).show();
                    new CommanMethod(view,"Login Successfully");
                    //Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    //startActivity(intent);
                    new CommanMethod(MainActivity.this, HomeActivity.class);
                }
            }
        });

    }
}
