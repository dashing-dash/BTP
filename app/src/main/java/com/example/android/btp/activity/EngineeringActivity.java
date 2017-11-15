package com.example.android.btp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.btp.R;

import com.example.android.btp.adapter.CardsAdapter;
import com.example.android.btp.adapter.TopicAdapter;
import com.example.android.btp.fragment.HomeFragment;
import com.example.android.btp.other.Categories;
import com.example.android.btp.other.Constants;
import com.example.android.btp.other.Topic;
import com.google.firebase.auth.FirebaseAuth;
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
 * Created by Pallav on 10/12/2017.
 */

public class EngineeringActivity extends AppCompatActivity{
    final Logger logger = Logger.getLogger("EngineeringActivity");
    private DatabaseReference databaseReference;
    private List<Topic> topicsList;
    private ListView topicListView;
    GeometricProgressView geometricProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        databaseReference = FirebaseDatabase.getInstance().getReference("Engineering").child("Topics");
        topicsList = new ArrayList<>();
        topicListView = (ListView) findViewById(R.id.listview_topic);
        geometricProgressView= (GeometricProgressView) findViewById(R.id.geometric_progress_view);

        topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Topic topic = topicsList.get(i);
                Intent intent = new Intent(EngineeringActivity.this, LevelListActivity.class);
                intent.putExtra("Topic_ID", topic.getId());
                intent.putExtra("Topic_Name", topic.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                topicsList.clear();
                for (DataSnapshot topicKey : dataSnapshot.getChildren()) {
                    Topic currentTopic = topicKey.getValue(Topic.class);
                    topicsList.add(currentTopic);
                }
                TopicAdapter topicAdapter = new TopicAdapter(EngineeringActivity.this, topicsList);
                topicListView.setAdapter(topicAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
