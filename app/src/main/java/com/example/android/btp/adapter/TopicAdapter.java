package com.example.android.btp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.btp.R;
import com.example.android.btp.other.Topic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pallav on 11/15/2017.
 */

public class TopicAdapter extends ArrayAdapter<Topic> {

    private Activity context;
    private List<Topic> topicsList = new ArrayList<>();

    public TopicAdapter(@NonNull Activity context, @NonNull List<Topic> topicsList) {
        super(context, 0, topicsList);
        this.context = context;
        this.topicsList = topicsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(context).inflate(R.layout.topic_list_item, parent, false);
        }
        Topic currentTopic = topicsList.get(position);
        ImageView ivTopicImage=listViewItem.findViewById(R.id.iv_topic_image);
        TextView tvTopicName = listViewItem.findViewById(R.id.tv_topic_name);
        TextView tvNoOfQuestions=listViewItem.findViewById(R.id.tv_no_of_questions);
        Picasso.with(getContext()).load(currentTopic.getLink()).resize(100,100).into(ivTopicImage);
        tvTopicName.setText(currentTopic.getName());
        return listViewItem;
    }
}