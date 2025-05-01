package com.example.headortail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        // 1 Player Button
        Button btnOnePlayer = findViewById(R.id.btnOnePlayer);
        btnOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ Toss এর আগে এখন OverSelectActivity তে যাবে
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // 2 Player Button
        Button btnTwoPlayer = findViewById(R.id.btnTwoPlayer);
        btnTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ Toss এর আগে এখন OverSelectActivity তে যাবে
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // Private Button
        Button btnPrivate = findViewById(R.id.btnPrivate);
        btnPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ Toss এর আগে এখন OverSelectActivity তে যাবে
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // ✅ Home Button Logic
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to MainActivity
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


