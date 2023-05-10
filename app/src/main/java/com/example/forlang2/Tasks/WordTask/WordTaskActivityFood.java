package com.example.forlang2.Tasks.WordTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonFood;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.Model.QuestionModel;
import com.example.forlang2.R;
import com.example.forlang2.Tasks.CustomWord;
import com.example.forlang2.Utils.ActivityNavigation;
import com.example.forlang2.Utils.Injection;
import com.nex3z.flowlayout.FlowLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordTaskActivityFood extends AppCompatActivity {

    private static final String TAG = "WordTaskActivity";

    @BindView(R.id.sentence_line)
    FlowLayout sentenceLine;

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.answer_container)
    RelativeLayout answerContainer;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.ttSTextView)
    ImageView ttSTextView;

    TextToSpeech ttS;

    private CustomWord customWord;

    private CustomLayout customLayout;

    QuestionModel questionModel;

    ArrayList<String> words = new ArrayList<>();

    ArrayList<String> answers = new ArrayList<>();

    Random random = new Random();

    int progressBarValue;

    Context context = WordTaskActivityFood.this;

    ActivityNavigation activityNavigation;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_task);

        ButterKnife.bind(this);

        initCustomLayout();
        initData();

        ttS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    ttS.setLanguage(Locale.US);
                }
            }
        });
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !customLayout.empty()) {

                customWord = (CustomWord) view;
                customWord.goToViewGroup(customLayout, sentenceLine);

                checkChildCount();

                return true;
            }

            return false;
        }
    }

    private void initData() {

        checkButton.setEnabled(false);

        repository = Injection.provideRepository();

        answers = repository.getAnswer();
        questionModel = repository.getRandomQuestionObjFood();

        Hawk.init(this).build();

        progressBarValue = 0;

        if (Hawk.get("progressBarValue") != null) {

            progressBarValue = Hawk.get("progressBarValue");

            progressBar.setProgress(progressBarValue);
        }

        tvQuestion.setText(questionModel.getQuestion());

        ttSTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = tvQuestion.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ttS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        randomizeCustomWords();

        activityNavigation = ActivityNavigation.getInstance(this);

        checkAnswer();
    }

    private void initCustomLayout() {

        customLayout = new CustomLayout(this);
        customLayout.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        answerContainer.addView(customLayout, params);
    }

    private void checkAnswer() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder answer = new StringBuilder();

                if (checkButton.getText().equals("проверить")) {

                    for (int i = 0; i < sentenceLine.getChildCount(); i++) {

                        customWord = (CustomWord) sentenceLine.getChildAt(i);

                        answer.append(customWord.getText().toString() + " ");
                    }

                    if (answer.toString().equals(questionModel.getAnswer() + " ")) {

                        Toast.makeText(WordTaskActivityFood.this, "Совершенно верно!", Toast.LENGTH_SHORT).show();

                        progressBarValue += 20;

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockViews();

                    } else {

                        Toast.makeText(WordTaskActivityFood.this, "Неправильный ответ. \n" + questionModel.getAnswer(), Toast.LENGTH_SHORT).show();

                        if (progressBarValue > 20) {

                            progressBarValue -= 20;

                        } else {

                            progressBarValue = 0;
                        }

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockViews();
                    }

                    checkButton.setText("продолжить");
                    checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));

                } else if (checkButton.getText().equals("продолжить")) {

                    if (progressBarValue < 100) {

                        activityNavigation.takeToRandomTaskFood();

                    } else {

                        progressBarValue = 0;

                        activityNavigation.lessonCompleted();

                        Hawk.put("progressBarValue", progressBarValue);
                    }
                }
            }
        });
    }

    private void checkChildCount() {

        if (sentenceLine.getChildCount() > 0) {

            checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
            checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));

            checkButton.setEnabled(true);

        } else {

            checkButton.setBackground(getDrawable(R.drawable.button_task_check));
            checkButton.setTextColor(getResources().getColor(R.color.button_task_check));

            checkButton.setEnabled(false);
        }
    }

    private void lockViews() {

        for (int i = 0; i < sentenceLine.getChildCount(); i++) {

            customWord = (CustomWord) sentenceLine.getChildAt(i);

            customWord.setEnabled(false);
        }

        for (int i = 0; i < customLayout.getChildCount(); i++) {

            customWord = (CustomWord) customLayout.getChildAt(i);

            customWord.setEnabled(false);
        }

    }

    private void randomizeCustomWords() {

        String[] wordsFromSentence = questionModel.getAnswer().split(" ");

        Collections.addAll(words, wordsFromSentence);

        int sentenceWordsCount = wordsFromSentence.length;

        //Declare how many words left to complete our layout
        int leftSize = 7 - sentenceWordsCount;

        //Pick a random number from "leftSize" and add 2
        int leftRandom = random.nextInt(leftSize) + 2;

        while (words.size() - leftSize < leftRandom) {

            addArrayWords();
        }

        Collections.shuffle(words);

        for (String word : words) {

            CustomWord customWord = new CustomWord(getApplicationContext(), word);

            customWord.setOnTouchListener(new WordTaskActivityFood.TouchListener());

            customLayout.push(customWord);
        }
    }

    private void addArrayWords() {

        String[] wordsFromAnswerArray = answers.get(random.nextInt(answers.size())).split(" ");

        for (int i = 0; i < 2; i++) {

            String word = wordsFromAnswerArray[random.nextInt(wordsFromAnswerArray.length)];

            if (!words.contains(word)) {

                words.add(word);
            }
        }
    }

    @Override
    public void onBackPressed() {

        new MaterialDialog.Builder(this)
                .title("Вы уверены?")
                .content("Весь прогресс на данном занятии будет утерян.")
                .positiveText("ВЫЙТИ")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        progressBarValue = 0;
                        Hawk.put("progressBarValue", progressBarValue);
                        Intent intent = new Intent(WordTaskActivityFood.this, ChooseLessonFood.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .negativeText("ОТМЕНИТЬ")
                .show();
    }

    @Override
    protected void onStop() {

        progressBarValue = 0;

        Hawk.put("progressBarValue", progressBarValue);

        finish();

        super.onStop();
    }
}
