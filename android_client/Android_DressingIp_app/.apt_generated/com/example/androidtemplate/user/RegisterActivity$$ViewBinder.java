// Generated code from Butter Knife. Do not modify!
package com.example.androidtemplate.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.example.androidtemplate.user.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
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
    view = finder.findRequiredView(source, 2131361822, "field 'sexSp'");
    target.sexSp = finder.castView(view, 2131361822, "field 'sexSp'");
    view = finder.findRequiredView(source, 2131361802, "field 'passwordEt'");
    target.passwordEt = finder.castView(view, 2131361802, "field 'passwordEt'");
    view = finder.findRequiredView(source, 2131361798, "field 'emailEt'");
    target.emailEt = finder.castView(view, 2131361798, "field 'emailEt'");
    view = finder.findRequiredView(source, 2131361792, "field 'titleLl'");
    target.titleLl = finder.castView(view, 2131361792, "field 'titleLl'");
    view = finder.findRequiredView(source, 2131361820, "field 'weChatEt'");
    target.weChatEt = finder.castView(view, 2131361820, "field 'weChatEt'");
    view = finder.findRequiredView(source, 2131361794, "field 'titleTv'");
    target.titleTv = finder.castView(view, 2131361794, "field 'titleTv'");
    view = finder.findRequiredView(source, 2131361797, "field 'usernameEt'");
    target.usernameEt = finder.castView(view, 2131361797, "field 'usernameEt'");
    view = finder.findRequiredView(source, 2131361818, "field 'telEt'");
    target.telEt = finder.castView(view, 2131361818, "field 'telEt'");
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
    view = finder.findRequiredView(source, 2131361817, "field 'nameEt'");
    target.nameEt = finder.castView(view, 2131361817, "field 'nameEt'");
    view = finder.findRequiredView(source, 2131361819, "field 'qqEt'");
    target.qqEt = finder.castView(view, 2131361819, "field 'qqEt'");
    view = finder.findRequiredView(source, 2131361796, "field 'contentLl'");
    target.contentLl = finder.castView(view, 2131361796, "field 'contentLl'");
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
    view = finder.findRequiredView(source, 2131361821, "field 'birthEt' and method 'onClick'");
    target.birthEt = finder.castView(view, 2131361821, "field 'birthEt'");
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
    view = finder.findRequiredView(source, 2131361812, "field 'activityRegister'");
    target.activityRegister = finder.castView(view, 2131361812, "field 'activityRegister'");
    view = finder.findRequiredView(source, 2131361816, "field 'password2Et'");
    target.password2Et = finder.castView(view, 2131361816, "field 'password2Et'");
  }

  @Override public void unbind(T target) {
    target.picAddTv = null;
    target.sexSp = null;
    target.passwordEt = null;
    target.emailEt = null;
    target.titleLl = null;
    target.weChatEt = null;
    target.titleTv = null;
    target.usernameEt = null;
    target.telEt = null;
    target.registerBtn = null;
    target.rightTv = null;
    target.nameEt = null;
    target.qqEt = null;
    target.contentLl = null;
    target.picAdd2Tv = null;
    target.birthEt = null;
    target.imgIv = null;
    target.leftTv = null;
    target.activityRegister = null;
    target.password2Et = null;
  }
}
