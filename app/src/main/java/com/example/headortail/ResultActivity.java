package com.example.headortail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private ImageView diceA, diceB;
    private TextView tvDiceResult, tvScoreBoard;
    private int totalBalls = 0;
    private int totalOvers = 1;
    private boolean userBatsFirst = true;
    private boolean isFirstInnings = true;

    private int userScore = 0;
    private int aiScore = 0;
    private int targetScore = 0;

    private boolean isInningsOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        totalOvers = getIntent().getIntExtra("totalOvers", 1);
        userBatsFirst = getIntent().getBooleanExtra("userBatsFirst", true);

        diceA = findViewById(R.id.diceA);
        diceB = findViewById(R.id.diceB);
        tvDiceResult = findViewById(R.id.tvDiceResult);
        tvScoreBoard = findViewById(R.id.tvScoreBoard);
        Button btnRestart = findViewById(R.id.btnRestart);

        int[] ballIds = {
                R.id.ball1, R.id.ball2, R.id.ball3,
                R.id.ball4, R.id.ball5, R.id.ball6
        };

        for (int i = 0; i < ballIds.length; i++) {
            int runValue = i + 1;
            Button ballBtn = findViewById(ballIds[i]);
            ballBtn.setOnClickListener(v -> {
                rollDice(runValue);
            });
        }

        Button btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(v -> {
            rollDice(0);
        });

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        updateScoreBoard();
    }

    private void rollDice(int userGuess) {
        if (isInningsOver) return; // innings শেষ হলে আর খেলা যাবে না

        Random random = new Random();
        int diceNumber = random.nextInt(6) + 1;

        int diceImageRes = getResources().getIdentifier("dice" + diceNumber, "drawable", getPackageName());
        diceA.setImageResource(diceImageRes);
        diceB.setImageResource(diceImageRes);
        tvDiceResult.setText(String.valueOf(diceNumber));

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diceA.startAnimation(rotate);
        diceB.startAnimation(rotate);

        boolean isOut = (userGuess == diceNumber);

        if (isFirstInnings) {
            if (userBatsFirst) {
                if (isOut) {
                    // User out → innings over
                    isFirstInnings = false;
                    isInningsOver = false;
                    targetScore = userScore + 1;
                    totalBalls = 0;
                    tvScoreBoard.setText("OUT!\nTarget for AI: " + targetScore);
                } else {
                    userScore += userGuess;
                    totalBalls++;
                }
            } else {
                if (isOut) {
                    isFirstInnings = false;
                    isInningsOver = false;
                    targetScore = aiScore + 1;
                    totalBalls = 0;
                    tvScoreBoard.setText("OUT!\nTarget for You: " + targetScore);
                } else {
                    aiScore += userGuess;
                    totalBalls++;
                }
            }

            // Check if overs done
            if (totalBalls >= totalOvers * 6) {
                isFirstInnings = false;
                isInningsOver = false;
                targetScore = userBatsFirst ? userScore + 1 : aiScore + 1;
                totalBalls = 0;
                tvScoreBoard.setText("Innings Over\nTarget: " + targetScore);
            }

        } else {
            // Second Innings
            if (userBatsFirst) {
                if (isOut) {
                    // AI out before reaching target
                    endGame("Team A"); // User wins
                    return;
                } else {
                    aiScore += userGuess;
                    totalBalls++;
                    if (aiScore >= targetScore) {
                        endGame("Team B"); // AI wins
                        return;
                    }
                }
            } else {
                if (isOut) {
                    // User out before reaching target
                    endGame("Team B"); // AI wins
                    return;
                } else {
                    userScore += userGuess;
                    totalBalls++;
                    if (userScore >= targetScore) {
                        endGame("Team A"); // User wins
                        return;
                    }
                }
            }

            // If overs done and target not reached
            if (totalBalls >= totalOvers * 6) {
                if (userBatsFirst) {
                    endGame(aiScore >= targetScore ? "Team B" : "Team A");
                } else {
                    endGame(userScore >= targetScore ? "Team A" : "Team B");
                }
            }
        }

        updateScoreBoard();
    }

    private void endGame(String winnerTeam) {
        isInningsOver = true;
        Intent intent = new Intent(ResultActivity.this, FinalResultActivity.class);
        intent.putExtra("winner", winnerTeam);
        startActivity(intent);
        finish();
    }

    private void updateScoreBoard() {
        int overs = totalBalls / 6;
        int balls = totalBalls % 6;

        String battingTeam;
        int currentScore;
        if (isFirstInnings) {
            battingTeam = userBatsFirst ? "Team A (You)" : "Team B (AI)";
            currentScore = userBatsFirst ? userScore : aiScore;
        } else {
            battingTeam = userBatsFirst ? "Team B (AI)" : "Team A (You)";
            currentScore = userBatsFirst ? aiScore : userScore;
        }

        String extra = isFirstInnings ? "" : "\nTarget: " + targetScore;

        tvScoreBoard.setText(
                battingTeam + "\nRuns: " + currentScore +
                        "\nOvers: " + overs + "." + balls + extra
        );
    }
}



