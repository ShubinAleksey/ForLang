package com.example.forlang2.Data;

import com.example.forlang2.Model.PairModel;
import com.example.forlang2.Model.QuestionModel;
import com.example.forlang2.Model.TextModel;
import com.example.forlang2.Model.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public interface DataSource {

    interface Local{

        ArrayList<PairModel> getPairs();

        QuestionModel getRandomQuestionObj();

        ArrayList<PairModel> getPairsAnimals();

        QuestionModel getRandomQuestionObjAnimals();

        ArrayList<PairModel> getPairsClothes();

        QuestionModel getRandomQuestionObjClothes();

        ArrayList<PairModel> getPairsFood();

        QuestionModel getRandomQuestionObjFood();

        ArrayList<PairModel> getPairsGreetings();

        QuestionModel getRandomQuestionObjGreetings();

        ArrayList<PairModel> getPairsFraze();

        QuestionModel getRandomQuestionObjFraze();

        ArrayList<String> getAnswer();

        TextModel getRandomTextObj();

        TextModel getRandomTextObjAnimals();

        TextModel getRandomTextObjFood();

        TextModel getRandomTextObjGreetings();

        TextModel getRandomTextObjClothes();

        TextModel getRandomTextObjFraze();
    }

    interface Remote {

        FirebaseDatabase getDatabaseInstance();

        void setNewLanguage(String language);

        void setNewDictionary(String ArrayListWords);

        void setDailyXp(int xp);

        void setUserTotalXp(int xp);

        void setLastTimeVisited();

        void setDailyGoal(int dailyGoal);

        void setUserInfo(User userData);

        void setLessonComplete(String lesson, boolean completeness);

        void setLessonCompleteDate(String lesson);

        void getDailyGoal();

        void getDailyXp();

        void getWeekXp();

        void getLessonCompleted();
    }
}
