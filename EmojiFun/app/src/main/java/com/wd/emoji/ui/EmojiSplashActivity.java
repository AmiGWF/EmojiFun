package com.wd.emoji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.wd.emoji.R;
import com.wd.emojiui.base.BaseActivity;

public class EmojiSplashActivity extends BaseActivity {
    /**
     * 图标标签
     */
    private ImageView ivIcon;
    /**
     * 延迟时间
     */
    private static final int DELAY_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 利用布局资源文件设置用户界面
        setContentView(R.layout.ac_splash);

        // 通过资源标识获得控件实例
        ivIcon = (ImageView) findViewById(R.id.em_splash_img);

        // 加载动画配置文件，启动动画
        //ivIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animator));

        // 利用消息处理器实现延迟跳转到登录窗口
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 启动登录窗口
                startActivity(new Intent(EmojiSplashActivity.this, EmojiMainActivity.class));
                // 关闭启动画面
                finish();
            }
        }, DELAY_TIME);
    }
}
