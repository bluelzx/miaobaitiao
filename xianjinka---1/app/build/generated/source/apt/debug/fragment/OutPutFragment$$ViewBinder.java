// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OutPutFragment$$ViewBinder<T extends fragment.OutPutFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558593, "field 'textArcIncomeBegin'");
    target.textArcIncomeBegin = finder.castView(view, 2131558593, "field 'textArcIncomeBegin'");
    view = finder.findRequiredView(source, 2131558594, "field 'textArcIncomeEnd'");
    target.textArcIncomeEnd = finder.castView(view, 2131558594, "field 'textArcIncomeEnd'");
    view = finder.findRequiredView(source, 2131558595, "field 'textButtonArcIncomBegin' and method 'onClick'");
    target.textButtonArcIncomBegin = finder.castView(view, 2131558595, "field 'textButtonArcIncomBegin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558596, "field 'textButtonArcIncomEnd' and method 'onClick'");
    target.textButtonArcIncomEnd = finder.castView(view, 2131558596, "field 'textButtonArcIncomEnd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558597, "field 'buttonArcSearch' and method 'onClick'");
    target.buttonArcSearch = finder.castView(view, 2131558597, "field 'buttonArcSearch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558599, "field 'arcChartViewArcOutarcChartView'");
    target.arcChartViewArcOutarcChartView = finder.castView(view, 2131558599, "field 'arcChartViewArcOutarcChartView'");
  }

  @Override public void unbind(T target) {
    target.textArcIncomeBegin = null;
    target.textArcIncomeEnd = null;
    target.textButtonArcIncomBegin = null;
    target.textButtonArcIncomEnd = null;
    target.buttonArcSearch = null;
    target.arcChartViewArcOutarcChartView = null;
  }
}
