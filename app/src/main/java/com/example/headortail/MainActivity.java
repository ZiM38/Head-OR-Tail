package com.example.headortail;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer bgMusic;  // 🔊 Background music variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔊 Background music start
        bgMusic = MediaPlayer.create(this, R.raw.bg_music);
        bgMusic.setLooping(true);
        bgMusic.start();

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ User ID create and pass to GameActivity
                String userId = "guest12345"; // এইটা ভবিষ্যতে dynamic করা যাবে
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("USER_ID", userId); // ✅ User ID পাঠানো হচ্ছে
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 🔇 Release background music when activity is destroyed
        if (bgMusic != null) {
            bgMusic.release();
            bgMusic = null;
        }
    }
}


