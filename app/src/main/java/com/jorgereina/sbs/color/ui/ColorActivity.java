package com.jorgereina.sbs.color.ui;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jorgereina.sbs.R;
import com.jorgereina.sbs.color.presenter.ColorContract;
import com.jorgereina.sbs.color.presenter.ColorPresenter;

public class ColorActivity extends AppCompatActivity implements ColorContract.ColorView {

    private Button requestBtn;
    private ConstraintLayout constraintLayout;
    private ColorPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.main_view_cl);
        requestBtn = findViewById(R.id.request_btn);
        progressBar = findViewById(R.id.pb);

        presenter = new ColorPresenter();
        presenter.attachView(this);
        hideProgress();


        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                presenter.onColorRequest();
            }
        });
    }

    @Override
    public void showColorRequest(String color) {
        constraintLayout.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void showText(String title) {
        requestBtn.setText(title);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
