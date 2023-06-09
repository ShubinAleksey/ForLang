package com.example.forlang2.Activity.SelectLanguage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forlang2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseLang extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.back_button)
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselang);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        LangAdapter languageAdapter = new LangAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RvDividerItemDecoration itemDecoration = new RvDividerItemDecoration(getDrawable(R.drawable.recycler_view_divider));

        recyclerView.setAdapter(languageAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}
