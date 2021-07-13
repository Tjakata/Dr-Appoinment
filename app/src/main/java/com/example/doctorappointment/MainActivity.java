package com.example.doctorappointment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public EditText mName, mSurname, mGender, mAge, mDate;
    private Button mButton, mButton2;
    private int day,month,year;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.firstname);
        mSurname = findViewById(R.id.lastname);
        mGender = findViewById(R.id.gender1);
        mAge = findViewById(R.id.age1);
        mDate = findViewById(R.id.date1);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String surname = mSurname.getText().toString();
                String gender = mGender.getText().toString();
                String age = mAge.getText().toString();
                String date = mDate.getText().toString();

                HashMap<String, String> patientMap = new HashMap<>();

                patientMap.put("Name", name);
                patientMap.put("Surname", surname);
                patientMap.put("Gender", gender);
                patientMap.put("Age", age);
                patientMap.put("Date", date);

                root.push().setValue(patientMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Data sent", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, dataActivity.class));
            }
        });
    }
}