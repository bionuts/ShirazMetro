package ir.shirazmetro.shirazmetro;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import ir.shirazmetro.shirazmetro.applib.MySlideAdapter;
import me.relex.circleindicator.CircleIndicator;

public class TilesActivity extends AppCompatActivity {

    private ViewPager mPager;
    VideoView video;
    private MediaController mc;

    Button btn_play;
    String videoPath;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.train1, R.drawable.train2, R.drawable.train3, R.drawable.train4, R.drawable.train5};
    private ArrayList<Integer> XMENArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);
        init();
        if (mc == null) {
            mc = new MediaController(this);
        }
//        video = (VideoView) findViewById(R.id.videoView);
//        video.setMediaController(mc);
//        btn_play = (Button) findViewById(R.id.button3);
    }

    public void PlayVideoOnClick(View v) {
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT);
//        videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
//        Toast.makeText(this, videoPath, Toast.LENGTH_LONG);
//        Uri videoUri = Uri.parse(videoPath);
//        video.setVideoURI(videoUri);
//        video.start();
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
