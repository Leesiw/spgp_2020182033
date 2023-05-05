package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

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
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SpinMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene.MainScene;

public class SpinMark extends Mark implements IRecyclable{

    private float end_timing;
    private static Sprite image;

    private static Paint bgPaint = new Paint();
    private static Paint fgPaint = new Paint();

    private static float CenterX = Metrics.game_width / 2, CenterY = Metrics.game_height / 2 + 0.5f;

    private int score;
    private double cur_angle;
    private double prev_angle;
    private boolean touched;

    public SpinMark() {
        super(0);

        image = new Sprite(R.mipmap.spinmark, CenterX, CenterY,
                Metrics.game_height - 1.f, Metrics.game_height - 1.f);

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(1.f);
        bgPaint.setColor(ResourcesCompat.getColor(GameView.res, R.color.black, null));
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(0.5f);
        fgPaint.setColor(ResourcesCompat.getColor(GameView.res, R.color.mark_purple, null));
        fgPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public static SpinMark get(SpinMarkData data) {
        SpinMark mark = (SpinMark) RecycleBin.get(SpinMark.class);
        if (mark == null) {
            mark = new SpinMark();
        }
        mark.init(data.appeared_timing, data.getEnd_timing());
        return mark;
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
        this.prev_angle = 0.f;
        this.cur_angle = 0.f;
        this.touched = false;
    }

        @Override
    public void update() {
        super.update();
        score -= 10.f * BaseScene.frameTime;
        if(score < 0){
            score = 0;
        }
        if (end_timing - MainScene.song_play_time < 0.f) {
            if (score > 290) {
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(CenterX, CenterY, 3));
                MainScene.score.add(300);
            }
            else if(score > 250){
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(CenterX, CenterY, 2));
                MainScene.score.add(100);
            }
            else if(score > 150f){
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(CenterX, CenterY, 1));
                MainScene.score.add(50);
            }
            else{
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(CenterX, CenterY, 0));
                MainScene.score.add(0);
            }

            BaseScene.getTopScene().remove(MainScene.Layer.spin_mark, this);
        }
    }

    public void setPrevAngle(float x, float y){
        touched = true;
        prev_angle = Math.toDegrees(Math.atan2(x - CenterX, CenterY - y));
    }
    public void isTouched(float x, float y){
        if(!touched){
            prev_angle = Math.toDegrees(Math.atan2(x - CenterX, CenterY - y));
            return;
        }

        double new_angle = Math.toDegrees(Math.atan2(x - CenterX, CenterY - y));

        cur_angle += new_angle - prev_angle;

        score += Math.abs(new_angle - prev_angle) / 10.f;

        if(score > 300){
            score = 300;
        }

        prev_angle = new_angle;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.rotate((float)cur_angle, CenterX, CenterY);
        image.draw(canvas);
        canvas.restore();

        canvas.drawLine(15.f, 1.f, 15.0f, 8.0f, bgPaint);
        if (score > 0) {
            canvas.drawLine(15.f, 8.f, 15.0f, 8.f - score / 300.f * 7.f, fgPaint);
        }
    }

    @Override
    public void onRecycle() {
    }

}
