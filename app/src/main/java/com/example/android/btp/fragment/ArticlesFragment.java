package com.example.android.btp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.btp.R;
import com.example.android.btp.other.Article;

import java.util.List;

/**
 * Created by Pallav on 11/24/2017.
 */

public class ArticlesFragment extends Fragment{
    WebView wv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_articles,container,false);
        TextView title = (TextView) v.findViewById(R.id.articleTitle);
        EditText content = (EditText) v.findViewById(R.id.articleContent);
        title.setText(getArguments().getString("title"));
        content.setText(getArguments().getString("content"));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getArguments().getString("link"));
                getActivity().setContentView(R.layout.activity_web_view);
                wv1=(WebView)getActivity().findViewById(R.id.webView);
                wv1.setWebViewClient(new MyBrowser());

                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setWebViewClient(new WebViewClient());
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(getArguments().getString("link"));
            }

            public boolean onKeyDown(int keyCode, KeyEvent event) {
                // Check if the key event was the Back button and if there's history
                if ((keyCode == KeyEvent.KEYCODE_BACK) && wv1.canGoBack()) {
                    wv1.goBack();
                    return true;
                }
                // If it wasn't the Back key or there's no web page history, bubble up to the default
                // system behavior (probably exit the activity)
//                return super.onKeyDown(keyCode, event);
                return true;
            }

             class MyBrowser extends WebViewClient {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,String url){
                    view.loadUrl(url);
                    return true;
                }
            }
        });
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
