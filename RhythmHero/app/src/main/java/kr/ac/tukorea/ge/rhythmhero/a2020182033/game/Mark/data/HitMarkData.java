package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.Mark;

public class HitMarkData extends Mark {
    public int getNum() {
        return num;
    }

    public int getColor() {
        return color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getTouch_timing() {
        return touch_timing;
    }

    protected int num;
    protected int color;
    protected float x;
    protected float y;
    protected float touch_timing;

    public HitMarkData(int num, int color, float x, float y, int appeared_timing, float touch_timing) {
        super(appeared_timing);
        this.num = num;
        this.color = color;
        this.x = x;
        this.y = y;
        this.touch_timing = touch_timing;
        this.type = 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
