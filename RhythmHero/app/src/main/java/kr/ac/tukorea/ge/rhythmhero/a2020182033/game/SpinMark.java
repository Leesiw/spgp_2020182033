package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.res.ResourcesCompat;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.GameView;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;

public class SpinMark extends Mark implements IRecyclable{

    private float end_timing;
    private static Sprite image;

    private static Paint bgPaint = new Paint();
    private static Paint fgPaint = new Paint();

    private int score;
    private float rad;

    public SpinMark() {
        super(0);

        image = new Sprite(R.mipmap.spinmark, Metrics.game_width / 2, Metrics.game_height / 2 + 0.5f,
                Metrics.game_height - 1.f, Metrics.game_height - 1.f);

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(1.f);
        // Gauge 생성 시점이 GameView.res 가 설정된 이후여야 한다.
        bgPaint.setColor(ResourcesCompat.getColor(GameView.res, R.color.black, null));
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(0.5f);
        fgPaint.setColor(ResourcesCompat.getColor(GameView.res, R.color.mark_purple, null));
        fgPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public static SpinMark get(float appeared_timing, float end_timing) {
        SpinMark mark = (SpinMark) RecycleBin.get(SpinMark.class);
        if (mark == null) {
            mark = new SpinMark();
        }
        mark.init(appeared_timing, end_timing);
        return mark;
    }

    private void init(float appeared_timing, float end_timing) {
        this.score = 0;
        this.appeared_timing = appeared_timing;
        this.end_timing = end_timing;
    }

        @Override
    public void update() {
        super.update();
        if (end_timing - MainScene.song_play_time < -1.f) {
            BaseScene.getTopScene().remove(MainScene.Layer.spin_mark, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.rotate(rad);
        image.draw(canvas);
        canvas.restore();

        canvas.drawLine(15.f, 1.f, 15.0f, 8.0f, bgPaint);
        if (score > 0) {
            canvas.drawLine(15.f, 8.f, 15.0f, 8.f - score / 300.f * 8.f, fgPaint);
        }
    }

    @Override
    public void onRecycle() {
    }

}
