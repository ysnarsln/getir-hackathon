package com.getirhackathon.preselection.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.getirhackathon.preselection.models.ShapeProperties;

public class CustomView extends View {

    private ShapeProperties shapeProperties;
    private Paint mPaint;
    private Context mContext;

    public CustomView(Context context, ShapeProperties shapeProperties) {
        super(context);
        this.mContext = context;
        this.shapeProperties = shapeProperties;

        if (mPaint == null)
            mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#"+shapeProperties.getColor()));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (shapeProperties.getType().equals(Constants.TYPE_CIRCLE)) {

            canvas.drawCircle(shapeProperties.getxPosition(), shapeProperties.getyPosition(),
                    shapeProperties.getR(), mPaint);

        } else if (shapeProperties.getType().equals(Constants.TYPE_RECTANGLE)) {

            canvas.drawRect(shapeProperties.getxPosition(), shapeProperties.getyPosition(),
                    shapeProperties.getxPosition() + shapeProperties.getWidth(),
                    shapeProperties.getyPosition() + shapeProperties.getHeight(), mPaint);

        }

    }

}