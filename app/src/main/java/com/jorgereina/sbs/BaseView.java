package com.jorgereina.sbs;

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);
}
