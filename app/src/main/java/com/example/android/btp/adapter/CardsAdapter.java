package com.example.android.btp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.btp.activity.ArticlesActivity;
import com.example.android.btp.activity.CodingActivity;
import com.example.android.btp.activity.EngineeringActivity;
import com.example.android.btp.activity.HomePageActivity;
import com.example.android.btp.activity.QuizzesActivity;
import com.example.android.btp.other.Categories;
import com.example.android.btp.R;



import java.util.List;

/**
 * Created by Pallav on 10/12/2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Categories> categoryList;
    private Fragment fragment;
    private Activity context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//            overflow = (ImageView) view.findViewById(R.id.overflow);

        }
    }
    public CardsAdapter(Context mContext, List<Categories> categoryList,Activity context,Fragment fragment) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        this.context=context;
        this.fragment=fragment;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Categories category = categoryList.get(position);
        holder.title.setText(category.getName());
        String title=category.getName();
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(context!=null) {
                Toast.makeText(fragment.getActivity(), holder.title.getText(), Toast.LENGTH_SHORT).show();
//                if(holder.title.getText().equals("Articles")){
////                    Intent intent=new Intent().setClass(mContext,com.example.android.btp.activity.ArticlesActivity.class);
//                    Intent intent=new Intent(fragment.getActivity(), ArticlesActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    fragment.getActivity().startActivity(intent);
//                    fragment.getActivity().getFragmentManager().popBackStack();
//                }
                if (holder.title.getText().equals("Engineering")) {
                    Intent intent = new Intent(context, EngineeringActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    fragment.getActivity().getFragmentManager().popBackStack();
                }
//                else if(holder.title.getText().equals("Quizzes")){
//                    Intent intent=new Intent(fragment.getActivity(), QuizzesActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    fragment.getActivity().startActivity(intent);
//                    fragment.getActivity().getFragmentManager().popBackStack();
//                }
//                else if(holder.title.getText().equals("Coding Problems")){
//                    Intent intent=new Intent(fragment.getActivity(), CodingActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    fragment.getActivity().startActivity(intent);
//                    fragment.getActivity().getFragmentManager().popBackStack();
//                }
            }
            else{

            }
            }
        });
        Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    //use ion case of overflow(favourite)
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
