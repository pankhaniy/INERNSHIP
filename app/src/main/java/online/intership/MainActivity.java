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

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("login Successfully");
                Toast.makeText(MainActivity.this,"login successfully",Toast.LENGTH_SHORT).show();
                Snackbar.make(view,"login successfully",1000).show();

                email=findViewById(R.id.main_email);
                password=findViewById(R.id.main_password);
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (email.getText().toString().trim().equals("")){
                            email.setError("email ID Required");
                        } else if (!email.getText().toString().trim().matches(emailpattern)) {
                            email.setError("valid email ID");

                        } else if (password.getText().toString().trim().equals("")) {
                            password.setError("Password Required");

                        } else if (password.getText().toString().trim().length()<6) {
                            password.setError("valid password");

                        } else {
                            System.out.println("login successfully");
                            Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_LONG).show();
                            Snackbar.make(view,"login Successfully",Snackbar.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}