package com.example.forlang2.Activity.Translator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.forlang2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TranslatorActivity extends AppCompatActivity {

    private TextInputEditText sourceLanguageEt;
    private TextView destinationLanguageTv;

    private AppCompatButton sourceLanguageChooseBtn, destinationLanguageChooseBtn;
    private ImageView micIV;
    private AppCompatButton translateBtn;

    private TranslatorOptions translatorOptions;
    private Translator translator;

    private ProgressDialog progressDialog;

    private ArrayList<ModelLanguage> languageArrayList;

    private String sourceLanguageCode = "ru";
    private String sourceLanguageTitle = "Russian";
    private String destinationLanguageCode = "en";
    private String destinationLanguageTitle = "English";

    private static final int REQUEST_PERMISSION_CODE = 1;

    private static final String TAG = "MAIN_TAG";
    private String sourceLanguageText = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        sourceLanguageChooseBtn = findViewById(R.id.sourceLanguageChooseBtn);
        destinationLanguageChooseBtn = findViewById(R.id.destinationLanguageChooseBtn);
        sourceLanguageEt = findViewById(R.id.sourceLanguageEt);
        micIV = findViewById(R.id.idIVMic);
        translateBtn = findViewById(R.id.translateBtn);
        destinationLanguageTv = findViewById(R.id.destinationLanguageTv);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Пожалуйста подождите");
        progressDialog.setCanceledOnTouchOutside(false);

        loadAvailableLanguages();

        // Метод клика для входного языка
        sourceLanguageChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLanguageChoose();
            }
        });

        // Метод клика для выходного языка
        destinationLanguageChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLanguageChoose();
            }
        });

        // Метод клика для преобразование речи в текст
        micIV.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Скажи что-то для перевода");

            try {
                startActivityForResult(intent, REQUEST_PERMISSION_CODE);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TranslatorActivity.this, "" + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Метод клика для кнопки перевода
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLanguageTv.setVisibility(View.VISIBLE);
                destinationLanguageTv.setText("");
                validateData();
            }
        });
    }

    // Метод для валидации данных текста от пользователя
    private void validateData() {
        sourceLanguageText = sourceLanguageEt.getText().toString().trim();

        Log.d(TAG, "validateData: sourceLanguageText: " + sourceLanguageText);

        if (sourceLanguageText.isEmpty()) {
            Toast.makeText(this, "Введите текст для перевода", Toast.LENGTH_SHORT).show();
        } else {
            startTranslation();
        }
    }

    // Метод функции перевода текста
    private void startTranslation() {
        progressDialog.setMessage("Скачивание языковой модели...");
        progressDialog.show();

        // Билдер настроек для перевода
        translatorOptions = new TranslatorOptions.Builder()
                .setSourceLanguage(sourceLanguageCode)
                .setTargetLanguage(destinationLanguageCode).build();
        translator = Translation.getClient(translatorOptions);

        // Условия скачивания языковой модели
        DownloadConditions downloadConditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        // Метод скачивания языковой модели
        translator.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: модель готова, начинаем перевод...");
                        progressDialog.setMessage("Переводим...");
                        translator.translate(sourceLanguageText)
                                .addOnSuccessListener(new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String translatedText) {
                                        Log.d(TAG, "onSuccess: translatedText: " + translatedText);
                                        progressDialog.dismiss();
                                        destinationLanguageTv.setText(translatedText);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Log.e(TAG, "onFailure: ", e);
                                        Toast.makeText(TranslatorActivity.this, "Ошибка перевода " + e.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "onFailure: ", e);
                        Toast.makeText(TranslatorActivity.this, "Ошибка получении модели " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sourceLanguageChoose() {
        PopupMenu popupMenu = new PopupMenu(this, sourceLanguageChooseBtn);

        for (int i = 0; i < languageArrayList.size(); i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, languageArrayList.get(i).languageTitle);
        }

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int position = menuItem.getItemId();

                sourceLanguageCode = languageArrayList.get(position).languageCode;
                sourceLanguageTitle = languageArrayList.get(position).languageTitle;

                sourceLanguageChooseBtn.setText(sourceLanguageTitle);
                sourceLanguageEt.setHint("Введите " + sourceLanguageTitle);

                Log.d(TAG, "onMenuItemClick: sourceLanguageCode " + sourceLanguageCode);
                Log.d(TAG, "onMenuItemClick: sourceLanguageTitle " + sourceLanguageTitle);

                return false;
            }
        });
    }

    private void destinationLanguageChoose() {
        PopupMenu popupMenu = new PopupMenu(this, destinationLanguageChooseBtn);

        for (int i = 0; i < languageArrayList.size(); i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, languageArrayList.get(i).getLanguageTitle());
        }

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int position = menuItem.getItemId();

                destinationLanguageCode = languageArrayList.get(position).languageCode;
                destinationLanguageTitle = languageArrayList.get(position).languageTitle;

                destinationLanguageChooseBtn.setText(destinationLanguageTitle);

                Log.d(TAG, "onMenuItemClick: destinationLanguageCode " + destinationLanguageCode);
                Log.d(TAG, "onMenuItemClick: destinationLanguageTitle " + destinationLanguageTitle);

                return false;
            }
        });
    }

    private void loadAvailableLanguages() {
        languageArrayList = new ArrayList<>();

        List<String> languageCodeList = TranslateLanguage.getAllLanguages();

        for (String languageCode : languageCodeList) {
            String languageTitle = new Locale(languageCode).getDisplayLanguage();
            Log.d(TAG, "loadAvailableLanguages: languageCode: " + languageCode);
            Log.d(TAG, "loadAvailableLanguages: languageTitle: " + languageTitle);
            ModelLanguage modelLanguage = new ModelLanguage(languageCode, languageTitle);
            languageArrayList.add(modelLanguage);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            sourceLanguageEt.setText(result.get(0));
        }
    }
}
