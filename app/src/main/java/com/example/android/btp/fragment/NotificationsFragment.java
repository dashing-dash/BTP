package com.example.android.btp.fragment;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.btp.R;
import com.example.android.btp.activity.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * Created by Pallav on 10/11/2017.
 */

public class NotificationsFragment extends Fragment {
    private View rootView;
//    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputSubject, inputLevel, inputQuestion, inputOptionA, inputOptionB, inputOptionC, inputOptionD, inputHint, inputAnswer, inputSolution;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        rootView=inflater.inflate(R.layout.fragment_console,container,false);
//        txtDetails = (TextView) rootView.findViewById(R.id.ctitle);
        inputSubject = (EditText) rootView.findViewById(R.id.csubject);
        inputQuestion = (EditText) rootView.findViewById(R.id.cquestion);
        inputLevel = (EditText) rootView.findViewById(R.id.clevel);
        inputOptionA = (EditText) rootView.findViewById(R.id.coptionA);
        inputOptionB = (EditText) rootView.findViewById(R.id.coptionB);
        inputOptionC = (EditText) rootView.findViewById(R.id.coptionC);
        inputOptionD = (EditText) rootView.findViewById(R.id.coptionD);
        inputHint = (EditText) rootView.findViewById(R.id.chint);
        inputAnswer = (EditText) rootView.findViewById(R.id.canswer);
        inputSolution = (EditText) rootView.findViewById(R.id.csolution);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Engineering").child("Questions");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = inputSubject.getText().toString();
                String question=inputQuestion.getText().toString();
                String level = inputLevel.getText().toString();
                String optionA = inputOptionA.getText().toString();
                String optionB = inputOptionB.getText().toString();
                String optionC = inputOptionC.getText().toString();
                String optionD = inputOptionD.getText().toString();
                String hint = inputHint.getText().toString();
                String answer = inputAnswer.getText().toString();
                String solution = inputSolution.getText().toString();
                if(subject.isEmpty()||question.isEmpty()||level.isEmpty()||optionA.isEmpty()||optionB.isEmpty()||optionC.isEmpty()||optionD.isEmpty()||hint.isEmpty()||answer.isEmpty()||solution.isEmpty()){
                    Toast.makeText(getActivity(),"Fill all the fileds",Toast.LENGTH_SHORT).show();
                    return;
                }
                userId = mFirebaseDatabase.push().getKey();

                Question q=new Question(question, optionA, optionB, optionC, optionD,  hint,  solution, answer);
                mFirebaseDatabase.child(subject).child(level).child(userId).setValue(q);
                Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_SHORT).show();

                inputSubject.setText("");
                inputLevel.setText("");
                inputQuestion.setText("");
                inputOptionA.setText("");
                inputOptionB.setText("");
                inputOptionC.setText("");
                inputOptionD.setText("");
                inputAnswer.setText("");
                inputHint.setText("");
                inputSolution.setText("");


            }
        });
        return rootView;
    }
}
