package com.example.forlang2.Activity.lessonlistactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonAnimals;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonClothes;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonFood;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonFraze;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonGreetings;
import com.example.forlang2.Activity.Dictionary.DictionaryActivity;
import com.example.forlang2.Activity.Translator.TranslatorActivity;
import com.example.forlang2.R;

public class LessonListActivity extends AppCompatActivity {
    TextView txtView, txtViewFood, txtViewClothes, txtViewAnimals, txtViewFraze, txtViewGreetings;
    Button btnTranslator, btnDictionary;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        txtView = findViewById(R.id.txtBase);
        txtViewFood = findViewById(R.id.txtFood);
        txtViewClothes = findViewById(R.id.txtClothes);
        txtViewAnimals = findViewById(R.id.txtAnimals);
        txtViewFraze = findViewById(R.id.txtFraze);
        txtViewGreetings = findViewById(R.id.txtGreetings);
        btnTranslator = findViewById(R.id.translator);
        btnDictionary = findViewById(R.id.btnDictionary);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLesson.class);
                startActivity(txtBases);
                finish();
            }
        });

        txtViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLessonFood.class);
                startActivity(txtBases);
                finish();
            }
        });

        txtViewClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLessonClothes.class);
                startActivity(txtBases);
                finish();
            }
        });

        txtViewAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLessonAnimals.class);
                startActivity(txtBases);
                finish();
            }
        });

        txtViewFraze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLessonFraze.class);
                startActivity(txtBases);
                finish();
            }
        });

        txtViewGreetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtBases = new Intent(LessonListActivity.this, ChooseLessonGreetings.class);
                startActivity(txtBases);
                finish();
            }
        });

        btnTranslator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonListActivity.this, TranslatorActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonListActivity.this, DictionaryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
