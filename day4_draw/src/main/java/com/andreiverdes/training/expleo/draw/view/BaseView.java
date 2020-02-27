package com.andreiverdes.training.expleo.draw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public abstract class BaseView extends View {

    protected Paint paint;
    protected Bitmap drawingBitmap;

    protected boolean drawHole = true;
    protected boolean invalidateDraw = false;
    private PorterDuffXfermode porterDuffOut;
    private PorterDuffXfermode porterDuffIn;

    public BaseView(Context context) {
        super(context);
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        init();
    }

    private void init(){
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.BLUE);
        porterDuffOut = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        porterDuffIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.setOnClickListener(v -> {
            drawHole = !drawHole;
            invalidateDraw = true;
            invalidate();
        });
    }

    @Override protected void onDraw(Canvas pCanvas) {
        super.onDraw(pCanvas);
        if(drawingBitmap == null){
            this.drawingBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(drawingBitmap);
//            canvas.drawPaint(paint);
            drawBackground(canvas);
            if (drawHole) {
                drawCircle(canvas, porterDuffOut);
            } else {
                drawCircle(canvas, porterDuffIn);
            }
        }
        if(drawingBitmap != null){
            pCanvas.drawBitmap(drawingBitmap, 0, 0, null);
            if (invalidateDraw) {
                if (drawHole) {
                    drawCircle(pCanvas, porterDuffOut);
                } else {
                    drawCircle(pCanvas, porterDuffIn);
                }
                invalidateDraw = false;
            }
        }
    }

    protected void drawBackground(Canvas canvas) {
        RectF rect = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
    }

    private void drawCircle(Canvas canvas, PorterDuffXfermode duffXfermode) {
        this.paint.setXfermode(duffXfermode);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth() * .5f, getHeight() * .5f, getHeight() * .3f, paint);
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (drawingBitmap != null && !drawingBitmap.isRecycled()) {
            drawingBitmap.recycle();
            drawingBitmap = null;
        }
        drawHole = true;
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
