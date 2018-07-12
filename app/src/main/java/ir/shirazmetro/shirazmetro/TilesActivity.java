package ir.shirazmetro.shirazmetro;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import android.os.Handler;

import ir.shirazmetro.shirazmetro.applib.MySlideAdapter;
import me.relex.circleindicator.CircleIndicator;

public class TilesActivity extends AppCompatActivity {

    private ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.train1, R.drawable.train2, R.drawable.train3, R.drawable.train4, R.drawable.train5};
    private ArrayList<Integer> XMENArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);
        init();
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = findViewById(R.id.slide1);
        mPager.setAdapter(new MySlideAdapter(TilesActivity.this, XMENArray));

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 3000);
    }
}
