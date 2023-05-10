package com.example.forlang2.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonFraze;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.Model.PairModel;
import com.example.forlang2.R;
import com.example.forlang2.Utils.ActivityNavigation;
import com.example.forlang2.Utils.Injection;
import com.nex3z.flowlayout.FlowLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TapPairActivityFraze extends AppCompatActivity {

    private static final String TAG = "TapPairActivity";

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    ArrayList<PairModel> pairs;
    ArrayList<CustomWord> compareWords = new ArrayList<>();

    boolean searchingPair = false;

    CustomWord customWord;

    int progressBarValue;
    int pairsFormed;
    int randomN;
    ActivityNavigation activityNavigation;

    Context context = TapPairActivityFraze.this;

    Random random = new Random();

    Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_pair);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        checkButton.setEnabled(false);

        repository = Injection.provideRepository();

        pairs = repository.getPairsFraze();

        Hawk.init(this).build();

        progressBarValue = 0;

        if (Hawk.get("progressBarValue") != null) {

            progressBarValue = Hawk.get("progressBarValue");

            progressBar.setProgress(progressBarValue);
        }

        activityNavigation = ActivityNavigation.getInstance(this);

        randomizePair();

        checkButtonListener();
    }

    private void randomizePair() {

        int randomIndex1;
        int randomIndex2;

        Collections.shuffle(pairs);

        randomN = random.nextInt(3) + 4;

        for (int i = 0; i < randomN; i++) {

            PairModel pair = pairs.get(i);

            String pair1 = pair.getPair1();
            String pair2 = pair.getPair2();

            CustomWord customWord1 = new CustomWord(this, pair1);
            CustomWord customWord2 = new CustomWord(this, pair2);

            customWord1.setTag(i);
            customWord2.setTag(i);

            customWord1.setOnTouchListener(new TapPairActivityFraze.TouchListener());
            customWord2.setOnTouchListener(new TapPairActivityFraze.TouchListener());

            if (flowLayout.getChildCount() != 0) {

                randomIndex1 = random.nextInt(flowLayout.getChildCount());
                randomIndex2 = random.nextInt(flowLayout.getChildCount());

                flowLayout.addView(customWord1, randomIndex1);
                flowLayout.addView(customWord2, randomIndex2);

            } else {

                flowLayout.addView(customWord1);
                flowLayout.addView(customWord2);
            }
        }
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            customWord = (CustomWord) view;

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                if (!isSearchingPair()) {

                    compareWords.add(customWord);

                    view.setBackground(getDrawable(R.drawable.custom_word_selected));

                    setSearchingPair(true);

                } else {

                    CustomWord previousWord = compareWords.get(0);

                    if (previousWord != view) {

                        if (previousWord.getTag() == view.getTag()) {

                            Toast.makeText(TapPairActivityFraze.this, "Правильная схожесть", Toast.LENGTH_SHORT).show();

                            previousWord.setEnabled(false);
                            view.setEnabled(false);

                            previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                            previousWord.setTextColor(getResources().getColor(R.color.grey_button));

                            view.setBackground(getDrawable(R.drawable.custom_word_border));
                            customWord.setTextColor(getResources().getColor(R.color.grey_button));

                            setSearchingPair(false);

                            compareWords.clear();

                            checkCompleteness();

                        } else {

                            Toast.makeText(TapPairActivityFraze.this, "Неправильная схожесть", Toast.LENGTH_SHORT).show();

                            previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                            view.setBackground(getDrawable(R.drawable.custom_word_border));

                            setSearchingPair(false);

                            compareWords.clear();
                        }

                    } else {

                        previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                        view.setBackground(getDrawable(R.drawable.custom_word_border));

                        setSearchingPair(false);

                        compareWords.clear();
                    }
                }

                return true;
            }

            return false;
        }
    }

    private void checkButtonListener() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (progressBarValue < 100) {

                    ActivityNavigation.getInstance(context).takeToRandomTaskFraze();

                } else {

                    progressBarValue = 0;

                    activityNavigation.lessonCompleted();

                    Hawk.put("progressBarValue", progressBarValue);
                }
            }
        });
    }

    private void checkCompleteness() {

        pairsFormed ++;

        if (pairsFormed == randomN) {

            Toast.makeText(TapPairActivityFraze.this, "Поздравляю, вы нашли все схожести!", Toast.LENGTH_SHORT).show();

            progressBarValue += 20;

            progressBar.setProgress(progressBarValue);

            Hawk.put("progressBarValue", progressBarValue);

            checkButton.setEnabled(true);
            checkButton.setText("продолжить");
            checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));
            checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
        }
    }

    public boolean isSearchingPair() {
        return searchingPair;
    }

    public void setSearchingPair(boolean searchingPair) {
        this.searchingPair = searchingPair;
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
                        Intent intent = new Intent(TapPairActivityFraze.this, ChooseLessonFraze.class);
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
