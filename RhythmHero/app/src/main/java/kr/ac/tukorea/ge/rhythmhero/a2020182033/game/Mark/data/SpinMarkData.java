package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.Mark;

public class SpinMarkData extends Mark {
    public float getEnd_timing() {
        return end_timing;
    }

    protected float end_timing;

    public SpinMarkData(float appeared_timing, float end_timing) {
        super(appeared_timing);
        this.end_timing = end_timing;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
