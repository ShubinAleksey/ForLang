package com.example.forlang2.Activity.Dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forlang2.Activity.DailyGoal.PickDailyGoalActivity;
import com.example.forlang2.Activity.SelectLanguage.ChooseLang;
import com.example.forlang2.Activity.lessonlistactivity.LessonListActivity;
import com.example.forlang2.R;
import com.example.forlang2.Utils.Injection;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class DictionaryActivity extends AppCompatActivity {

    ListView dictionaryList;
    ArrayList<String> wordsList = new ArrayList<>();
    DatabaseReference myRef;
    ImageView back_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DictionaryActivity.this,android.R.layout.simple_list_item_1, wordsList);

        dictionaryList = findViewById(R.id.list_view1);
        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trSen = new Intent(DictionaryActivity.this, LessonListActivity.class);
                startActivity(trSen);
                finish();
            }
        });

        dictionaryList.setAdapter(arrayAdapter);

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef = FirebaseDatabase.getInstance().getReference("user/"+userID+"/dictionary");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                wordsList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                wordsList.remove(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dictionaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) dictionaryList.getItemAtPosition(i);
                Toast.makeText(DictionaryActivity.this,"Вы выбрали: " + item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
