// Generated code from Butter Knife. Do not modify!
package com.example.androidtemplate;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TuijianAddActivity$$ViewBinder<T extends com.example.androidtemplate.TuijianAddActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361796, "field 'contentLl'");
    target.contentLl = finder.castView(view, 2131361796, "field 'contentLl'");
    view = finder.findRequiredView(source, 2131361794, "field 'titleTv'");
    target.titleTv = finder.castView(view, 2131361794, "field 'titleTv'");
    view = finder.findRequiredView(source, 2131361813, "field 'picAddTv' and method 'onClick'");
    target.picAddTv = finder.castView(view, 2131361813, "field 'picAddTv'");
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
    view = finder.findRequiredView(source, 2131361814, "field 'picAdd2Tv' and method 'onClick'");
    target.picAdd2Tv = finder.castView(view, 2131361814, "field 'picAdd2Tv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361834, "field 'msgEt'");
    target.msgEt = finder.castView(view, 2131361834, "field 'msgEt'");
    view = finder.findRequiredView(source, 2131361839, "field 'categorySp'");
    target.categorySp = finder.castView(view, 2131361839, "field 'categorySp'");
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
    view = finder.findRequiredView(source, 2131361828, "field 'okBtn' and method 'onClick'");
    target.okBtn = finder.castView(view, 2131361828, "field 'okBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361795, "field 'rightTv' and method 'onClick'");
    target.rightTv = finder.castView(view, 2131361795, "field 'rightTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361815, "field 'imgIv'");
    target.imgIv = finder.castView(view, 2131361815, "field 'imgIv'");
    view = finder.findRequiredView(source, 2131361817, "field 'nameEt'");
    target.nameEt = finder.castView(view, 2131361817, "field 'nameEt'");
  }

  @Override public void unbind(T target) {
    target.contentLl = null;
    target.titleTv = null;
    target.picAddTv = null;
    target.titleLl = null;
    target.picAdd2Tv = null;
    target.msgEt = null;
    target.categorySp = null;
    target.leftTv = null;
    target.okBtn = null;
    target.rightTv = null;
    target.imgIv = null;
    target.nameEt = null;
  }
}
