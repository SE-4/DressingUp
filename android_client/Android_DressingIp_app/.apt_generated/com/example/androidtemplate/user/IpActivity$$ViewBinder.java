// Generated code from Butter Knife. Do not modify!
package com.example.androidtemplate.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IpActivity$$ViewBinder<T extends com.example.androidtemplate.user.IpActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361793, "field 'leftTv' and method 'onClick'");
    target.leftTv = finder.castView(view, 2131361793, "field 'leftTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361795, "field 'rightTv'");
    target.rightTv = finder.castView(view, 2131361795, "field 'rightTv'");
    view = finder.findRequiredView(source, 2131361794, "field 'titleTv'");
    target.titleTv = finder.castView(view, 2131361794, "field 'titleTv'");
    view = finder.findRequiredView(source, 2131361823, "field 'registerBtn' and method 'onClick'");
    target.registerBtn = finder.castView(view, 2131361823, "field 'registerBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361792, "field 'titleLl'");
    target.titleLl = finder.castView(view, 2131361792, "field 'titleLl'");
    view = finder.findRequiredView(source, 2131361838, "field 'ipEt'");
    target.ipEt = finder.castView(view, 2131361838, "field 'ipEt'");
    view = finder.findRequiredView(source, 2131361837, "field 'ipTv'");
    target.ipTv = finder.castView(view, 2131361837, "field 'ipTv'");
    view = finder.findRequiredView(source, 2131361796, "field 'contentLl'");
    target.contentLl = finder.castView(view, 2131361796, "field 'contentLl'");
  }

  @Override public void unbind(T target) {
    target.leftTv = null;
    target.rightTv = null;
    target.titleTv = null;
    target.registerBtn = null;
    target.titleLl = null;
    target.ipEt = null;
    target.ipTv = null;
    target.contentLl = null;
  }
}
