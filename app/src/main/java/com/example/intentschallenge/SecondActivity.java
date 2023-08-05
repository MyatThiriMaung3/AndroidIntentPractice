package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etPhoneNumber, etWeb, etAddress;
    ImageView ivMan, ivWoman, ivBoi, ivGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etWeb = findViewById(R.id.etWeb);
        etAddress = findViewById(R.id.etAddress);
        ivMan = findViewById(R.id.ivMan);
        ivWoman = findViewById(R.id.ivWoman);
        ivBoi = findViewById(R.id.ivBoi);
        ivGirl = findViewById(R.id.ivGirl);

        ivMan.setOnClickListener(this);
        ivWoman.setOnClickListener(this);
        ivBoi.setOnClickListener(this);
        ivGirl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (etName.getText().toString().isEmpty() || etPhoneNumber.getText().toString().isEmpty()
                || etWeb.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty()) {
            Toast.makeText(SecondActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etPhoneNumber.getText().toString().trim());
            intent.putExtra("web", etWeb.getText().toString().trim());
            intent.putExtra("address", etAddress.getText().toString().trim());

            if (v.getId() == R.id.ivMan)
                intent.putExtra("mood", "man");
            else if (v.getId() == R.id.ivWoman)
                intent.putExtra("mood", "woman");
            else if (v.getId() == R.id.ivBoi)
                intent.putExtra("mood", "boi");
            else
                intent.putExtra("mood", "girl");

            setResult(RESULT_OK, intent);
            SecondActivity.this.finish();
        }
    }
}