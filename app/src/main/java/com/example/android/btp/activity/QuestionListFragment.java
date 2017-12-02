package com.example.android.btp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.btp.R;

import java.util.logging.Logger;

import static com.example.android.btp.activity.QuestionListActivity.count;
import static com.example.android.btp.activity.QuestionListActivity.size;

public class QuestionListFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_NO = "id";
    private static final String QUESTION = "question";
    private static final String OPTIONA = "optionA";
    private static final String OPTIONB = "optionB";
    private static final String OPTIONC = "optionC";
    private static final String OPTIOND = "optionD";
    static final String HINT = "hint";
    static final String SOLUTION = "solution";
    private static final String ANSWER = "answer";

    private static int i = 0;
    private TextView tvOptionA;
    private TextView tvOptionB;
    private TextView tvOptionC;
    private TextView tvOptionD;
    private View rootView;
    private static Logger logger = Logger.getLogger("QuestionListFragment");
    //array to keep check visit to question
    boolean[] arr = new boolean[size];

    public QuestionListFragment() {
    }

    public static QuestionListFragment newInstance(int position, Question question) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        i = position - 1;
        args.putString(QUESTION_NO, "Question " + String.valueOf(position));
        args.putString(QUESTION, question.getQuestion());
        args.putString(OPTIONA, question.getOptionA());
        args.putString(OPTIONB, question.getOptionB());
        args.putString(OPTIONC, question.getOptionC());
        args.putString(OPTIOND, question.getOptionD());
        args.putString(HINT, question.getHint());
        args.putString(SOLUTION, question.getSolution());
        args.putString(ANSWER, question.getAnswer());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
            TextView tvQuestionNo = rootView.findViewById(R.id.tv_question_no);
            TextView tvQuestion = rootView.findViewById(R.id.tv_question);
            tvOptionA = rootView.findViewById(R.id.tv_option_a);
            tvOptionB = rootView.findViewById(R.id.tv_option_b);
            tvOptionC = rootView.findViewById(R.id.tv_option_c);
            tvOptionD = rootView.findViewById(R.id.tv_option_d);

            tvOptionA.setOnClickListener(this);
            tvOptionB.setOnClickListener(this);
            tvOptionC.setOnClickListener(this);
            tvOptionD.setOnClickListener(this);

            tvQuestionNo.setText(getArguments().getString(QUESTION_NO));
            tvQuestion.setText(getArguments().getString(QUESTION));
            tvOptionA.setText(getArguments().getString(OPTIONA));
            tvOptionB.setText(getArguments().getString(OPTIONB));
            tvOptionC.setText(getArguments().getString(OPTIONC));
            tvOptionD.setText(getArguments().getString(OPTIOND));
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_option_a) {
            logger.info(getArguments().getString(ANSWER) + "ANSWER");
            if (getArguments().getString(ANSWER).equalsIgnoreCase("A")) {
                tvOptionA.setBackgroundColor(Color.GREEN);
                if (!arr[i]) {
                    count++;
                }
                logger.info(count + "count");
                arr[i] = true;
            } else {
                tvOptionA.setBackgroundColor(Color.RED);
                if (!arr[i]) {
                    count--;
                }
                logger.info(count + "count");
                arr[i] = true;
            }
        }
        if (view.getId() == R.id.tv_option_b) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("B")) {
                tvOptionB.setBackgroundColor(Color.GREEN);
                if (!arr[i]) {
                    count++;
                }
                logger.info(count + "count");
                arr[i] = true;
            } else {
                tvOptionB.setBackgroundColor(Color.RED);
                if (!arr[i]) {
                    count--;
                }
                logger.info(count + "count");
                arr[i] = true;
            }
        }
        if (view.getId() == R.id.tv_option_c) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("C")) {
                tvOptionC.setBackgroundColor(Color.GREEN);
                if (!arr[i]) {
                    count++;
                }
                logger.info(count + "count");
                arr[i] = true;
            } else {
                tvOptionC.setBackgroundColor(Color.RED);
                if (!arr[i]) {
                    count--;
                }
                logger.info(count + "count");
                arr[i] = true;
            }
        }
        if (view.getId() == R.id.tv_option_d) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("D")) {
                tvOptionD.setBackgroundColor(Color.GREEN);
                if (!arr[i]) {
                    count++;
                }
                logger.info(count + "count");
                arr[i] = true;
            } else {
                tvOptionD.setBackgroundColor(Color.RED);
                if (!arr[i]) {
                    count--;
                }
                logger.info(count + "count");
                arr[i] = true;
            }
        }
    }
}