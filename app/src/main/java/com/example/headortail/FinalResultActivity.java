package com.example.headortail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalResultActivity extends AppCompatActivity {

    TextView tvFinalMessage, tvMatchResult;
    Button btnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        tvFinalMessage = findViewById(R.id.tvFinalMessage);
        tvMatchResult = findViewById(R.id.tvMatchResult);
        btnGoHome = findViewById(R.id.btnGoHome);

        // ResultActivity থেকে winner team আনো
        String winner = getIntent().getStringExtra("winner");

        if (winner != null) {
            tvFinalMessage.setText(winner + " Wins!");

            if (winner.equals("Team A")) {
                tvMatchResult.setText("🎉 You have won the Match!");
            } else {
                tvMatchResult.setText("😞 You have lost the Match.");
            }

        } else {
            tvFinalMessage.setText("Game Over!");
            tvMatchResult.setText("");
        }

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
