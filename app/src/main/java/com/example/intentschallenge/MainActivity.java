package com.example.intentschallenge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvApp;
    Button btnCreateContact;
    ImageView ivPerson, ivCall, ivMap, ivWeb;
    ActivityResultLauncher<Intent> activityResultLauncher;
    String name = "", number = "", web = "", address = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvApp = findViewById(R.id.tvApp);
        btnCreateContact = findViewById(R.id.btnCreateContact);
        ivPerson = findViewById(R.id.ivPerson);
        ivCall = findViewById(R.id.ivCall);
        ivWeb = findViewById(R.id.ivWeb);
        ivMap = findViewById(R.id.ivMap);

        ivPerson.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivMap.setVisibility(View.GONE);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , result -> {
                    int resultCode = result.getResultCode();
                    Intent data = result.getData();

                    if (resultCode == RESULT_OK && data != null) {
                        name = data.getStringExtra("name");
                        number = data.getStringExtra("number");
                        web = data.getStringExtra("web");
                        address = data.getStringExtra("address");
                        mood = data.getStringExtra("mood");

                        if (mood.equals("man"))
                            ivPerson.setImageResource(R.drawable.man);
                        else if (mood.equals("woman"))
                            ivPerson.setImageResource(R.drawable.woman);
                        else if (mood.equals("boi"))
                            ivPerson.setImageResource(R.drawable.boi);
                        else
                            ivPerson.setImageResource(R.drawable.girl);

                        ivPerson.setVisibility(View.VISIBLE);
                        ivCall.setVisibility(View.VISIBLE);
                        ivWeb.setVisibility(View.VISIBLE);
                        ivMap.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(this, "No Data Received.", Toast.LENGTH_SHORT).show();
                    }
        });

        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.intentschallenge.SecondActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
                startActivity(intent);
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0.0?q=" + address));
                startActivity(intent);
            }
        });
    }
}