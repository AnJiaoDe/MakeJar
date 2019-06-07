package com.cy.makejar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cy.manager.Manager;
import com.cy.router.Container;
import com.cy.router.LoadListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager.load(MainActivity.this, new Object[]{1}, new LoadListener() {
                    @Override
                    public void onLoadSuccess(Container container) {

                    }

                    @Override
                    public void onLoadFailed(String errorMsg) {

                    }
                });
            }
        });
    }
}
