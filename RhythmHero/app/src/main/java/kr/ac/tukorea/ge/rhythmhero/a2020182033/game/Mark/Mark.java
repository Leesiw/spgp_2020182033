package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;

public class Mark implements IGameObject, Comparable<Mark> {
    public int getAppeared_timing() {
        return appeared_timing;
    }

    protected int appeared_timing;

    public int getType() {
        return type;
    }

    protected int type;

    protected Mark(int appeared_timing) {
        super();
        this.appeared_timing = appeared_timing;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public int compareTo(@NotNull Mark mark) {
        return this.appeared_timing - mark.appeared_timing;
    }
}