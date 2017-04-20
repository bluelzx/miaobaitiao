// Generated code from Butter Knife. Do not modify!
package com.example.miaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Main2Activity$$ViewBinder<T extends com.example.miaobaitiao.activity.Main2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624130, "field 'appItem'");
    target.appItem = finder.castView(view, 2131624130, "field 'appItem'");
  }

  @Override public void unbind(T target) {
    target.appItem = null;
  }
}
