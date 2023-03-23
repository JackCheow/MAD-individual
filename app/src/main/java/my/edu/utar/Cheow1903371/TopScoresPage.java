package my.edu.utar.Cheow1903371;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TopScoresPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get the start game button and set its click listener
        Button startGameBtn = findViewById(R.id.start_game_button);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopScoresPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("TOP25Scores", MODE_PRIVATE);
        Map<String, ?> scoresMap = sharedPreferences.getAll();

        List<Score> scoresList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : scoresMap.entrySet()) {
            String name = entry.getKey();
            int score = Integer.parseInt(entry.getValue().toString());
            Score s = new Score(name, score);
            scoresList.add(s);
        }

        Collections.sort(scoresList);

        ListView listView = findViewById(R.id.nameScoreList);
        ArrayAdapter<Score> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, scoresList.subList(0, Math.min(scoresList.size(), 25)));
        listView.setAdapter(adapter);
    }
}

