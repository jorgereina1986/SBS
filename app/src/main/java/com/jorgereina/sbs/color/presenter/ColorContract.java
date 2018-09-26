package com.jorgereina.sbs.color.presenter;

import com.jorgereina.sbs.BaseView;

public interface ColorContract {

    interface Presenter {

        void onColorRequest();
    }

    interface ColorView extends BaseView {

        void showColorRequest(String color);

        void showText(String title);
    }
}
