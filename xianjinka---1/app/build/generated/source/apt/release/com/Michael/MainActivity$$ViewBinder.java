// Generated code from Butter Knife. Do not modify!
package com.Michael;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.Michael.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558560, "field 'appItem'");
    target.appItem = finder.castView(view, 2131558560, "field 'appItem'");
    view = finder.findRequiredView(source, 2131558559, "field 'tab'");
    target.tab = finder.castView(view, 2131558559, "field 'tab'");
    view = finder.findRequiredView(source, 2131558558, "field 'activityHome'");
    target.activityHome = finder.castView(view, 2131558558, "field 'activityHome'");
  }

  @Override public void unbind(T target) {
    target.appItem = null;
    target.tab = null;
    target.activityHome = null;
  }
}
