// Generated code from Butter Knife. Do not modify!
package com.example.miaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeActivity$$ViewBinder<T extends com.example.miaobaitiao.activity.HomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624104, "field 'bannerFrescoDemoContent'");
    target.bannerFrescoDemoContent = finder.castView(view, 2131624104, "field 'bannerFrescoDemoContent'");
    view = finder.findRequiredView(source, 2131624105, "field 'recylerview'");
    target.recylerview = finder.castView(view, 2131624105, "field 'recylerview'");
  }

  @Override public void unbind(T target) {
    target.bannerFrescoDemoContent = null;
    target.recylerview = null;
  }
}
