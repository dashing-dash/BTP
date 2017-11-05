package com.example.android.btp.other;

/**
 * Created by Pallav on 11/2/2017.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
@IgnoreExtraProperties
public class Branch {
    public String department;
    public String subject;
    public String id;
    public String level,question,optionA,optionB,optionC,optionD,hint,solution,answer;
    public Map<String,String> hm=new HashMap<>();
    public Branch(){

    }
    public Branch(String department,String subject,String id,String level,String question,String optionA,String optionB,String optionC,String optionD,String hint,String solution,String answer){
        this.department=department;
        this.subject=subject;
        this.id=id;
        this.level=level;
        this.question=question;
        this.optionA=optionA;
        this.optionB=optionB;
        this.optionC=optionC;
        this.optionD=optionD;
        this.hint=hint;
        this.solution=solution;
        this.answer=answer;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("department", department);
        result.put("subject", subject);
        result.put("id", id);
        result.put("level", level);
        result.put("question", question);
        result.put("optionA", optionA);
        result.put("optionB", optionB);
        result.put("optionC", optionC);
        result.put("optionD", optionD);
        result.put("hint", hint);
        result.put("solution", solution);
        result.put("answer", answer);


        return result;
    }


}
