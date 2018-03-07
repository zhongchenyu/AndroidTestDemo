package chenyu.memorydemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimationActivity extends AppCompatActivity implements
    View.OnClickListener{
  ImageView mLogo;
  Button btnAnimation;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, AnimationActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation);

    mLogo = (ImageView) findViewById(R.id.logo);
    btnAnimation = (Button) findViewById(R.id.button_start_animation);
    btnAnimation.setOnClickListener(this);
    setAnimation();
  }

  @Override public void onClick(View view) {
    switch( view.getId()) {
      case R.id.button_start_animation:
        startObjectAnimator();
        break;
    }
  }
  private void setAnimation() {
    Animation mRota = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    ScaleAnimation mScale = new ScaleAnimation(0.0f, 1f, 0.0f, 1f);
    mScale.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
        Toast.makeText(AnimationActivity.this, "onAnimationStart", Toast.LENGTH_SHORT).show();
      }

      @Override public void onAnimationEnd(Animation animation) {
        Toast.makeText(AnimationActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
      }

      @Override public void onAnimationRepeat(Animation animation) {
        Toast.makeText(AnimationActivity.this, "onAnimationRepeat", Toast.LENGTH_SHORT).show();
      }
    });
    AnimationSet animationSet = new AnimationSet(true);
    animationSet.addAnimation(mRota);
    animationSet.addAnimation(mScale);
    animationSet.setDuration(3000);
    mLogo.startAnimation(animationSet);
  }

  private void startObjectAnimator() {
    ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogo, "scaleX", 0, 1);
    ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogo, "scaleY", 0, 1);
    ObjectAnimator rotation = ObjectAnimator.ofFloat(mLogo, "rotation", 0.0f, 360f);
    rotation.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {

      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });

    AnimatorSet mSetPlayer = new AnimatorSet();
    mSetPlayer.setDuration(3000);
    mSetPlayer.play(scaleX).with(scaleY).with(rotation);
    mSetPlayer.start();
  }
}
