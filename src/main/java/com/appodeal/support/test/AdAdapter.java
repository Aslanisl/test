package com.appodeal.support.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.appodeal.ads.NativeAd;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.List;

public class AdAdapter extends ArrayAdapter<NativeAd> {
    private Context c;

    public AdAdapter(Context c, List<NativeAd> history) {
        super(c, 0, history);
        this.c = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c)
                    .inflate(R.layout.native_ad_view_news_feed, parent, false);
        }

        final NativeAdViewNewsFeed nav_nf = (NativeAdViewNewsFeed) convertView.findViewById(R.id.native_ad_view_news_feed);
        nav_nf.setNativeAd(getItem(position));

        return convertView;
    }


}