// Generated code from Butter Knife. Do not modify!
package com.example.laijie;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Main2Activity$$ViewBinder<T extends com.example.laijie.Main2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427462, "field 'appItem'");
    target.appItem = finder.castView(view, 2131427462, "field 'appItem'");
    view = finder.findRequiredView(source, 2131427461, "field 'tab'");
    target.tab = finder.castView(view, 2131427461, "field 'tab'");
    view = finder.findRequiredView(source, 2131427460, "field 'activityMain2'");
    target.activityMain2 = finder.castView(view, 2131427460, "field 'activityMain2'");
  }

  @Override public void unbind(T target) {
    target.appItem = null;
    target.tab = null;
    target.activityMain2 = null;
  }
}
