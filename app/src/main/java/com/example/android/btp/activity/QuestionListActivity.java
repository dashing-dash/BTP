package com.example.android.btp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
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


public class QuestionListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Question> questionList;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    Logger logger;
    static int count = 0;
    static int size = 0;
    GeometricProgressView geometricProgressView;
    Spinner spinnerQuestion;
    List<String> list;
    String hint = "";
    String solution = "";
    CustomDialog customDialog;
    static String level_name;
    static String topic_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Intent i = getIntent();
        level_name = i.getStringExtra("Level_name");
        topic_name = i.getStringExtra("Topic_name");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Engineering").child("Questions").child(topic_name).child(level_name);

        questionList = new ArrayList<>();
        logger = Logger.getLogger("QuestionListActivity");
        logger.info(level_name);
        viewPager = findViewById(R.id.view_pager);
        geometricProgressView = findViewById(R.id.geometric_progress_view);
        spinnerQuestion = findViewById(R.id.spinner_question_no);
        ImageView imageViewHint = findViewById(R.id.iv_hint);
        ImageView imageViewSolution = findViewById(R.id.iv_solution);
        ImageView imageViewSubmit = findViewById(R.id.iv_submit);
        imageViewSolution.setOnClickListener(this);
        imageViewHint.setOnClickListener(this);
        imageViewSubmit.setOnClickListener(this);

        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count = 0;
                int s = 0;
                geometricProgressView.setVisibility(View.GONE);
                for (DataSnapshot questionKey : dataSnapshot.getChildren()) {
                    Question currentQuestion = questionKey.getValue(Question.class);
                    questionList.add(currentQuestion);
                    s++;
                }
                size = s;
                pagerAdapter = new ScreenAdapter(getSupportFragmentManager(), questionList);
                viewPager.setAdapter(pagerAdapter);
                list = new ArrayList<>();
                for (int j = 1; j <= questionList.size(); j++) {
                    list.add("Question " + j);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerQuestion.setAdapter(arrayAdapter);
                spinnerQuestion.setSelection(0);
                viewPager.setOffscreenPageLimit(questionList.size());
                hint = questionList.get(0).getHint();
                solution = questionList.get(0).getSolution();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewPager.setCurrentItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                logger.info("Page Selected" + getFragmentManager().findFragmentById(position));
                spinnerQuestion.setSelection(position);
                hint = questionList.get(position).getHint();
                solution = questionList.get(position).getSolution();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_hint) {
            if (hint.equalsIgnoreCase(""))
                customDialog = new CustomDialog(QuestionListActivity.this, getString(R.string.hint), getString(R.string.basic), getString(R.string.got_it));
            else
                customDialog = new CustomDialog(QuestionListActivity.this, getString(R.string.hint), hint, getString(R.string.got_it));
        }
        if (view.getId() == R.id.iv_solution) {
            customDialog = new CustomDialog(QuestionListActivity.this, getString(R.string.sol), solution, getString(R.string.got_it));
        }
        if (view.getId() == R.id.iv_submit) {
            customDialog = new CustomDialog(QuestionListActivity.this, getString(R.string.submit), getString(R.string.warning_message) + count, getString(R.string.no), getString(R.string.yes));
        }
//        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();
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

    @Override
    public void onBackPressed() {
        CustomDialog customDialog = new CustomDialog(QuestionListActivity.this, getString(R.string.submit), getString(R.string.warning_message) + count, getString(R.string.no), getString(R.string.yes));
        customDialog.show();
    }


}
