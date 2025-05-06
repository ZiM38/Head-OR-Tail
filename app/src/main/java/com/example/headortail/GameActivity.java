package com.example.headortail;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    TextView tvUserId;  // ⬅️ New line
    ImageView imgProfile; // ⬅️ New line

    // 🔊 Tap sound method
    private void playTapSound() {
        MediaPlayer tap = MediaPlayer.create(this, R.raw.tap_click);
        tap.start();
        tap.setOnCompletionListener(MediaPlayer::release);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        // ✅ Find Views for userId and profile
        tvUserId = findViewById(R.id.tvUserId);
        imgProfile = findViewById(R.id.imgProfile);

        // ✅ Receive and display userId from intent
        Intent intent = getIntent();
        String userId = intent.getStringExtra("USER_ID");
        if (userId != null && !userId.isEmpty()) {
            tvUserId.setText(userId);
        } else {
            tvUserId.setText("guest12345"); // default fallback
        }

        // 1 Player Button
        Button btnOnePlayer = findViewById(R.id.btnOnePlayer);
        btnOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTapSound(); // 🔊 Tap sound
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // 2 Player Button
        Button btnTwoPlayer = findViewById(R.id.btnTwoPlayer);
        btnTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTapSound(); // 🔊 Tap sound
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // Private Button
        Button btnPrivate = findViewById(R.id.btnPrivate);
        btnPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTapSound(); // 🔊 Tap sound
                Intent intent = new Intent(GameActivity.this, OverSelectActivity.class);
                startActivity(intent);
            }
        });

        // ✅ Home Button Logic
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTapSound(); // 🔊 Tap sound
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


