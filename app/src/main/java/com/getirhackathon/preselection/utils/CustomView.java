package com.getirhackathon.preselection.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.getirhackathon.preselection.models.Element;

public class CustomView extends View {

    private Element mElement;
    private Paint mPaint;
    private Context mContext;

    public CustomView(Context context, Element element) {
        super(context);
        this.mContext = context;
        this.mElement = element;

        if (mPaint == null)
            mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#"+ element.getColor()));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mElement.getType().equals(Constants.TYPE_CIRCLE)) {

            canvas.drawCircle(100+mElement.getxPosition(), 100+mElement.getyPosition(),
                    mElement.getR(), mPaint);

        } else if (mElement.getType().equals(Constants.TYPE_RECTANGLE)) {

            canvas.drawRect(100+mElement.getxPosition(), 100+mElement.getyPosition(),
                    100+mElement.getxPosition() + mElement.getWidth(),
                    100+mElement.getyPosition() + mElement.getHeight(), mPaint);

        }

    }

}