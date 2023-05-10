package com.example.forlang2.Activity.LessonCompletedActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;

public class LessonCompletedActivity extends AppCompatActivity {
    AppCompatButton btnReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_completed);

        btnReturn = findViewById(R.id.btnBack);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonCompletedActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });
    }
}
