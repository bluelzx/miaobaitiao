// Generated code from Butter Knife. Do not modify!
package com.example.weimiaobaitiao.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FristFragment$$ViewBinder<T extends com.example.weimiaobaitiao.fragment.FristFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427466, "field 'webView'");
    target.webView = finder.castView(view, 2131427466, "field 'webView'");
  }

  @Override public void unbind(T target) {
    target.webView = null;
  }
}
