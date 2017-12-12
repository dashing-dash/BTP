package com.example.android.btp.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.btp.R;
import com.example.android.btp.adapter.CardsAdapter;
import com.example.android.btp.other.Categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pallav on 10/11/2017.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardsAdapter adapter;
    private List<Categories> categoryList;
    private View rootView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
//        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
//        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        categoryList = new ArrayList<>();
        adapter = new CardsAdapter(getContext(), categoryList,getActivity(),HomeFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCategories();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) rootView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    private void prepareCategories() {
        int[] covers = new int[]{
                R.drawable.category1,
                R.drawable.category2,
                R.drawable.category3,
                R.drawable.category4,
                R.drawable.category5,
                R.drawable.category6,
                R.drawable.category7,
                R.drawable.category8,
                R.drawable.category9,
                };

        Categories a = new Categories("Articles", covers[0]);
        categoryList.add(a);

        a = new Categories("Engineering", covers[1]);
        categoryList.add(a);

//        a = new Categories("Quizzes", covers[2]);
//        categoryList.add(a);

        a = new Categories("Coding Problems", covers[3]);
        categoryList.add(a);

        a = new Categories("General Knowledge", covers[4]);
        categoryList.add(a);

//        a = new Categories("School", covers[5]);
//        categoryList.add(a);
//
//        a = new Categories("Around the world", covers[6]);
//        categoryList.add(a);
//
//        a = new Categories("Random", covers[7]);
//        categoryList.add(a);

        a = new Categories("Coming Soon",  covers[8]);
        categoryList.add(a);



        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
