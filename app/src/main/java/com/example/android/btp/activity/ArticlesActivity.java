package com.example.android.btp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.btp.R;
import com.example.android.btp.fragment.ArticlesFragment;
import com.example.android.btp.other.Article;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pallav on 10/12/2017.
 */

public class ArticlesActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    public List<Article> articleList;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    static int count=0;
    GeometricProgressView geometricProgressView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        databaseReference = FirebaseDatabase.getInstance().getReference("Articles").child("gfg");
        articleList=new ArrayList<>();
        viewPager = findViewById(R.id.viewPagerArticles);
        geometricProgressView = findViewById(R.id.geometric_progress_view);


    }

    @Override
    protected void onStart(){
        super.onStart();
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                for (DataSnapshot key : dataSnapshot.getChildren()) {
                    Article currentArticle = key.getValue(Article.class);
                    articleList.add(currentArticle);
                    viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
                    viewPager.setOffscreenPageLimit(articleList.size());
                }
//                Toast.makeText(getApplicationContext(),articleList.size(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//
//            }
//        }, 9000);


    }

    private class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int pos){
            return ArticlesFragment.newInstance(pos,articleList);
        }

        @Override
        public int getCount(){
            return 150;
        }
    }


}
