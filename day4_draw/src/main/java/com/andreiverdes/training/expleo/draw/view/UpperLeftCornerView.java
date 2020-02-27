package com.andreiverdes.training.expleo.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class UpperLeftCornerView extends BaseView {

    public UpperLeftCornerView(Context context) {
        super(context);
    }

    public UpperLeftCornerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UpperLeftCornerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UpperLeftCornerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void drawBackground(Canvas canvas) {
        Path path = roundedRect(0, 0, getWidth() , getHeight() ,
                getWidth() * .2f,getWidth() * .2f,
                true, false, false, false);
        canvas.drawPath(path,paint);
    }
}
