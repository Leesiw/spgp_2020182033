package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.Mark;

public class SlideMarkData extends Mark {
    public int getNum() {
        return num;
    }

    public int getColor() {
        return color;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public float getStart_timing() {
        return start_timing;
    }

    public float getEnd_timing() {
        return end_timing;
    }

    public int getReturn_num() {
        return return_num;
    }

    protected int num;
    protected int color;
    protected float x1;
    protected float y1;
    protected float x2;
    protected float y2;
    protected float start_timing;
    protected float end_timing;
    protected int return_num;

    public SlideMarkData(int num, int color, float x1, float y1, float x2, float y2,
    int appeared_timing, float start_timing, float end_timing, int return_num) {
        super(appeared_timing);
        this.num = num;
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start_timing = start_timing;
        this.end_timing = end_timing;
        this.return_num = return_num;
        this.type = 1;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
