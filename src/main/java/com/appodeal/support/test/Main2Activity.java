package com.appodeal.support.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealMediaView;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView mListView;
    private AdAdapter adAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mListView = (ListView) findViewById(R.id.listview);

        Appodeal.setAutoCacheNativeIcons(true);
        Appodeal.setAutoCacheNativeMedia(false);
        Appodeal.setNativeAdType(Native.NativeAdType.Auto);
        Appodeal.cache(Main2Activity.this, Appodeal.NATIVE, 10);
        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded(List<NativeAd> nativeAds) {
//                Toast.makeText(Main2Activity.this, "onNativeLoaded", Toast.LENGTH_SHORT).show();

                if(adAdapter == null) {
                    adAdapter = new AdAdapter(getApplicationContext(), nativeAds);
                    mListView.setAdapter(adAdapter);

                    Appodeal.show(Main2Activity.this, Appodeal.NATIVE);
                } else {
                    adAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNativeFailedToLoad() {
//                Toast.makeText(Main2Activity.this, "onNativeFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNativeShown(NativeAd nativeAd) {
//                Toast.makeText(Main2Activity.this, "onNativeShown", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNativeClicked(NativeAd nativeAd) {
//                Toast.makeText(Main2Activity.this, "onNativeClicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
