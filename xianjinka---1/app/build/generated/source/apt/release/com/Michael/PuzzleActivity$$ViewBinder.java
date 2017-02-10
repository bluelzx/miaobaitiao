// Generated code from Butter Knife. Do not modify!
package com.Michael;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PuzzleActivity$$ViewBinder<T extends com.Michael.PuzzleActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558563, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131558563, "field 'viewPager'");
    view = finder.findRequiredView(source, 2131558631, "field 'tabLayout'");
    target.tabLayout = finder.castView(view, 2131558631, "field 'tabLayout'");
    view = finder.findRequiredView(source, 2131558630, "field 'appbarLayout'");
    target.appbarLayout = finder.castView(view, 2131558630, "field 'appbarLayout'");
    view = finder.findRequiredView(source, 2131558562, "field 'coord'");
    target.coord = finder.castView(view, 2131558562, "field 'coord'");
    view = finder.findRequiredView(source, 2131558561, "field 'activityPuzzle'");
    target.activityPuzzle = finder.castView(view, 2131558561, "field 'activityPuzzle'");
  }

  @Override public void unbind(T target) {
    target.viewPager = null;
    target.tabLayout = null;
    target.appbarLayout = null;
    target.coord = null;
    target.activityPuzzle = null;
  }
}
