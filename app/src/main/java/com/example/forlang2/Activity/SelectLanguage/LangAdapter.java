package com.example.forlang2.Activity.SelectLanguage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.forlang2.Activity.DailyGoal.PickDailyGoalActivity;
import com.example.forlang2.Data.Repository;
import com.example.forlang2.R;
import com.example.forlang2.Utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.ViewHolder> {

    private static final String TAG = "LanguageAdapter";

    String[] languages = {
            "English"};

    Repository repository;
    Context context;

    public LangAdapter(Context context) {

        repository = Injection.provideRepository();
        this.context = context;
    }

    @Override
    public LangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_course_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LangAdapter.ViewHolder holder, final int position) {

        final String language = languages[position];

        holder.tvLanguage.setText(language);

        holder.main_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                repository.setNewLanguage(language.toLowerCase());

                Intent intent = new Intent(context, PickDailyGoalActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languages.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.language)
        TextView tvLanguage;

        @BindView(R.id.main_item_layout)
        RelativeLayout main_item_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
