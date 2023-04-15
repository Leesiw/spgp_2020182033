package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;

public class Ball extends Sprite {
    private static final float SIZE = 1.f;

    private float x1, y1, x2, y2;
    private float start_timing, end_timing;
    private float ball_start_timing, ball_end_timing;
    private float stoe_time;
    private int return_num;

    private int dir = 1;

    private float angle = 0;

    public Ball(float x1, float y1, float x2, float y2, float start_timing, float end_timing, int return_num){
        super(R.mipmap.ball, x1, y1, SIZE, SIZE);

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.ball_start_timing = start_timing;
        this.end_timing = end_timing;
        this.ball_end_timing = (end_timing - start_timing) / (return_num + 1) + start_timing;
        this.return_num = return_num;

        this.stoe_time = (ball_end_timing - ball_start_timing);
    }

    @Override
    public void update() {
        super.update();

        if(ball_end_timing < MainScene.song_play_time)
        {
            --return_num;
            if(return_num < 0){ return; }
            float temp = x1; x1 = x2; x2 = temp;
            temp = y1; y1 = y2; y2 = temp;
            ball_start_timing = ball_end_timing;
            ball_end_timing = (end_timing - ball_start_timing) / (return_num + 1) + ball_start_timing;
            dir = -dir;
        }

        float c_time = (-ball_start_timing + MainScene.song_play_time);

        this.x = x1 + (x2 - x1) * c_time / stoe_time;
        this.y = y1 + (y2 - y1) * c_time / stoe_time;


        fixDstRect();
        this.angle += BaseScene.frameTime * 180.f * dir;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        //canvas.drawBitmap(bitmap, null, dstRect, null);
        super.draw(canvas);
        canvas.restore();
    }

    public int getReturn_num() {
        return return_num;
    }
}
