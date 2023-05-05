package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;

public class Mark implements IGameObject {
    public float getAppeared_timing() {
        return appeared_timing;
    }

    protected float appeared_timing;

    protected Mark(float appeared_timing) {
        super();
        this.appeared_timing = appeared_timing;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
