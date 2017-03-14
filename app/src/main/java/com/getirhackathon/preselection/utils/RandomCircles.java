package com.getirhackathon.preselection.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

// EXPERIMENTAL CLASS - http://stackoverflow.com/a/36358138
// You can use this class in xml

public class RandomCircles extends View {

    public int height;
    Context context;
    private Paint mPaint;

    private static final int MAX_SIZE = 200;
    private List<Data> dataList = new ArrayList<>();

    public RandomCircles(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for (int i = 0; i <= 50; i++) {
            int x = (int)(Math.random() * w);
            int y = (int)(Math.random() * h);
            int size = (int)(Math.random() * MAX_SIZE);
            addCircle(x, y, size);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Data data : dataList) {
            int size = data.size;
            mPaint.setAlpha(255 - size);
            canvas.drawCircle(data.x, data.y, data.size, mPaint);
        }
    }

    private void addCircle(float x, float y, int size) {
        dataList.add(new Data(x, y, size));
    }

    class Data {
        public float x;
        public float y;
        public int size;

        public Data(float x, float y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
}