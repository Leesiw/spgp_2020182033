package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;

public class Button extends Sprite implements IBoxCollidable {


    private int id;

    public int getId() {
        return id;
    }


    public Button(int resId, float x, float y, float width, float height, int id) {
        super(resId, x, y, width, height);
        this.id = id;
        fixDstRect();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
