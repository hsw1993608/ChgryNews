package com.chgrynews.chgrynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chgrynews.adapter.Guide_Adapter;
import com.chgrynews.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by huangsw on 2016/7/11.
 * 引导页界面
 */
public class GuideActivity extends Activity{

    private ViewPager mGuide_viewpager;
    private int[] mImages;
    private Button mGuide_begin;
    private ArrayList<ImageView> mImageViewArrayList;
    private LinearLayout mLlContainear;
    private ImageView mGuide_red_point;
    private int mPointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mGuide_viewpager =  (ViewPager) findViewById(R.id.guide_viewpager);
        mGuide_begin = (Button) findViewById(R.id.guide_begin);
        mLlContainear = (LinearLayout)findViewById(R.id.llContainer);
        mGuide_red_point = (ImageView)findViewById(R.id.guide_red_point);
        mImages = new int[]{R.drawable.guide_1,
                R.drawable.guide_3,
                R.drawable.guide_4,
                R.drawable.guide_5};

        mImageViewArrayList =  new ArrayList<ImageView>();
        for (int i = 0;i<mImages.length;i++){
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(mImages[i]);
            mImageViewArrayList.add(iv);

            ImageView circleView = new ImageView(this);
            circleView.setBackgroundResource(R.drawable.guide_circle_shape_select);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i>0){
                params.leftMargin = 8;
            }
            circleView.setLayoutParams(params);
            mLlContainear.addView(circleView);
        }

        mGuide_red_point.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGuide_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointWidth = mLlContainear.getChildAt(1).getLeft()-
                        mLlContainear.getChildAt(0).getLeft();
            }
        });

        Guide_Adapter ga = new Guide_Adapter(mImageViewArrayList,this);
        mGuide_viewpager.setAdapter(ga);

        mGuide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {

                int leftMargin = (int) (position * mPointWidth + mPointWidth * positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        mGuide_red_point.getLayoutParams();
                params.leftMargin = leftMargin;
                mGuide_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImages.length-1){
                    AlphaAnimation aniAlpha = new AlphaAnimation(0.0f,1.0f);
                    aniAlpha.setDuration(2000);
                    aniAlpha.setRepeatMode(Animation.REVERSE);
                    aniAlpha.setRepeatCount(1);
                    mGuide_begin.startAnimation(aniAlpha);
                    mGuide_begin.setVisibility(View.VISIBLE);
                }else{
                    mGuide_begin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void begin(View view){
        PrefUtils.putBoolean("isGuideShow",true,getApplicationContext());
        startActivity(new Intent(GuideActivity.this,
                MainActivity.class));
        finish();
    }
}
