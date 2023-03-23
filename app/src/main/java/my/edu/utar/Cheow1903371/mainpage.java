package my.edu.utar.Cheow1903371;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class mainpage extends AppCompatActivity {

    private Button startGameBtn;
    private Button ScoresBtn;
    private Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        startGameBtn = findViewById(R.id.start_game_button);
        ScoresBtn = findViewById(R.id.high_scores_button);
        quitBtn = findViewById(R.id.quit_button);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        ScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHighScores();
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(mainpage.this, MainActivity.class);
        startActivity(intent);
    }

    private void viewHighScores() {
        Intent intent = new Intent(mainpage.this, TopScoresPage.class);
        startActivity(intent);
    }
}
