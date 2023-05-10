package com.example.forlang2.Activity.ChooseLesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.LearnText.LearnTextActivityClothes;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TapPairActivityClothes;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityClothes;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityClothes;

public class ChooseLessonClothes extends AppCompatActivity {
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

        txtView.setText("Одежда");
        if (txtView.getText().equals("Одежда")) {
            buttonTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent trSen = new Intent(ChooseLessonClothes.this, TSTaskActivityClothes.class);
                    startActivity(trSen);
                    finish();
                }
            });
            buttonWT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wrdTas = new Intent(ChooseLessonClothes.this, WordTaskActivityClothes.class);
                    startActivity(wrdTas);
                    finish();
                }
            });
            buttonTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonClothes.this, TapPairActivityClothes.class);
                    startActivity(tpPair);
                    finish();
                }
            });
            buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLessonClothes.this, LearnTextActivityClothes.class);
                    startActivity(tpPair);
                    finish();
                }
            });
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnBack = new Intent(ChooseLessonClothes.this, LessonListActivity.class);
                startActivity(btnBack);
            }
        });
    }
}
