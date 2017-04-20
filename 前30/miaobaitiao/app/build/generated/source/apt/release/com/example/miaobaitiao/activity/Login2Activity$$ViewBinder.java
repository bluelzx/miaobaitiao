// Generated code from Butter Knife. Do not modify!
package com.example.miaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Login2Activity$$ViewBinder<T extends com.example.miaobaitiao.activity.Login2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624115, "field 'etPhone'");
    target.etPhone = finder.castView(view, 2131624115, "field 'etPhone'");
    view = finder.findRequiredView(source, 2131624118, "field 'etCode'");
    target.etCode = finder.castView(view, 2131624118, "field 'etCode'");
    view = finder.findRequiredView(source, 2131624119, "field 'btGetCode' and method 'onViewClicked'");
    target.btGetCode = finder.castView(view, 2131624119, "field 'btGetCode'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624120, "method 'onViewClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.etPhone = null;
    target.etCode = null;
    target.btGetCode = null;
  }
}
