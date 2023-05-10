package com.example.forlang2.Tasks.TranslateSentence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonClothes;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.Model.QuestionModel;
import com.example.forlang2.R;
import com.example.forlang2.Utils.ActivityNavigation;
import com.example.forlang2.Utils.Injection;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TSTaskActivityClothes extends AppCompatActivity {

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.user_answer)
    EditText etUserAnswer;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.ttSTextView)
    ImageView ttSTextView;

    QuestionModel questionModel;

    int progressBarValue;

    Repository repository;

    Context context = TSTaskActivityClothes.this;

    TextToSpeech ttS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_sentence);

        ButterKnife.bind(this);

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

    private void initData() {

        checkButton.setEnabled(false);

        repository = Injection.provideRepository();

        questionModel = repository.getRandomQuestionObjClothes();

        progressBarValue = 0;

        Hawk.init(this).build();

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

        checkAnswer();
        enableButton();
    }

    private void checkAnswer() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userAnswer = etUserAnswer.getText().toString();

                if (checkButton.getText().equals("проверить")) {

                    if (questionModel.getAnswer().toLowerCase().equals(userAnswer.toLowerCase())) {

                        Toast.makeText(context, "Совершенно верно!", Toast.LENGTH_SHORT).show();

                        progressBarValue += 20;

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockEditText();

                    } else {

                        Toast.makeText(context, "Неправильный ответ! " + questionModel.getAnswer(), Toast.LENGTH_SHORT).show();

                        if (progressBarValue > 20) {

                            progressBarValue -= 20;

                        } else {

                            progressBarValue = 0;
                        }

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockEditText();
                    }

                    checkButton.setText("продолжить");
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));
                    checkButton.setBackground(getDrawable(R.drawable.button_task_continue));

                } else if (checkButton.getText().equals("продолжить")) {

                    if (progressBarValue < 100) {

                        ActivityNavigation.getInstance(context).takeToRandomTaskClothes();

                    } else {

                        progressBarValue = 0;

                        Hawk.put("progressBarValue", progressBarValue);
                    }

                }
            }
        });
    }

    private void lockEditText() {

        etUserAnswer.setEnabled(false);
    }

    private void enableButton() {

        etUserAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 > 0) {

                    checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));

                    checkButton.setEnabled(true);

                } else {

                    checkButton.setBackground(getDrawable(R.drawable.button_task_check));
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_check));

                    checkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
                        Intent intent = new Intent(TSTaskActivityClothes.this, ChooseLessonClothes.class);
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
