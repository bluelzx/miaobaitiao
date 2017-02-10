// Generated code from Butter Knife. Do not modify!
package com.example.weimiaobaitiao.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AboutFragment$$ViewBinder<T extends com.example.weimiaobaitiao.fragment.AboutFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427465, "field 'layout' and method 'onClick'");
    target.layout = finder.castView(view, 2131427465, "field 'layout'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.layout = null;
  }
}
