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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.btp.R;
import com.example.android.btp.activity.EngineeringActivity;
import com.example.android.btp.activity.HomePageActivity;
import com.example.android.btp.activity.Question;
import com.example.android.btp.adapter.TopicAdapter;
import com.example.android.btp.other.Topic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Pallav on 10/11/2017.
 */

public class NotificationsFragment extends Fragment {
    private View rootView;
//    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputQuestion, inputOptionA, inputOptionB, inputOptionC, inputOptionD, inputHint, inputAnswer, inputSolution;
    private Spinner inputSubject, inputLevel;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private ArrayList<Topic> topicsList=new ArrayList<>();
    ArrayList<String> levelArray=new ArrayList<>();
    ArrayList<String> subjectArray=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        rootView=inflater.inflate(R.layout.fragment_console,container,false);
//        txtDetails = (TextView) rootView.findViewById(R.id.ctitle);
        inputSubject = (Spinner) rootView.findViewById(R.id.csubject);
        inputQuestion = (EditText) rootView.findViewById(R.id.cquestion);
        inputLevel = (Spinner) rootView.findViewById(R.id.clevel);
        inputOptionA = (EditText) rootView.findViewById(R.id.coptionA);
        inputOptionB = (EditText) rootView.findViewById(R.id.coptionB);
        inputOptionC = (EditText) rootView.findViewById(R.id.coptionC);
        inputOptionD = (EditText) rootView.findViewById(R.id.coptionD);
        inputHint = (EditText) rootView.findViewById(R.id.chint);
        inputAnswer = (EditText) rootView.findViewById(R.id.canswer);
        inputSolution = (EditText) rootView.findViewById(R.id.csolution);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Engineering").child("Topics");
        topicsList = new ArrayList<>();
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topicsList.clear();
                for (DataSnapshot topicKey : dataSnapshot.getChildren()) {
                    Topic currentTopic = topicKey.getValue(Topic.class);
                    topicsList.add(currentTopic);
                }
                for(int i=0;i<topicsList.size();i++){
                    subjectArray.add(topicsList.get(i).getName());
                }

                Spinner spinner = (Spinner)rootView.findViewById(R.id.csubject);
// Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, subjectArray);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        levelArray.add("Easy");
        levelArray.add("Medium");
        levelArray.add("Hard");
        Spinner spinnerLevel = (Spinner)rootView.findViewById(R.id.clevel);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, levelArray);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerLevel.setAdapter(adapter);

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Engineering").child("Questions");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = inputSubject.getSelectedItem().toString();
                String question=inputQuestion.getText().toString();
                String level = inputLevel.getSelectedItem().toString();
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
