package com.example.administrator.myviewpager;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyViewPager viewPager;
    private ImageView imageView1, imageView2;
    private String[] title = {"第一页", "第二页", "第三页"};
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        viewPager.setScroll(false);

        viewList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(title[i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(16);
            viewList.add(textView);
        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if (position == 0) {
                            imageView2.setImageResource(R.mipmap.image1);
                        } else if (position == 1) {
                            imageView2.setImageResource(R.mipmap.image2);
                        } else if (position == 2) {
                            imageView2.setImageResource(R.mipmap.image3);
                        }
                        viewPager.setScroll(true);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (position == 0) {
                            imageView1.setImageResource(R.mipmap.image1);
                        } else if (position == 1) {
                            imageView1.setImageResource(R.mipmap.image2);
                        } else if (position == 2) {
                            imageView1.setImageResource(R.mipmap.image3);
                        }
                        imageView1.clearAnimation();
                        viewPager.setScroll(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView1.setAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
