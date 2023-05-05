package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene.MainScene;

public class ScoreMark extends Sprite implements IRecyclable {
    public static final float SIZE = 1.0f;
    private Paint paint;
    private int alpha;

    private static final int[] resIds = {
        R.mipmap.scorex, R.mipmap.score50, R.mipmap.score100, R.mipmap.score300,
            R.mipmap.scorebeat100, R.mipmap.scorebeat300, R.mipmap.scoreelitebeat,
    };

    public ScoreMark() {
        this.width = SIZE;
        this.height = SIZE;
        //super(R.mipmap.score50, 0, 0, SIZE, SIZE);
        paint = new Paint();
        alpha = 255;
    }

    public static ScoreMark get(float x, float y, int score) {
        ScoreMark mark = (ScoreMark) RecycleBin.get(ScoreMark.class);
        if (mark == null) {
            mark = new ScoreMark();
        }
        MainScene.gaugeValue += score;
        mark.init(x, y, score);
        return mark;
    }

    private void init(float x, float y, int score){
        this.setBitmapResource(resIds[score]);
        this.x = x;
        this.y = y;
        this.alpha = 255;
        fixDstRect();
    }

    @Override
    public void update() {
        super.update();
        alpha -= BaseScene.frameTime * 200;
        if(alpha <= 0){
            BaseScene.getTopScene().remove(MainScene.Layer.score_mark, this);
        }
        paint.setAlpha(alpha);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, paint);
    }

    @Override
    public void onRecycle() {
    }
}
