package com.example.headortail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OverSelectActivity extends AppCompatActivity {

    RadioGroup rgOvers;
    Button btnNextToToss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_select);

        rgOvers = findViewById(R.id.rgOvers);
        btnNextToToss = findViewById(R.id.btnNextToToss);

        btnNextToToss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgOvers.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(OverSelectActivity.this, "Please select number of overs", Toast.LENGTH_SHORT).show();
                    return;
                }

                int overs = 1; // default

                if (selectedId == R.id.rbOver2) {
                    overs = 2;
                }

                // Pass overs to next activity (TossActivity)
                Intent intent = new Intent(OverSelectActivity.this, TossActivity.class);
                intent.putExtra("totalOvers", overs);
                startActivity(intent);
                finish();
            }
        });
    }
}
