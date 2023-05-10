package com.example.forlang2.Activity.ChooseLesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.LearnText.LearnTextActivity;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TapPairActivity;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivity;
import com.example.forlang2.Tasks.WordTask.WordTaskActivity;

public class ChooseLesson extends AppCompatActivity {
    Button buttonTS, buttonWT, buttonTP, buttonText;
    TextView txtViewValue;
    ImageView imageViewBack;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselesson);
        buttonTS = findViewById(R.id.translate_sentece);
        buttonWT = findViewById(R.id.wordTaskButton);
        buttonTP = findViewById(R.id.tapPairButton);
        buttonText = findViewById(R.id.learnTextButton);
        imageViewBack = findViewById(R.id.back_button);
        txtViewValue = findViewById(R.id.txtValue);

        txtViewValue.setText("Базовые");
        if (txtViewValue.getText().equals("Базовые")) {
            buttonTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent trSen = new Intent(ChooseLesson.this, TSTaskActivity.class);
                    startActivity(trSen);
                    finish();
                }
            });
            buttonWT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wrdTas = new Intent(ChooseLesson.this, WordTaskActivity.class);
                    startActivity(wrdTas);
                    finish();
                }
            });
            buttonTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tpPair = new Intent(ChooseLesson.this, TapPairActivity.class);
                    startActivity(tpPair);
                    finish();
                }
            });
            buttonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent txtLrn = new Intent(ChooseLesson.this, LearnTextActivity.class);
                    startActivity(txtLrn);
                    finish();
                }
            });
        }

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnBack = new Intent(ChooseLesson.this, LessonListActivity.class);
                startActivity(btnBack);
            }
        });
    }
}
