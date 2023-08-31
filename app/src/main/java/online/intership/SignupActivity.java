package online.intership;


import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {

    Button  signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    EditText name,email,contact, password,confirmPassword,dob;

   // RadioButton male,female;
    RadioGroup gender;

    String scity="";
    String sgender;

    Spinner city;
    //String[] cityArray = {"Ahmedabad","Vadodara","Surat","Rajkot","Gandhinager","kadi","kalol",};

    ArrayList<String> arrayList;
    Calendar calendar;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db=openOrCreateDatabase("Internship",MODE_PRIVATE,null);
        String tableQuery="CREATE TABLE IF NOT EXISTS USER(USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(100),CONTECT INT(10),EMAIL VARCHAR(100),PASSWORD VARCHAR(10),GENDER VARCHAR(6),CITY VARCHAR(50),DOB VARCHAR(10))";
        db.execSQL(tableQuery);

        name = findViewById(R.id.main_name);
        email = findViewById(R.id.main_email);
        contact = findViewById(R.id.main_contect);
        password = findViewById(R.id.main_password);
        confirmPassword = findViewById(R.id.main_conformpassword);

        dob = findViewById(R.id.signup_dob);

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dataClick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                dob.setText(sdf.format(calendar.getTime()));
            }
        };

        dob.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(SignupActivity.this,dataClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

                return true;
            }
        });

                /*dob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog=new DatePickerDialog(SignupActivity.this,dataClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                        datePickerDialog.show();
                    }
                });*/


        city=findViewById(R.id.signup_city);

        arrayList=new ArrayList<>();

        /*for (int i=0;i<20;i++){
            arrayList.add("Index"+i);
        }*/
        arrayList.add("Select City");
        arrayList.add("Ahmedabad");
        arrayList.add("Gandhinager");
        arrayList.add("surat");
        arrayList.add("Jamnager");
        arrayList.add("Rajkot");
        arrayList.add("Kadi");

        ArrayAdapter adapter = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_list_item_1,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    scity="";
                }
                else {
                    scity=arrayList.get(i);
                    new CommanMethod(SignupActivity.this,scity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender=findViewById(R.id.button);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=findViewById(i);
                sgender = radioButton.getText().toString();
                new CommanMethod(SignupActivity.this,sgender);
            }
        });

        /*male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommanMethod(SignupActivity.this,"Male");

            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommanMethod(SignupActivity.this,"Female");
            }
        });*/

        signup = findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("")) {
                    name.setError("Name Required");
                } else if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                } else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact No. Required");
                } else if (contact.getText().toString().trim().length() < 10) {
                    contact.setError("Valid Contact No. Required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 Char Password Required");
                } else if (confirmPassword.getText().toString().trim().equals("")) {
                    confirmPassword.setError("Confirm Password Required");
                } else if (confirmPassword.getText().toString().trim().length() < 6) {
                    confirmPassword.setError("Min. 6 Char Confirm Password Required");
                } else if (!confirmPassword.getText().toString().trim().matches(password.getText().toString().trim())) {
                    confirmPassword.setError("Password Does Not Match");
                } else if (gender.getCheckedRadioButtonId() == -1) {
                    new CommanMethod(SignupActivity.this, "Please select Gender");
                } else if (scity.equals("")) {
                    new CommanMethod(SignupActivity.this, "please select city");
                } else if (dob.getText().toString().trim().equals("")) {
                    dob.setError("Please select date of birth");

                }
               /* else {
                    System.out.println("Signup Successfully");
                    new CommanMethod(SignupActivity.this, "Signup Successfully");
                    //Toast.makeText(SignupActivity.this,"Signup Successfully",Toast.LENGTH_SHORT).show();
                    new CommanMethod(view, "Signup Successfully");
                    //Snackbar.make(view, "Login Successfully", Snackbar.LENGTH_SHORT).show();
                    //new CommanMethod(SignupActivity.this, HomeActivity.class);
                    //intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    //startActivity(intent);*/
                else {
                        String selectQuery = "SELECT * FROM USER WHERE EMAIL='"+email.getText().toString()+"'OR CONTECT='"+contact.getText().toString()+"'";
                        Cursor cursor=db.rawQuery(selectQuery,null);
                        if(cursor.getCount()>0){
                            new CommanMethod(SignupActivity.this,"Email id/Contect no. Already Registered");

                            }
                            else {
                            String insertQuery = "INSERT INTO USER VALUE(NULL,'" + name.getText().toString() + "','" + contact.getText().toString() + "','" + email.getText().toString() + "','" + password.getText().toString() + "','" + sgender + "','" + scity + "','" + dob.getText().toString() + "')";
                            db.execSQL(insertQuery);

                            System.out.println("Signup Successfully");
                            new CommanMethod(SignupActivity.this,"Signup Successfully");
                        onBackPressed();
                            onBackPressed();
                    }

                }
            }
        });
    }
}
