package kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.res.ResourcesCompat;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.GameView;

public class Gauge {
    private Paint fgPaint = new Paint();
    private Paint bgPaint = new Paint();

    float start_x, start_y, value_width;
    public Gauge(float width, int fgColorResId, int bgColorResId, float x, float y, float value_width) {
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(width);
        this.start_x = x;
        this.start_y = y;
        this.value_width = value_width;
        // Gauge 생성 시점이 GameView.res 가 설정된 이후여야 한다.
        bgPaint.setColor(ResourcesCompat.getColor(GameView.res, bgColorResId, null));
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(width / 2);
        fgPaint.setColor(ResourcesCompat.getColor(GameView.res, fgColorResId, null));
        fgPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    public void draw(Canvas canvas, float value) {
        canvas.drawLine(start_x, start_y, start_x + value_width * 1.0f, start_y, bgPaint);
        if (value > 0) {
            canvas.drawLine(start_x, start_y, start_x + value_width * value, start_y, fgPaint);
        }
    }
}
