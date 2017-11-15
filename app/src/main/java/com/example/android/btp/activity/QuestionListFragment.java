package com.example.android.btp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.btp.R;
import static com.example.android.btp.activity.QuestionListActivity.count;
import static com.example.android.btp.activity.QuestionListActivity.size;
import java.util.logging.Logger;

/**
 * Created by Pallav on 11/16/2017.
 */

public class QuestionListFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_NO = "id";
    private static final String QUESTION = "question";
    private static final String OPTIONA = "optionA";
    private static final String OPTIONB = "optionB";
    private static final String OPTIONC = "optionC";
    private static final String OPTIOND = "optionD";
    private static final String HINT = "hint";
    private static final String SOLUTION = "solution";
    private static final String ANSWER = "answer";

    private static int i = 0;
    private TextView tvOptionA;
    private TextView tvOptionB;
    private TextView tvOptionC;
    private TextView tvOptionD;
    private Logger logger = Logger.getLogger("QuestionListFragment");
    //array to keep check visit to question
    boolean[] arr = new boolean[1000];

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
        View rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
        TextView tvQuestionNo = rootView.findViewById(R.id.tv_question_no);
        TextView tvQuestion = rootView.findViewById(R.id.tv_question);
        tvOptionA = rootView.findViewById(R.id.tv_option_a);
        tvOptionB = rootView.findViewById(R.id.tv_option_b);
        tvOptionC = rootView.findViewById(R.id.tv_option_c);
        tvOptionD = rootView.findViewById(R.id.tv_option_d);

        ImageView imageViewHint = getActivity().findViewById(R.id.iv_hint);
        ImageView imageViewSolution = getActivity().findViewById(R.id.iv_solution);
        ImageView imageViewSubmit = getActivity().findViewById(R.id.iv_submit);


        tvOptionA.setOnClickListener(this);
        tvOptionB.setOnClickListener(this);
        tvOptionC.setOnClickListener(this);
        tvOptionD.setOnClickListener(this);

        imageViewHint.setOnClickListener(this);
        imageViewSolution.setOnClickListener(this);
        imageViewSubmit.setOnClickListener(this);

        tvQuestionNo.setText(getArguments().getString(QUESTION_NO));
        tvQuestion.setText(getArguments().getString(QUESTION));
        tvOptionA.setText(getArguments().getString(OPTIONA));
        tvOptionB.setText(getArguments().getString(OPTIONB));
        tvOptionC.setText(getArguments().getString(OPTIONC));
        tvOptionD.setText(getArguments().getString(OPTIOND));
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_option_a) {
            logger.info(getArguments().getString(ANSWER) + "ANSWER");
            if (getArguments().getString(ANSWER).equalsIgnoreCase("A")) {
                QuestionListActivity.count++;
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
                QuestionListActivity.count++;
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
                QuestionListActivity.count++;
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
                QuestionListActivity.count++;
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
        CustomDialog customDialog;
        if (view.getId() == R.id.iv_hint) {
            if (getArguments().getString(HINT).equalsIgnoreCase(""))
                customDialog = new CustomDialog(getActivity(), "Hint", "Basic", "GOT IT!");
            else
                customDialog = new CustomDialog(getActivity(), "Hint", getArguments().getString(HINT), "GOT IT!");
            customDialog.show();
        }
        if (view.getId() == R.id.iv_solution) {
            customDialog = new CustomDialog(getActivity(), "Solution", getArguments().getString(SOLUTION), "GOT IT!");
            customDialog.show();
        }
        if (view.getId() == R.id.iv_submit) {
            customDialog = new CustomDialog(getActivity(), "Submit", "Are you sure you want to submit? your score is : " + count, "YES");
            customDialog.show();
        }
    }
}
