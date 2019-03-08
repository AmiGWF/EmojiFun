package com.wd.emojiui.pages;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.emojiui.R;
import com.wd.emojiui.base.BaseActivity;
import com.wd.emojiui.inter.EMInterpolator;

public class EmLoginActivity extends BaseActivity {
    private TextView em_login_btn;
    private LinearLayout em_login_input_layout;
    private View em_login_progressBar;
    private TextView em_login_ideas, em_login_password;
    private View em_login_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_main_login);
        initView();
    }

    private void initView() {
        em_login_btn = findView(R.id.em_login_btn);
        em_login_btn.setOnClickListener(this);
        em_login_input_layout = findView(R.id.em_login_input_layout);
        em_login_progressBar = findView(R.id.em_login_progressBar);
        em_login_ideas = findView(R.id.em_login_ideas);
        em_login_password = findView(R.id.em_login_password);
        em_login_view = findView(R.id.em_login_view);
    }


    /**
     * 登录前输入框的动画
     *
     * @param view
     */
    private void loginInputAnimator(final View view, float start, float end) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", start, end);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", start, end);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "alpha", start, end);
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(objectAnimator, objectAnimator2, objectAnimator3);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束，显示loading，隐藏输入框
                 */
                em_login_progressBar.setVisibility(View.VISIBLE);
                progressAnimator(em_login_progressBar);
                em_login_input_layout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void loginFaileAnimation(final View view, float start, float end) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", start, end);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", start, end);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "alpha", start, end);
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(objectAnimator, objectAnimator2, objectAnimator3);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    /**
     * 登录按钮动画
     * 缩放、半透明
     */
    private void LoginBtnAnimation(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.6f, 1f);
        animatorSet.setDuration(1200);
        animatorSet.playTogether(animator, animator2);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画开始就去检车登录成功与否
                 */
                if (checkLogin()) {
                    //登录成功逻辑
                    loginSuccess();
                } else {
                    //登录失败逻辑
                    resetAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    /**
     * 加载框动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolder, propertyValuesHolder2);
        animator.setDuration(800);
        animator.setInterpolator(new EMInterpolator());
        animator.start();
    }

    /**
     * 重置所有控件属性
     */
    private void resetAnimation() {
        // 显示输入框
        em_login_ideas.setVisibility(View.VISIBLE);
        em_login_password.setVisibility(View.VISIBLE);
        em_login_view.setVisibility(View.VISIBLE);
        em_login_input_layout.setVisibility(View.VISIBLE);
        em_login_progressBar.setVisibility(View.GONE);
        loginFaileAnimation(em_login_input_layout, 0f, 1f);
    }


    /**
     * 检查登录
     *
     * @return
     */
    private boolean checkLogin() {
        String ideas = em_login_ideas.getText().toString();
        String pass = em_login_password.getText().toString();
        if (!TextUtils.isEmpty(ideas) && !TextUtils.isEmpty(pass)) {
            return true;
        }
        return false;
    }

    private void loginSuccess(){
        this.finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.em_login_btn) {
            // 隐藏输入框
            em_login_ideas.setVisibility(View.INVISIBLE);
            em_login_password.setVisibility(View.INVISIBLE);
            em_login_view.setVisibility(View.INVISIBLE);

            LoginBtnAnimation(em_login_btn);
            loginInputAnimator(em_login_input_layout, 1f, 0f);
        }
    }
}
