package my.edu.utar.Cheow1903371;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class HighlightView extends View {

    public boolean isHighlighted;


    public HighlightView(Context context) {
        super(context);
        isHighlighted = false;
    }

    public void highlight() {
        isHighlighted = true;
        invalidate();
    }

    public void unhighlight() {
        isHighlighted = false;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;
        int radius = Math.min(centerX, centerY);
        int gap = radius / 2;
        if (isHighlighted) {
            Paint paint = getHighlightPaint();
            canvas.drawCircle(centerX - gap, centerY - gap, radius, paint);
            canvas.drawCircle(centerX + gap, centerY - gap, radius, paint);
            canvas.drawCircle(centerX - gap, centerY + gap, radius, paint);
            canvas.drawCircle(centerX + gap, centerY + gap, radius, paint);
            canvas.drawLine(centerX - radius, centerY, centerX - gap, centerY, paint);
            canvas.drawLine(centerX + gap, centerY, centerX + radius, centerY, paint);
            canvas.drawLine(centerX, centerY - radius, centerX, centerY - gap, paint);
            canvas.drawLine(centerX, centerY + gap, centerX, centerY + radius, paint);
        } else {
            canvas.drawCircle(centerX - gap, centerY - gap, radius, getUnhighlightPaint());
            canvas.drawCircle(centerX + gap, centerY - gap, radius, getUnhighlightPaint());
            canvas.drawCircle(centerX - gap, centerY + gap, radius, getUnhighlightPaint());
            canvas.drawCircle(centerX + gap, centerY + gap, radius, getUnhighlightPaint());
        }
    }

    private Paint getHighlightPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(65, 253, 254));
        return paint;
    }

    private Paint getUnhighlightPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        return paint;
    }
}

