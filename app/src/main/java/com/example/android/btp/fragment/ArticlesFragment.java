package com.example.android.btp.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.btp.R;
import com.example.android.btp.other.Article;

import java.util.List;

/**
 * Created by Pallav on 11/24/2017.
 */

public class ArticlesFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_articles,container,false);
        TextView title = (TextView) v.findViewById(R.id.articleTitle);
        EditText content = (EditText) v.findViewById(R.id.articleContent);
        title.setText(getArguments().getString("title"));
        content.setText(getArguments().getString("content"));
        return v;
    }

    public static ArticlesFragment newInstance(int pos,List<Article> articleList){

        ArticlesFragment f=new ArticlesFragment();
        Bundle b=new Bundle();
//        if(articleList.size()==0){
//            b.putString("title","abc");
//            b.putString("content","ansjkdfab");
//            b.putString("link","sbfjhs");
//            f.setArguments(b);
//
//            return f;
//        }
        b.putString("title",articleList.get(pos).getTitle());
        b.putString("content",articleList.get(pos).getContent());
        b.putString("link",articleList.get(pos).getLink());
        f.setArguments(b);

        return f;
    }
}
