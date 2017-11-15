package com.example.android.btp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.btp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Pallav on 11/16/2017.
 */

public class QuestionListActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Question> questionList;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    Logger logger;
    static int count = 0;
    static int size = 0;
    GeometricProgressView geometricProgressView;
    Spinner spinnerQuestion;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Intent i = getIntent();
        String level_name = i.getStringExtra("Level_name");
        String topic_name = i.getStringExtra("Topic_name");
        databaseReference = FirebaseDatabase.getInstance().getReference("Engineering").child("Questions").child(topic_name).child(level_name);

        questionList = new ArrayList<>();
        logger = Logger.getLogger("QuestionListActivity");
        logger.info(level_name);
        viewPager = findViewById(R.id.view_pager);
        geometricProgressView = findViewById(R.id.geometric_progress_view);
        spinnerQuestion = findViewById(R.id.spinner_question_no);
    }

    @Override
    protected void onStart() {
        super.onStart();
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count = 0;
                int s = 0;
                geometricProgressView.setVisibility(View.GONE);
                for (DataSnapshot questionKey : dataSnapshot.getChildren()) {
                    Question currentQuestion = questionKey.getValue(Question.class);
                    questionList.add(currentQuestion);
                    size = s;

                    pagerAdapter = new ScreenAdapter(getSupportFragmentManager(), questionList);
                    viewPager.setAdapter(pagerAdapter);
                    list = new ArrayList<>();
                    for (int j = 1; j <= questionList.size(); j++) {
                        list.add("Question " + j);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerQuestion.setPrompt("Jump to Question ");
                    spinnerQuestion.setAdapter(arrayAdapter);
                    spinnerQuestion.setSelection(0);
                    spinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            viewPager.setCurrentItem(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    viewPager.setOffscreenPageLimit(questionList.size());
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            spinnerQuestion.setSelection(position);
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class ScreenAdapter extends FragmentStatePagerAdapter {
        private List<Question> questionList = new ArrayList<>();

        ScreenAdapter(FragmentManager fm, List<Question> questionList) {
            super(fm);
            this.questionList = questionList;
        }

        @Override
        public Fragment getItem(int position) {
            return QuestionListFragment.newInstance((position + 1), questionList.get(position));
        }

        @Override
        public int getCount() {
            return questionList.size();
        }
    }
}
