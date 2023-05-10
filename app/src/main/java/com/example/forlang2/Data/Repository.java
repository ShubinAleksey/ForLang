package com.example.forlang2.Data;

import androidx.annotation.NonNull;

import com.example.forlang2.Model.PairModel;
import com.example.forlang2.Model.QuestionModel;
import com.example.forlang2.Model.TextModel;
import com.example.forlang2.Model.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Repository implements DataSource.Local, DataSource.Remote {
    private static Repository INSTANCE;

    private final DataSource.Local dataSourceLocal;
    private final DataSource.Remote dataSourceRemote;

    private Repository(
            @NonNull DataSource.Local questionDataSource,
            @NonNull DataSource.Remote dataSourceRemote) {
        this.dataSourceLocal = questionDataSource;
        this.dataSourceRemote = dataSourceRemote;

    }

    public static Repository getInstance(
            DataSource.Local dataSourceLocal,
            DataSource.Remote dataSourceRemote) {

        if (INSTANCE == null) {

            INSTANCE = new Repository(dataSourceLocal, dataSourceRemote);
            dataSourceRemote.getDatabaseInstance();
        }

        return INSTANCE;
    }

    @Override
    public FirebaseDatabase getDatabaseInstance() {
        return dataSourceRemote.getDatabaseInstance();
    }

    @Override
    public ArrayList<PairModel> getPairs() {
        return dataSourceLocal.getPairs();
    }

    @Override
    public QuestionModel getRandomQuestionObj() {
        return dataSourceLocal.getRandomQuestionObj();
    }

    @Override
    public ArrayList<PairModel> getPairsAnimals() {
        return dataSourceLocal.getPairsAnimals();
    }

    @Override
    public QuestionModel getRandomQuestionObjAnimals() {
        return dataSourceLocal.getRandomQuestionObjAnimals();
    }

    @Override
    public ArrayList<PairModel> getPairsClothes() {
        return dataSourceLocal.getPairsClothes();
    }

    @Override
    public QuestionModel getRandomQuestionObjClothes() {
        return dataSourceLocal.getRandomQuestionObjClothes();
    }

    @Override
    public ArrayList<PairModel> getPairsFood() {
        return dataSourceLocal.getPairsFood();
    }

    @Override
    public QuestionModel getRandomQuestionObjFood() {
        return dataSourceLocal.getRandomQuestionObjFood();
    }

    @Override
    public ArrayList<PairModel> getPairsGreetings() {
        return dataSourceLocal.getPairsGreetings();
    }

    @Override
    public QuestionModel getRandomQuestionObjGreetings() {
        return dataSourceLocal.getRandomQuestionObjGreetings();
    }

    @Override
    public ArrayList<PairModel> getPairsFraze() {
        return dataSourceLocal.getPairsFraze();
    }

    @Override
    public QuestionModel getRandomQuestionObjFraze() {
        return dataSourceLocal.getRandomQuestionObjFraze();
    }

    @Override
    public TextModel getRandomTextObj() {
        return dataSourceLocal.getRandomTextObj();
    }

    @Override
    public TextModel getRandomTextObjFood() {
        return dataSourceLocal.getRandomTextObjFood();
    }

    @Override
    public TextModel getRandomTextObjClothes() {
        return dataSourceLocal.getRandomTextObjClothes();
    }

    @Override
    public TextModel getRandomTextObjGreetings() {
        return dataSourceLocal.getRandomTextObjGreetings();
    }

    @Override
    public TextModel getRandomTextObjAnimals() {
        return dataSourceLocal.getRandomTextObjAnimals();
    }

    @Override
    public TextModel getRandomTextObjFraze() {
        return dataSourceLocal.getRandomTextObjFraze();
    }

    @Override
    public ArrayList<String> getAnswer() {
        return dataSourceLocal.getAnswer();
    }

    @Override
    public void setNewLanguage(String language) {
        dataSourceRemote.setNewLanguage(language);
    }

    @Override
    public void setNewDictionary(String ArrayListWords) {
        dataSourceRemote.setNewDictionary(ArrayListWords);
    }

    @Override
    public void setDailyXp(int xp) {
        dataSourceRemote.setDailyXp(xp);
    }

    @Override
    public void setUserTotalXp(int xp) {
        dataSourceRemote.setUserTotalXp(xp);
    }

    @Override
    public void setLastTimeVisited() {
        dataSourceRemote.setLastTimeVisited();
    }

    @Override
    public void setDailyGoal(int dailyGoal) {
        dataSourceRemote.setDailyGoal(dailyGoal);
    }

    @Override
    public void setUserInfo(User userData) {
        dataSourceRemote.setUserInfo(userData);
    }

    @Override
    public void setLessonComplete(String lesson, boolean completeness) {
        dataSourceRemote.setLessonComplete(lesson, completeness);
    }

    @Override
    public void setLessonCompleteDate(String lesson) {
        dataSourceRemote.setLessonCompleteDate(lesson);
    }

    @Override
    public void getDailyGoal() {
        dataSourceRemote.getDailyGoal();
    }

    @Override
    public void getDailyXp() {
        dataSourceRemote.getDailyXp();
    }

    @Override
    public void getWeekXp() {
        dataSourceRemote.getWeekXp();
    }

    @Override
    public void getLessonCompleted() {
        dataSourceRemote.getLessonCompleted();
    }
}
