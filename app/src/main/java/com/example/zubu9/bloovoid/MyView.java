package com.example.zubu9.bloovoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zubu9 on 4/04/2017.
 */

public class MyView extends View implements View.OnTouchListener, Runnable {
    Bitmap bool;
    Bitmap boolTwo;
    Bitmap boolThree;
    Bitmap boolFour;
    Bitmap sandwich;
    Handler timer;
    int calls = 0;
    float sx = 0.0f;
    float by = 0.0f;
    float by2 = 0.0f;
    float by3 = 0.0f;
    float by4 = 0.0f;
    float bx = 0.0f;
    float bx2 = 250.0f;
    float bx3 = 500.0f;
    float bx4 = 750.0f;
    int boolsAvoided = 0;
    int speed = 5;
    float width;

    Random rand = new Random();


    public float distance (float sx, float bx, float sy, float by){
        return (float) (Math.sqrt  ((Math.pow((sx - bx),2)) + Math.pow((sy - by) , 2))   );
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        bool = BitmapFactory.decodeResource(getResources(), R.drawable.bool);
        boolTwo = BitmapFactory.decodeResource(getResources(), R.drawable.bool);
        boolThree = BitmapFactory.decodeResource(getResources(), R.drawable.bool);
        boolFour = BitmapFactory.decodeResource(getResources(), R.drawable.bool);

        sandwich = BitmapFactory.decodeResource(getResources(), R.drawable.sandwich);
        timer = new Handler();
        timer.postDelayed(this, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = canvas.getWidth() - 100;
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(50);
        super.onDraw(canvas);
        canvas.drawBitmap(sandwich, sx, 1100, p);
        canvas.drawBitmap(bool, bx, by, p);
        canvas.drawBitmap(bool, bx2, by2, p);
        canvas.drawBitmap(bool, bx3, by3, p);
        canvas.drawBitmap(bool, bx4, by4, p);

        canvas.drawText("ABULS AVOIDED: " + boolsAvoided, 50, 50, p);

//        bx = 0.0f;
//        bx2 = (float) (canvas.getWidth() * 0.25);
//        bx3 = (float) (canvas.getWidth() * 0.55);
//        bx4 = (float) (canvas.getWidth() * 0.85);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE){
        sx = event.getX();
        this.invalidate();
       }
        return true;

    }

    @Override
    public void run() {
        ArrayList <Float> distances = new ArrayList<Float>();

        distances.add(distance(sx, bx, 1100, by));
        distances.add(distance(sx, bx2, 1100, by2));
        distances.add(distance(sx, bx3, 1100, by3));
        distances.add(distance(sx, bx4, 1100, by4));

        calls += 1;

        Boolean negativeDistance  = true;

        for (Float t : distances){
            if (t <= 100){
                negativeDistance = false;
            }
        }


        if (negativeDistance){
        if (by < 1200){
        by += speed;
        }else{
            by = 0;
            boolsAvoided +=1;
            bx = rand.nextInt((int) width);
        }

        if (by4 < 1200 && calls > 70){
            by4 += speed;
        }else if (calls > 70){
            by4 = 0;
            boolsAvoided +=1;
            bx4 = rand.nextInt((int) width);

        }

        if (by2 < 1200 && calls > 140){
            by2 += speed;
        }else if (calls > 140){
            by2 = 0;
            boolsAvoided +=1;
            bx2 = rand.nextInt((int) width);

        }


        if (by3 < 1200 && calls > 210){
            by3 += speed;
        }else if (calls > 210){
            by3 = 0;
            boolsAvoided +=1;
            bx3 = rand.nextInt((int) width);
            if (speed <= 30){
            speed += 1;
            }
        }
        timer.postDelayed(this, 10);
        this.invalidate();
        }

    }


}
