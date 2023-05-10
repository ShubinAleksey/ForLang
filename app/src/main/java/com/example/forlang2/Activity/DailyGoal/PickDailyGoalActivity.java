package com.example.forlang2.Activity.DailyGoal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.SelectLanguage.ChooseLang;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivity;
import com.example.forlang2.Utils.Injection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickDailyGoalActivity extends AppCompatActivity {
    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.casual_goal)
    RadioButton casualGoal;

    @BindView(R.id.regular_goal)
    RadioButton regularGoal;

    @BindView(R.id.serious_goal)
    RadioButton seriousGoal;

    @BindView(R.id.insane_goal)
    RadioButton insaneGoal;

    @BindView(R.id.continue_button)
    Button continueButton;

    ArrayList<RadioButton> radioButtonArray = new ArrayList<>();

    int checkedButton;

    Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goal);

        ButterKnife.bind(this);

        repository = Injection.provideRepository();

        setRadioButton();
        regularGoal.setChecked(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trSen = new Intent(PickDailyGoalActivity.this, ChooseLang.class);
                startActivity(trSen);
                finish();
            }
        });

        continueListener();
    }

    // Метод для установки значений радио кнопки
    private void setRadioButton() {
        // Добавление элементов массива для радио кнопки
        radioButtonArray.add(casualGoal);
        radioButtonArray.add(regularGoal);
        radioButtonArray.add(seriousGoal);
        radioButtonArray.add(insaneGoal);

        // Цикл на индексацию конкретного элемента радио кнопки
        for (int i = 0; i < radioButtonArray.size(); i++) {
            final int finalIndex = i;
            radioButtonArray.get(i).setOnClickListener(new View.OnClickListener() {
                // Метод нажатия на элемент радио кнопки
                @Override
                public void onClick(View v) {
                    checkedButton = finalIndex;
                    ArrayList<Integer> buttonIdx = new ArrayList<>();
                    buttonIdx.add(0);
                    buttonIdx.add(1);
                    buttonIdx.add(2);
                    buttonIdx.add(3);
                    buttonIdx.remove(finalIndex);
                    radioButtonArray.get(finalIndex).setChecked(true);
                    for (int index : buttonIdx) {
                        radioButtonArray.get(index).setChecked(false);
                    }
                }
            });
        }
    }

    private void continueListener() {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dailyGoal = 2;
                if(checkedButton == 3) dailyGoal = 50; else dailyGoal = (checkedButton + 1) * 10;
                repository.setDailyGoal(dailyGoal);
                Intent d = new Intent(PickDailyGoalActivity.this, LessonListActivity.class);
                startActivity(d);
            }
        });
    }
}
