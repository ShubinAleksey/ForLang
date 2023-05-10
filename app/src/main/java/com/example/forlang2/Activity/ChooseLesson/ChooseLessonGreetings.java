package com.example.forlang2.Activity.ChooseLesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.LearnText.LearnTextActivityGreetings;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TapPairActivityGreetings;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityGreetings;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityGreetings;

public class ChooseLessonGreetings extends AppCompatActivity {
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

        txtView.setText("Приветствия");
        if (txtView.getText().equals("Приветствия")) {
            buttonTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent trSen = new Intent(ChooseLessonGreetings.this, TSTaskActivityGreetings.class);
                    startActivity(trSen);
                    finish();
                }
            });
            buttonWT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wrdTas = new Intent(ChooseLessonGreetings.this, WordTaskActivityGreetings.class);
                    startActivity(wrdTas);
                    finish();
                }
            });
            buttonTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonGreetings.this, TapPairActivityGreetings.class);
                    startActivity(tpPair);
                    finish();
                }
            });
            buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonGreetings.this, LearnTextActivityGreetings.class);
                    startActivity(tpPair);
                    finish();
                }
            });
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnBack = new Intent(ChooseLessonGreetings.this, LessonListActivity.class);
                startActivity(btnBack);
            }
        });
    }
}
