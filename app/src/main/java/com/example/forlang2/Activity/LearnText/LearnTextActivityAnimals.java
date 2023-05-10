package com.example.forlang2.Activity.LearnText;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.forlang2.Activity.ChooseLesson.ChooseLesson;
import com.example.forlang2.Activity.ChooseLesson.ChooseLessonAnimals;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.Model.TextModel;
import com.example.forlang2.R;
import com.example.forlang2.Utils.ActivityNavigation;
import com.example.forlang2.Utils.Injection;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearnTextActivityAnimals extends AppCompatActivity {

    @BindView(R.id.txtClickable)
    EditText edit;

    @BindView(R.id.ttSTextView)
    ImageView ttSTextView;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.check_button)
    Button checkButton;

    TextModel textModel;

    ActivityNavigation activityNavigation;

    int progressBarValue;

    Context context = LearnTextActivityAnimals.this;

    Repository repository;

    TextToSpeech ttS;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learntext);

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
        checkButton.setEnabled(true);
        repository = Injection.provideRepository();
        textModel = repository.getRandomTextObjAnimals();
        progressBarValue = 0;
        Hawk.init(this).build();
        if (Hawk.get("progressBarValue") != null) {
            progressBarValue = Hawk.get("progressBarValue");
            progressBar.setProgress(progressBarValue);
        }

        edit.setText(textModel.getText());

        checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));
        checkButton.setBackground(getDrawable(R.drawable.button_task_continue));

        ttSTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = edit.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ttS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        edit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    int selection_start = edit.getSelectionStart();
                    int selection_end = edit.getSelectionEnd();

                    String copy = edit.getText().toString().substring(selection_start, selection_end);

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                    repository.setNewDictionary(copy);

                    Toast.makeText(LearnTextActivityAnimals.this, "Слово добавлено: " + copy, Toast.LENGTH_SHORT).show();

                    clipboard.setText(copy);
                }, 3000);
                return false;
            }
        });

        activityNavigation = ActivityNavigation.getInstance(this);

        nextText();
    }

    private void nextText() {
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBarValue += 33;

                progressBar.setProgress(progressBarValue);

                Hawk.put("progressBarValue", progressBarValue);

                if (progressBarValue < 99) {

                    ActivityNavigation.getInstance(context).takeToRandomTask();

                } else {
                    progressBarValue = 0;
                    activityNavigation.lessonCompleted();
                    Hawk.put("progressBarValue", progressBarValue);
                }
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
                        Intent intent = new Intent(LearnTextActivityAnimals.this, ChooseLessonAnimals.class);
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
