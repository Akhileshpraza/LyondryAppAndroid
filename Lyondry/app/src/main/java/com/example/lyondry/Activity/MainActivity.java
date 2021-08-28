package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.Adapters.sliderAdapter;
import com.lyondry.lyondry.Modals.servicesModal;
import com.lyondry.lyondry.Modals.sliderModal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button Btn_schedual;

    RecyclerView recyclerView;
    List<servicesModal> servicesModalList;

    //**********************************banner_slider******************************************
    ViewPager bannerSliderviewPager;
    sliderAdapter slideAdapter;
    List<sliderModal> sliderModalList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    //**********************************banner_slider******************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.small_logo);
        recyclerView = findViewById(R.id.service_recycleView);

        Btn_schedual = findViewById(R.id.btn_schedular);

        servicesModalList = new ArrayList<servicesModal>();
        servicesModalList.add(new servicesModal(R.drawable.s1, "Dry cleaning"));
        servicesModalList.add(new servicesModal(R.drawable.laundry, "Laundry"));
        servicesModalList.add(new servicesModal(R.drawable.altration, "Alteration"));
        servicesModalList.add(new servicesModal(R.drawable.shoe_care, "Shoe care"));
        servicesModalList.add(new servicesModal(R.drawable.monthly_package, "Monthly Package"));


        servicesAdapter adapter = new servicesAdapter(servicesModalList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        //**********************************banner_slider******************************************
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

        slideAdapter = new sliderAdapter(sliderModalList);
        bannerSliderviewPager.setAdapter(slideAdapter);
        bannerSliderviewPager.setClipToPadding(false);
        bannerSliderviewPager.setPageMargin(20);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
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
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startbannerSliderShow();
                }

                return false;
            }
        });
        //**********************************banner_slider******************************************

        //**********************************Btn Schedual******************************************

        Btn_schedual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //**********************************Btn Schedual******************************************

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //**********************************banner_slider******************************************
    private void pageLooper() {
        if (currentPage == sliderModalList.size() - 2) {
            currentPage = 2;
            bannerSliderviewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModalList.size() - 3;
            bannerSliderviewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startbannerSliderShow() {
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModalList.size()) {
                    currentPage = 1;
                }
                bannerSliderviewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELY_TIME, PERIOD_TIME);
    }

    private void stopBannerSliderShow() {
        timer.cancel();
    }
    //**********************************banner_slider******************************************
}