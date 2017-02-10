// Generated code from Butter Knife. Do not modify!
package com.example.weimiaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Main2Activity$$ViewBinder<T extends com.example.weimiaobaitiao.activity.Main2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427459, "field 'appItem'");
    target.appItem = finder.castView(view, 2131427459, "field 'appItem'");
    view = finder.findRequiredView(source, 2131427458, "field 'tab'");
    target.tab = finder.castView(view, 2131427458, "field 'tab'");
  }

  @Override public void unbind(T target) {
    target.appItem = null;
    target.tab = null;
  }
}
