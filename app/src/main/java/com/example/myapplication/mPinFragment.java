package com.example.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class mPinFragment extends Fragment {
    int counter = 0;
    long startTime = SystemClock.elapsedRealtime();
    TextView timelapse,cordinates;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mpin,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button btn1 = getView().findViewById(R.id.btn1);
        final Button btn2 = getView().findViewById(R.id.btn2);
        final Button btn3 = getView().findViewById(R.id.btn3);
        final Button btn4 = getView().findViewById(R.id.btn4);
        final Button btn5 = getView().findViewById(R.id.btn5);
        final Button btn6 = getView().findViewById(R.id.btn6);
        final Button btn7 = getView().findViewById(R.id.btn7);
        final Button btn8 = getView().findViewById(R.id.btn8);
        final Button btn9 = getView().findViewById(R.id.btn9);
        final Button btn0 = getView().findViewById(R.id.btn0);
        final Button btnclr = getView().findViewById(R.id.btnclr);
        final Button btnenter = getView().findViewById(R.id.btnenter);
        final TextView tvNewUser = getView().findViewById(R.id.tvNewUser);

        timelapse = getView().findViewById(R.id.timelapse);
        cordinates = getView().findViewById(R.id.cordinates);
        tvNewUser.setPaintFlags(tvNewUser.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnclr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        btn1.setOnTouchListener(handleTouch);
        btn2.setOnTouchListener(handleTouch);
        btn3.setOnTouchListener(handleTouch);
        btn4.setOnTouchListener(handleTouch);
        btn5.setOnTouchListener(handleTouch);
        btn6.setOnTouchListener(handleTouch);
        btn7.setOnTouchListener(handleTouch);
        btn8.setOnTouchListener(handleTouch);
        btn9.setOnTouchListener(handleTouch);
        btn0.setOnTouchListener(handleTouch);
        btnclr.setOnTouchListener(handleTouch);
        btnenter.setOnTouchListener(handleTouch);



    }
    View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int)event.getRawX();
                int y = (int)event.getRawY();
                cordinates.setText("Cordinates are x:"+x+" and y:"+y);
                long difference = (SystemClock.elapsedRealtime()-startTime)/1000;
                if(counter == 0)
                    timelapse.setText("Time counter started");
                else
                    timelapse.setText("Time elapsed "+difference+"seconds");
                startTime = SystemClock.elapsedRealtime();
                counter++;
            }
            return true;
        }
    };
}
