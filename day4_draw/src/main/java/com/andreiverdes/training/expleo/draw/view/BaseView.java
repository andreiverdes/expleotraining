package com.andreiverdes.training.expleo.draw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BaseView extends View {

    private Bitmap drawingBitmap;
    protected Paint paint;
    private PorterDuffXfermode porterDuffSrcOut;
    private PorterDuffXfermode porterDuffSrcIn;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.BLUE);
        porterDuffSrcOut = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        porterDuffSrcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawingBitmap == null) {
            drawingBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            float radius = getWidth() * .3f;
            Canvas secondCanvas = new Canvas();
            secondCanvas.setBitmap(drawingBitmap);
            drawBackground(secondCanvas);
            paint.setXfermode(porterDuffSrcOut);
            paint.setColor(Color.RED);
            secondCanvas.drawCircle(getWidth() * .5f, getHeight() * .5f,
                    radius, paint);
        }
        canvas.drawBitmap(drawingBitmap,0, 0, null);
    }

    protected void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(),  paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (drawingBitmap != null && !drawingBitmap.isRecycled()) {
            drawingBitmap.recycle();
            drawingBitmap = null;
        }
    }

    public static Path roundedRect(
            float left, float top, float right, float bottom, float rx, float ry,
            boolean tl, boolean tr, boolean br, boolean bl
    ){
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        if (tr)
            path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        else{
            path.rLineTo(0, -ry);
            path.rLineTo(-rx,0);
        }
        path.rLineTo(-widthMinusCorners, 0);
        if (tl)
            path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        else{
            path.rLineTo(-rx, 0);
            path.rLineTo(0,ry);
        }
        path.rLineTo(0, heightMinusCorners);

        if (bl)
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        else{
            path.rLineTo(0, ry);
            path.rLineTo(rx,0);
        }

        path.rLineTo(widthMinusCorners, 0);
        if (br)
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        else{
            path.rLineTo(rx,0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;
    }

}
