package com.example.forlang2.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.forlang2.Activity.LearnText.LearnTextActivity;
import com.example.forlang2.Activity.LearnText.LearnTextActivityAnimals;
import com.example.forlang2.Activity.LearnText.LearnTextActivityClothes;
import com.example.forlang2.Activity.LearnText.LearnTextActivityFood;
import com.example.forlang2.Activity.LearnText.LearnTextActivityFraze;
import com.example.forlang2.Activity.LearnText.LearnTextActivityGreetings;
import com.example.forlang2.Activity.LessonCompletedActivity.LessonCompletedActivity;
import com.example.forlang2.Tasks.TapPairActivity;
import com.example.forlang2.Tasks.TapPairActivityAnimals;
import com.example.forlang2.Tasks.TapPairActivityClothes;
import com.example.forlang2.Tasks.TapPairActivityFood;
import com.example.forlang2.Tasks.TapPairActivityGreetings;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivity;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityAnimals;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityClothes;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityFood;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityFraze;
import com.example.forlang2.Tasks.TranslateSentence.TSTaskActivityGreetings;
import com.example.forlang2.Tasks.WordTask.WordTaskActivity;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityAnimals;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityClothes;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityFood;
import com.example.forlang2.Tasks.WordTask.WordTaskActivityGreetings;

import java.util.ArrayList;
import java.util.Random;

public class ActivityNavigation {

    static ActivityNavigation INSTANCE;

    Context context;

    ArrayList<Class> activities = new ArrayList<>();
    ArrayList<Class> activitiesFraze = new ArrayList<>();
    ArrayList<Class> activitiesAnimals = new ArrayList<>();
    ArrayList<Class> activitiesFood = new ArrayList<>();
    ArrayList<Class> activitiesClothes = new ArrayList<>();
    ArrayList<Class> activitiesGreetings = new ArrayList<>();

    Random random = new Random();

    public ActivityNavigation(Context context) {
        this.context = context;
        initData();
        initDataFraze();
        initDataFood();
        initDataAnimals();
        initDataGreetings();
        initDataClothes();
    }

    public static ActivityNavigation getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ActivityNavigation(context);
        }

        return INSTANCE;
    }

    private void initData() {
        activities.add(WordTaskActivity.class);
        activities.add(TSTaskActivity.class);
        activities.add(TapPairActivity.class);
        activities.add(LearnTextActivity.class);
    }

    private void initDataFraze() {
        activitiesFraze.add(WordTaskActivity.class);
        activitiesFraze.add(TSTaskActivityFraze.class);
        activitiesFraze.add(TapPairActivity.class);
        activitiesFraze.add(LearnTextActivityFraze.class);
    }

    private void initDataFood() {
        activitiesFood.add(WordTaskActivityFood.class);
        activitiesFood.add(TSTaskActivityFood.class);
        activitiesFood.add(TapPairActivityFood.class);
        activitiesFood.add(LearnTextActivityFood.class);
    }

    private void initDataClothes() {
        activitiesClothes.add(WordTaskActivityClothes.class);
        activitiesClothes.add(TSTaskActivityClothes.class);
        activitiesClothes.add(TapPairActivityClothes.class);
        activitiesClothes.add(LearnTextActivityClothes.class);
    }

    private void initDataAnimals() {
        activitiesAnimals.add(WordTaskActivityAnimals.class);
        activitiesAnimals.add(TSTaskActivityAnimals.class);
        activitiesAnimals.add(TapPairActivityAnimals.class);
        activitiesAnimals.add(LearnTextActivityAnimals.class);
    }

    private void initDataGreetings() {
        activitiesGreetings.add(WordTaskActivityGreetings.class);
        activitiesGreetings.add(TSTaskActivityGreetings.class);
        activitiesGreetings.add(TapPairActivityGreetings.class);
        activitiesGreetings.add(LearnTextActivityGreetings.class);
    }

    public void takeToRandomTask() {

        int randomIndex = random.nextInt(activities.size());

        Intent intent = new Intent(context, activities.get(randomIndex));
        context.startActivity(intent);
    }

    public void takeToRandomTaskFraze() {

        int randomIndex = random.nextInt(activitiesFraze.size());

        Intent intent = new Intent(context, activitiesFraze.get(randomIndex));
        context.startActivity(intent);
    }

    public void takeToRandomTaskFood() {

        int randomIndex = random.nextInt(activitiesFood.size());

        Intent intent = new Intent(context, activitiesFood.get(randomIndex));
        context.startActivity(intent);
    }

    public void takeToRandomTaskAnimals() {

        int randomIndex = random.nextInt(activitiesAnimals.size());

        Intent intent = new Intent(context, activitiesAnimals.get(randomIndex));
        context.startActivity(intent);
    }

    public void takeToRandomTaskClothes() {

        int randomIndex = random.nextInt(activitiesClothes.size());

        Intent intent = new Intent(context, activitiesClothes.get(randomIndex));
        context.startActivity(intent);
    }

    public void takeToRandomTaskGreetings() {

        int randomIndex = random.nextInt(activitiesGreetings.size());

        Intent intent = new Intent(context, activitiesGreetings.get(randomIndex));
        context.startActivity(intent);
    }


    public void lessonCompleted() {
        Intent intent = new Intent(context, LessonCompletedActivity.class);
        context.startActivity(intent);
    }
}