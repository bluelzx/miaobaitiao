// Generated code from Butter Knife. Do not modify!
package ericssonlabs;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BarCodeTestActivity$$ViewBinder<T extends ericssonlabs.BarCodeTestActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558600, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558600, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
  }
}
