package com.zhq.commonlib.statelayout.source.anim;

import android.view.View;

import androidx.annotation.NonNull;

import com.zhq.commonlib.statelayout.source.StateLayout;
import com.zhq.commonlib.statelayout.source.TransitionAnimator;


/**
 * The alpha transition animator.
 *
 * @author airsaid
 */
public class AlphaTransitionAnimator implements TransitionAnimator {
  @Override
  public boolean isPlayTogether() {
    return false;
  }

  @Override
  public void onExitAnimation(@NonNull StateLayout layout, @NonNull View stateView) {
    stateView.animate().alpha(0f);
  }

  @Override
  public void onEntryAnimation(@NonNull StateLayout layout, @NonNull View stateView) {
    stateView.setAlpha(0f);
    stateView.animate().alpha(1f);
  }

  @Override
  public void onReset(@NonNull StateLayout layout, @NonNull View stateView) {
    stateView.setAlpha(1f);
  }
}