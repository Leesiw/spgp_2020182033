package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;

public class SlideMark extends Mark implements IRecyclable {

    public static final float SIZE = 1.0f;
    public static final float RADIUS = SIZE / 2;

    private static final int[][] resIds = {
            {R.mipmap.blue, R.mipmap.green, R.mipmap.purple,
            R.mipmap.red, R.mipmap.yellow},
            {R.mipmap.bluereturn, R.mipmap.greenreturn, R.mipmap.purplereturn,
            R.mipmap.redreturn, R.mipmap.yellowreturn},
            {R.mipmap.bluestraight, R.mipmap.greenstraight, R.mipmap.purplestraight,
            R.mipmap.redstraight, R.mipmap.yellowstraight},
    };

    private int color;
    private float start_timing;
    private float end_timing;
    private int return_num;
    private float x1, y1, x2, y2;

    private HitMark hitmark;
    private Sprite circle1;
    private Sprite circle2;

    private int score;

    private boolean start_circle = true;
    //Sprite road;

    private Paint whitePaint;
    private Paint roadPaint;
    private Ball ball;

    public SlideMark(int num, int color, float x1, float y1, float x2, float y2, float appeared_timing, float start_timing, float end_timing, int return_num) {
        super(appeared_timing);
        this.color = color;
        this.end_timing = end_timing;
        this.return_num = return_num;
        this.start_timing = start_timing;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.score = 0;


        hitmark = new HitMark(num, color, x1, y1, appeared_timing, start_timing);
        circle1 = new Sprite(resIds[0][color], x1, y1, SIZE, SIZE);

        if (return_num > 0){
            circle2 = new Sprite(resIds[1][color], x2, y2, SIZE, SIZE);
        }
        else {
            circle2 = new Sprite(resIds[0][color], x2, y2, SIZE, SIZE);
        }

        this.roadPaint = new Paint();
        switch(color) {
            case 0:
                this.roadPaint.setColor(Color.parseColor("#8CC6E7"));
                //R.color.mark_blue
                break;
            case 1:
                this.roadPaint.setColor(Color.parseColor("#6BCE9E"));
                //R.color.mark_green
                break;
            case 2:
                this.roadPaint.setColor(Color.parseColor("#A7A2DE"));
                //R.color.mark_purple
                break;
            case 3:
                this.roadPaint.setColor(Color.parseColor("#F18D80"));
                //R.color.mark_red
                break;
            case 4:
                this.roadPaint.setColor(Color.parseColor("#FCC43D"));
                //R.color.mark_yellow
                break;
        }
        roadPaint.setStyle(Paint.Style.STROKE);
        //roadPaint.setAntiAlias(true);
        roadPaint.setStrokeWidth(SIZE);

        whitePaint = new Paint();
        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setAntiAlias(true);
       // whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(0.1f);

        ball = new Ball(x1, y1, x2, y2, start_timing, end_timing, return_num);
    }

    @Override
    public void update() {
        super.update();

        if (hitmark != null && start_timing - MainScene.song_play_time < -1.f) {
            hitmark = null;
        }

        if(MainScene.song_play_time > start_timing) {
            ball.update();

            if(ball.getReturn_num() != return_num){
                return_num = ball.getReturn_num();
                if(return_num > 0){
                    if(start_circle){
                        circle1.setBitmapResource(resIds[1][color]);
                        circle2.setBitmapResource(resIds[0][color]);
                    }
                    else{
                        circle1.setBitmapResource(resIds[0][color]);
                        circle2.setBitmapResource(resIds[1][color]);
                    }
                    start_circle = !start_circle;
                }
            }
        }

        if (end_timing - MainScene.song_play_time < -0.f) {
            BaseScene.getTopScene().remove(MainScene.Layer.slide_mark, this);
            BaseScene.getTopScene().add(MainScene.Layer.score_mark, new ScoreMark(x2, y2, score));
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawLine(x1, y1, x2, y2, roadPaint);

        circle1.draw(canvas);
        circle2.draw(canvas);
        canvas.drawCircle(x1, y1, RADIUS, whitePaint);
        canvas.drawCircle(x2, y2, RADIUS, whitePaint);

        if(hitmark != null){
            hitmark.draw(canvas);
        }

        if(MainScene.song_play_time > start_timing) {
            ball.draw(canvas);
        }
    }

    @Override
    public void onRecycle() {
    }
}
