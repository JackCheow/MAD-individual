package my.edu.utar.Cheow1903371;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<HighlightView> views;
    private int curLevel;
    private int score;
    private TextView timerTextView;
    private int timeLeft = 25000;
    private CountDownTimer timer;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);

        views = new ArrayList<>();
        curLevel = 1;
        score = 0;
        controlViews(4);
        startGame();
        startTimer();

        Button quitBtn = findViewById(R.id.quitBtn);

        quitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, mainpage.class);
            startActivity(intent);
        });
    }
    private void startTimer() {
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished;
                timerTextView.setText("Time left: " + timeLeft / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Times Up!");
                nameInput();
            }
        };
        timer.start();
    }


    private void controlViews(int numViews) {
        LinearLayout container = findViewById(R.id.container);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        container.removeAllViews();
        views.clear();
        for (int i = 0; i < numViews; i++) {
            HighlightView view = new HighlightView(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view.isHighlighted) {
                        score++;
                        scoreTextView.setText("Score " + score);
                        view.unhighlight();
                        highlightRandomView();
                    }
                }
            });
            views.add(view);
            container.addView(view);
        }
    }


    private void highlightRandomView() {
        for (HighlightView view : views) {
            view.unhighlight();
        }
        int randomIndex = new Random().nextInt(views.size());
        HighlightView randomView = views.get(randomIndex);
        randomView.highlight();
    }

    private void startGame() {
        TextView levelTextView = findViewById(R.id.level_text_view);
        levelTextView.setText("Level " + curLevel);
        final Handler handler = new Handler();
        handler.postDelayed(this::endLevel, 5000); // level lasts 5 seconds
        int numViews = curLevel == 1 ? 4 : curLevel == 2 ? 9 :
                curLevel == 3 ? 16 : curLevel == 4 ? 25 :
                        curLevel == 5 ? 36 : curLevel * curLevel;
        controlViews(numViews);
        highlightRandomView();
    }


    private void endLevel() {
        curLevel++;
        if (curLevel <= 5) {
            startGame();
        } else {
            nameInput();
        }
    }

    public void nameInput() {
        final EditText showName = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You've completed the game!")
                .setMessage("Please enter your name to save your score:");
        builder.setView(showName);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = showName.getText().toString();
                save(name, score);
                showHighScores();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    private void save(String name, int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("TOP25Scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, score);
        editor.apply();
    }

    private void showHighScores() {
        Intent intent = new Intent(this, TopScoresPage.class);
        startActivity(intent);
    }
}