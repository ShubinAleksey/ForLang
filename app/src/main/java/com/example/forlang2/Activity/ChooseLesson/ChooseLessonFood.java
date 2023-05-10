package com.example.forlang2.Activity.ChooseLesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.LearnText.LearnTextActivityFood;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TapPairActivityFood;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityFood;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityFood;

public class ChooseLessonFood extends AppCompatActivity {
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

        txtView.setText("Еда");
        if (txtView.getText().equals("Еда")) {
            buttonTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent trSen = new Intent(ChooseLessonFood.this, TSTaskActivityFood.class);
                    startActivity(trSen);
                    finish();
                }
            });
            buttonWT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wrdTas = new Intent(ChooseLessonFood.this, WordTaskActivityFood.class);
                    startActivity(wrdTas);
                    finish();
                }
            });
            buttonTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonFood.this, TapPairActivityFood.class);
                    startActivity(tpPair);
                    finish();
                }
            });
            buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonFood.this, LearnTextActivityFood.class);
                    startActivity(tpPair);
                    finish();
                }
            });
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnBack = new Intent(ChooseLessonFood.this, LessonListActivity.class);
                startActivity(btnBack);
            }
        });
    }
}