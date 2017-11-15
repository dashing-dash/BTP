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
    public String field1,field2,field3,field4,field5,field6,field7,field8,field9;
    public Map<String,String> hm=new HashMap<>();
    public Branch(){

    }
    public Branch(String department,String subject,String id,String level,String question,String optionA,String optionB,String optionC,String optionD,String hint,String solution,String answer){
        this.department=department;
        this.subject=subject;
        this.id=id;
        this.field1=level;
        this.field2=question;
        this.field3=optionA;
        this.field4=optionB;
        this.field5=optionC;
        this.field6=optionD;
        this.field7=hint;
        this.field8=solution;
        this.field9=answer;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("department", department);
        result.put("subject", subject);
        result.put("id", id);
        result.put("level", field1);
        result.put("question", field2);
        result.put("optionA", field3);
        result.put("optionB", field4);
        result.put("optionC", field5);
        result.put("optionD", field6);
        result.put("hint", field7);
        result.put("solution", field8);
        result.put("answer", field9);


        return result;
    }

    public String getDepartment() {
        return department;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public String getField5() {
        return field5;
    }

    public String getField6() {
        return field6;
    }

    public String getField7() {
        return field7;
    }

    public String getField8() {
        return field8;
    }

    public String getField9() {
        return field9;
    }
}
