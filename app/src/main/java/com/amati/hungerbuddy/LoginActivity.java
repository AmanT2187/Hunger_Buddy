package com.amati.hungerbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btn1;
    EditText edt1 , edt2, edt3 , edt4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn1 = findViewById(R.id.btnLogin);
        edt1 = findViewById(R.id.editTextTextPersonName);
        edt2 = findViewById(R.id.editTextTextEmailAddress);
        edt3 = findViewById(R.id.editTextPhone);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edt1.getText().toString().equals("")) && !(edt2.getText().equals("")) && !(edt3.getText().equals(""))){

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }else{
                    edt1.setError("blank input");
                }

            }
        });
    }
}