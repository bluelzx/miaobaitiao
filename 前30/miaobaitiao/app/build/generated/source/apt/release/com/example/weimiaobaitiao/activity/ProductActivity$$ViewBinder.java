// Generated code from Butter Knife. Do not modify!
package com.example.miaobaitiao.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProductActivity$$ViewBinder<T extends com.example.miaobaitiao.activity.ProductActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624132, "field 'descHead'");
    target.descHead = finder.castView(view, 2131624132, "field 'descHead'");
    view = finder.findRequiredView(source, 2131624142, "field 'tvDemand1'");
    target.tvDemand1 = finder.castView(view, 2131624142, "field 'tvDemand1'");
    view = finder.findRequiredView(source, 2131624143, "field 'tvDemand2'");
    target.tvDemand2 = finder.castView(view, 2131624143, "field 'tvDemand2'");
    view = finder.findRequiredView(source, 2131624145, "field 'tvTips1'");
    target.tvTips1 = finder.castView(view, 2131624145, "field 'tvTips1'");
    view = finder.findRequiredView(source, 2131624146, "field 'tvTips2'");
    target.tvTips2 = finder.castView(view, 2131624146, "field 'tvTips2'");
    view = finder.findRequiredView(source, 2131624147, "field 'apply' and method 'onViewClicked'");
    target.apply = finder.castView(view, 2131624147, "field 'apply'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624224, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624224, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624134, "field 'lines'");
    target.lines = finder.castView(view, 2131624134, "field 'lines'");
    view = finder.findRequiredView(source, 2131624136, "field 'timeLimit'");
    target.timeLimit = finder.castView(view, 2131624136, "field 'timeLimit'");
    view = finder.findRequiredView(source, 2131624137, "field 'cost'");
    target.cost = finder.castView(view, 2131624137, "field 'cost'");
    view = finder.findRequiredView(source, 2131624138, "field 'rate'");
    target.rate = finder.castView(view, 2131624138, "field 'rate'");
    view = finder.findRequiredView(source, 2131624139, "field 'level'");
    target.level = finder.castView(view, 2131624139, "field 'level'");
    view = finder.findRequiredView(source, 2131624140, "field 'difficulty'");
    target.difficulty = finder.castView(view, 2131624140, "field 'difficulty'");
    view = finder.findRequiredView(source, 2131624225, "method 'onViewClicked'");
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
    target.descHead = null;
    target.tvDemand1 = null;
    target.tvDemand2 = null;
    target.tvTips1 = null;
    target.tvTips2 = null;
    target.apply = null;
    target.toolbar = null;
    target.lines = null;
    target.timeLimit = null;
    target.cost = null;
    target.rate = null;
    target.level = null;
    target.difficulty = null;
  }
}
