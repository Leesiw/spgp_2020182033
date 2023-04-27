package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.view.Metrics;

public class SpinMark extends Mark implements IRecyclable{

    private float end_timing;
    private static Sprite image;

    private float rad;

    public SpinMark() {
        super(0);

        image = new Sprite(R.mipmap.spinmark, Metrics.game_width / 2, Metrics.game_height / 2,
                Metrics.game_width, Metrics.game_width);
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
    }

    @Override
    public void onRecycle() {
    }

}
