package com.zhq.commonlib.statelayout.source;


/**
 * The content state. this is a marker state to mark whether it is a content layout.
 *
 * @author airsaid
 */
public class ContentState implements State {
  @Override
  public int getLayoutId() {
    throw new UnsupportedOperationException();
  }
}
