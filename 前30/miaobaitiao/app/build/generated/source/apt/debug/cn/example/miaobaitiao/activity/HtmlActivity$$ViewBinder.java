// Generated code from Butter Knife. Do not modify!
package com.example.miaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HtmlActivity$$ViewBinder<T extends com.example.miaobaitiao.activity.HtmlActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624107, "field 'webView'");
    target.webView = finder.castView(view, 2131624107, "field 'webView'");
    view = finder.findRequiredView(source, 2131624106, "field 'bar'");
    target.bar = finder.castView(view, 2131624106, "field 'bar'");
  }

  @Override public void unbind(T target) {
    target.webView = null;
    target.bar = null;
  }
}
