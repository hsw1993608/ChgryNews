package com.chgrynews.chgrynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.chgrynews.utils.PrefUtils;

/**
 * Created by huangsw on 2016/7/11.
 * 启动页界面
 */
public class SplashActivity extends Activity {

    private  LinearLayout mLinAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mLinAnimation =   (LinearLayout) findViewById(R.id.linAnimation);
        startAnimation();
    }

    private void startAnimation() {

        AlphaAnimation aniAlyha = new AlphaAnimation(0.0f,1.0f);
        aniAlyha.setDuration(1000);
        aniAlyha.setRepeatCount(1);
        aniAlyha.setFillAfter(true);
        aniAlyha.setRepeatMode(Animation.REVERSE);

        RotateAnimation aniRotate = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        aniRotate.setDuration(1000);
        aniRotate.setRepeatCount(1);
        aniRotate.setFillAfter(true);
        aniRotate.setRepeatMode(Animation.REVERSE);

        ScaleAnimation aniScalse = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        aniScalse.setDuration(1000);
        aniScalse.setRepeatCount(1);
        aniScalse.setFillAfter(true);
        aniScalse.setRepeatMode(Animation.REVERSE);

        AnimationSet setAll = new AnimationSet(true);
        setAll.addAnimation(aniAlyha);
        setAll.addAnimation(aniRotate);
        setAll.addAnimation(aniScalse);
        mLinAnimation.startAnimation(setAll);

        setAll.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isGuideShow = PrefUtils.getBoolean("isGuideShow",false,getApplicationContext());
                if (isGuideShow){
                    startActivity(new Intent(SplashActivity.this,
                            MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this,
                            GuideActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
