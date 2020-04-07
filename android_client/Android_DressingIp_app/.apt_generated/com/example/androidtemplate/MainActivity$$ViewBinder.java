// Generated code from Butter Knife. Do not modify!
package com.example.androidtemplate;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.androidtemplate.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361792, "field 'titleLl'");
    target.titleLl = finder.castView(view, 2131361792, "field 'titleLl'");
    view = finder.findRequiredView(source, 2131361794, "field 'titleTv'");
    target.titleTv = finder.castView(view, 2131361794, "field 'titleTv'");
    view = finder.findRequiredView(source, 2131361847, "field 'twoTv' and method 'onClick'");
    target.twoTv = finder.castView(view, 2131361847, "field 'twoTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361840, "field 'menuRl'");
    target.menuRl = finder.castView(view, 2131361840, "field 'menuRl'");
    view = finder.findRequiredView(source, 2131361842, "field 'menuLv'");
    target.menuLv = finder.castView(view, 2131361842, "field 'menuLv'");
    view = finder.findRequiredView(source, 2131361848, "field 'threeTv' and method 'onClick'");
    target.threeTv = finder.castView(view, 2131361848, "field 'threeTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
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
    view = finder.findRequiredView(source, 2131361839, "field 'categorySp'");
    target.categorySp = finder.castView(view, 2131361839, "field 'categorySp'");
    view = finder.findRequiredView(source, 2131361796, "field 'contentLl'");
    target.contentLl = finder.castView(view, 2131361796, "field 'contentLl'");
    view = finder.findRequiredView(source, 2131361849, "field 'fourTv' and method 'onClick'");
    target.fourTv = finder.castView(view, 2131361849, "field 'fourTv'");
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
    view = finder.findRequiredView(source, 2131361846, "field 'oneTv' and method 'onClick'");
    target.oneTv = finder.castView(view, 2131361846, "field 'oneTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361841, "field 'shadeLv'");
    target.shadeLv = finder.castView(view, 2131361841, "field 'shadeLv'");
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
    view = finder.findRequiredView(source, 2131361843, "field 'usernameTv'");
    target.usernameTv = finder.castView(view, 2131361843, "field 'usernameTv'");
    view = finder.findRequiredView(source, 2131361845, "field 'backTv' and method 'onClick'");
    target.backTv = finder.castView(view, 2131361845, "field 'backTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361844, "field 'backIv' and method 'onClick'");
    target.backIv = finder.castView(view, 2131361844, "field 'backIv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361833, "field 'listview'");
    target.listview = finder.castView(view, 2131361833, "field 'listview'");
    view = finder.findRequiredView(source, 2131361850, "field 'fiveTv' and method 'onClick'");
    target.fiveTv = finder.castView(view, 2131361850, "field 'fiveTv'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.titleLl = null;
    target.titleTv = null;
    target.twoTv = null;
    target.menuRl = null;
    target.menuLv = null;
    target.threeTv = null;
    target.leftTv = null;
    target.categorySp = null;
    target.contentLl = null;
    target.fourTv = null;
    target.imgIv = null;
    target.oneTv = null;
    target.shadeLv = null;
    target.rightTv = null;
    target.usernameTv = null;
    target.backTv = null;
    target.backIv = null;
    target.listview = null;
    target.fiveTv = null;
  }
}
