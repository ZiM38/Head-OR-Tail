package com.example.headortail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseActivity extends AppCompatActivity {

    Button btnBat, btnBall;
    TextView tvTossResult;
    int totalOvers = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        btnBat = findViewById(R.id.btnBat);
        btnBall = findViewById(R.id.btnBall);
        tvTossResult = findViewById(R.id.tvTossResult);

        // TossActivity থেকে overs data আনো
        totalOvers = getIntent().getIntExtra("totalOvers", 1);

        // Toss জয়ী user, তাই show করবে
        tvTossResult.setText("🎉 You have won the Toss!");

        btnBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMatch(true);
            }
        });

        btnBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMatch(false);
            }
        });
    }

    private void startMatch(boolean userBatFirst) {
        Intent intent = new Intent(ChooseActivity.this, ResultActivity.class);
        intent.putExtra("userBatsFirst", userBatFirst);
        intent.putExtra("totalOvers", totalOvers);
        startActivity(intent);
        finish();
    }
}
