package com.example.forlang2.Activity.ChooseLesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.LearnText.LearnTextActivityFraze;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TapPairActivityFraze;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityFraze;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityFraze;

public class ChooseLessonFraze extends AppCompatActivity {
    Button buttonTS, buttonWT, buttonTP, buttonText;
    TextView txtView;
    ImageView imageView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselesson);
        buttonTS = findViewById(R.id.translate_sentece);
        buttonWT = findViewById(R.id.wordTaskButton);
        buttonTP = findViewById(R.id.tapPairButton);
        buttonText = findViewById(R.id.learnTextButton);
        imageView = findViewById(R.id.back_button);
        txtView = findViewById(R.id.txtValue);

        txtView.setText("Фразы");
        if (txtView.getText().equals("Фразы")) {
            buttonTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent trSen = new Intent(ChooseLessonFraze.this, TSTaskActivityFraze.class);
                    startActivity(trSen);
                    finish();
                }
            });
            buttonWT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wrdTas = new Intent(ChooseLessonFraze.this, WordTaskActivityFraze.class);
                    startActivity(wrdTas);
                    finish();
                }
            });
            buttonTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonFraze.this, TapPairActivityFraze.class);
                    startActivity(tpPair);
                    finish();
                }
            });
            buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonFraze.this, LearnTextActivityFraze.class);
                    startActivity(tpPair);
                    finish();
                }
            });
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnBack = new Intent(ChooseLessonFraze.this, LessonListActivity.class);
                startActivity(btnBack);
            }
        });
    }
}