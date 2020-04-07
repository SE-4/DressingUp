// Generated code from Butter Knife. Do not modify!
package com.example.androidtemplate.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UpdateUserActivity$$ViewBinder<T extends com.example.androidtemplate.user.UpdateUserActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361798, "field 'emailEt'");
    target.emailEt = finder.castView(view, 2131361798, "field 'emailEt'");
    view = finder.findRequiredView(source, 2131361819, "field 'qqEt'");
    target.qqEt = finder.castView(view, 2131361819, "field 'qqEt'");
    view = finder.findRequiredView(source, 2131361826, "field 'updateBtn' and method 'onClick'");
    target.updateBtn = finder.castView(view, 2131361826, "field 'updateBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361797, "field 'usernameEt'");
    target.usernameEt = finder.castView(view, 2131361797, "field 'usernameEt'");
    view = finder.findRequiredView(source, 2131361795, "field 'rightTv'");
    target.rightTv = finder.castView(view, 2131361795, "field 'rightTv'");
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
    view = finder.findRequiredView(source, 2131361821, "field 'birthEt'");
    target.birthEt = finder.castView(view, 2131361821, "field 'birthEt'");
    view = finder.findRequiredView(source, 2131361822, "field 'sexSp'");
    target.sexSp = finder.castView(view, 2131361822, "field 'sexSp'");
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
    view = finder.findRequiredView(source, 2131361815, "field 'imgIv'");
    target.imgIv = finder.castView(view, 2131361815, "field 'imgIv'");
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
    view = finder.findRequiredView(source, 2131361818, "field 'telEt'");
    target.telEt = finder.castView(view, 2131361818, "field 'telEt'");
    view = finder.findRequiredView(source, 2131361820, "field 'weChatEt'");
    target.weChatEt = finder.castView(view, 2131361820, "field 'weChatEt'");
  }

  @Override public void unbind(T target) {
    target.emailEt = null;
    target.qqEt = null;
    target.updateBtn = null;
    target.usernameEt = null;
    target.rightTv = null;
    target.leftTv = null;
    target.birthEt = null;
    target.sexSp = null;
    target.titleLl = null;
    target.picAdd2Tv = null;
    target.imgIv = null;
    target.contentLl = null;
    target.titleTv = null;
    target.picAddTv = null;
    target.telEt = null;
    target.weChatEt = null;
  }
}
