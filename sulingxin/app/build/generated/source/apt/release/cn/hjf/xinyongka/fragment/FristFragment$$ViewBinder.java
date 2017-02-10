// Generated code from Butter Knife. Do not modify!
package cn.hjf.xinyongka.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FristFragment$$ViewBinder<T extends cn.hjf.xinyongka.fragment.FristFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493116, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131493116, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    target.mWebView = null;
  }
}
