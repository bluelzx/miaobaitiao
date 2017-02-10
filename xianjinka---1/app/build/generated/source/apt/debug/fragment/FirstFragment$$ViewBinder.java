// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FirstFragment$$ViewBinder<T extends fragment.FirstFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558600, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558600, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558589, "field 'spinner1'");
    target.spinner1 = finder.castView(view, 2131558589, "field 'spinner1'");
    view = finder.findRequiredView(source, 2131558585, "field 'radioButtonMainIncome' and method 'onClick'");
    target.radioButtonMainIncome = finder.castView(view, 2131558585, "field 'radioButtonMainIncome'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558586, "field 'radioButtonMainExpend' and method 'onClick'");
    target.radioButtonMainExpend = finder.castView(view, 2131558586, "field 'radioButtonMainExpend'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558588, "field 'buttonConfirm' and method 'onClick'");
    target.buttonConfirm = finder.castView(view, 2131558588, "field 'buttonConfirm'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558590, "field 'textMainDate' and method 'onClick'");
    target.textMainDate = finder.castView(view, 2131558590, "field 'textMainDate'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558592, "field 'webView'");
    target.webView = finder.castView(view, 2131558592, "field 'webView'");
    view = finder.findRequiredView(source, 2131558582, "field 'Layout'");
    target.Layout = finder.castView(view, 2131558582, "field 'Layout'");
    view = finder.findRequiredView(source, 2131558584, "field 'radioGroup'");
    target.radioGroup = finder.castView(view, 2131558584, "field 'radioGroup'");
    view = finder.findRequiredView(source, 2131558587, "field 'editTextMianInput'");
    target.editTextMianInput = finder.castView(view, 2131558587, "field 'editTextMianInput'");
    view = finder.findRequiredView(source, 2131558591, "field 'listViewMainBill'");
    target.listViewMainBill = finder.castView(view, 2131558591, "field 'listViewMainBill'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.spinner1 = null;
    target.radioButtonMainIncome = null;
    target.radioButtonMainExpend = null;
    target.buttonConfirm = null;
    target.textMainDate = null;
    target.webView = null;
    target.Layout = null;
    target.radioGroup = null;
    target.editTextMianInput = null;
    target.listViewMainBill = null;
  }
}
