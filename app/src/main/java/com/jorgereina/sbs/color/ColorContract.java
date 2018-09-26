package com.jorgereina.sbs.color;

import com.jorgereina.sbs.BaseView;

public class ColorContract {

    interface Presenter {

        void onColorRequest();
    }

    interface ColorView extends BaseView {

        void showColorRequest(String color);

        void showText(String title);
    }
}
