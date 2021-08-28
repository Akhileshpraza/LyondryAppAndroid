package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;

import com.lyondry.lyondry.Adapters.sliderAdapter;
import com.lyondry.lyondry.Modals.sliderModal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerActivity extends AppCompatActivity {

    ViewPager bannerSliderviewPager ;
    sliderAdapter adapter;
    List<sliderModal> sliderModalList;
    private int currentPage =2;
    private Timer timer;
    final private long DELY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        bannerSliderviewPager = findViewById(R.id.banner_viewpager);

        sliderModalList = new ArrayList<sliderModal>();
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));

        adapter = new sliderAdapter(sliderModalList);
        bannerSliderviewPager.setAdapter(adapter);
        bannerSliderviewPager.setClipToPadding(false);
        bannerSliderviewPager.setPageMargin(20);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage =1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderviewPager.addOnPageChangeListener(onPageChangeListener);

        startbannerSliderShow();
        bannerSliderviewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSliderShow();
                if (event.getAction() ==MotionEvent.ACTION_UP){
                    startbannerSliderShow();
                }

                return false;
            }
        });
    }

    private void pageLooper(){
        if (currentPage ==sliderModalList.size()-2){
            currentPage=2;
            bannerSliderviewPager.setCurrentItem(currentPage,false);
        }
        if (currentPage==1){
            currentPage =sliderModalList.size()-3;
            bannerSliderviewPager.setCurrentItem(currentPage,false);
        }
    }

    private void startbannerSliderShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >=sliderModalList.size()){
                    currentPage=1;
                }
                bannerSliderviewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELY_TIME,PERIOD_TIME);
    }

    private void stopBannerSliderShow(){
        timer.cancel();
    }
}