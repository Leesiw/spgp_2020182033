package kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.objects.Sprite;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.BaseScene;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Ball;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Mark.data.SlideMarkData;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.game.Scene.MainScene;

public class SlideMark extends Mark implements IRecyclable {

    public static final float SIZE = 1.0f;
    public static final float RADIUS = SIZE / 2;

    private static final int[][] resIds = {
            {R.mipmap.blue, R.mipmap.green, R.mipmap.purple,
            R.mipmap.red, R.mipmap.yellow},
            {R.mipmap.bluereturn, R.mipmap.greenreturn, R.mipmap.purplereturn,
            R.mipmap.redreturn, R.mipmap.yellowreturn}
    };

    private int color;
    private float start_timing, end_timing, full_time;
    private int return_num;
    private float x1, y1, x2, y2;

    private float cur_x, cur_y;
    private int num;

    private float line1x1, line1y1, line1x2, line1y2;
    private float line2x1, line2y1, line2x2, line2y2;

    private HitMark hitmark;
    private Sprite circle1;
    private Sprite circle2;

    private boolean isTouching;

    private int score;

    private boolean start_circle = true;

    private static Paint whitePaint = new Paint();
    private Paint roadPaint = new Paint();
    private Ball ball;

    public SlideMark() {
        super(0);
        roadPaint.setStyle(Paint.Style.STROKE);
        roadPaint.setStrokeWidth(SIZE);

        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setAntiAlias(true);
        //whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(0.1f);
    }

    public static SlideMark get(SlideMarkData data) {
        SlideMark mark = (SlideMark) RecycleBin.get(SlideMark.class);
        if (mark == null) {
            mark = new SlideMark();
        }
        mark.init(data.getNum(), data.getColor(), data.getX1(), data.getY1(), data.getX2(), data.getY2(), data.appeared_timing,
                data.getStart_timing(), data.getEnd_timing(), data.getReturn_num());
        return mark;
    }

    public static SlideMark get(int num, int color, float x1, float y1, float x2, float y2,
                                int appeared_timing, float start_timing, float end_timing, int return_num) {
        SlideMark mark = (SlideMark) RecycleBin.get(SlideMark.class);
        if (mark == null) {
            mark = new SlideMark();
        }
        mark.init(num, color, x1, y1, x2, y2, appeared_timing, start_timing, end_timing, return_num);
        return mark;
    }

    private void init(int num, int color, float x1, float y1, float x2, float y2,
                      int appeared_timing, float start_timing, float end_timing, int return_num) {
        this.appeared_timing = appeared_timing;
        this.color = color;
        this.end_timing = end_timing;
        this.return_num = return_num;
        this.start_timing = start_timing;
        this.isTouching = false;
        this.full_time = end_timing - start_timing;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.score = 0;

        this.cur_x = 0.f;
        this.cur_y = 0.f;

        this.num = 0;

        float dx = x1 - x2;
        float dy = y1 - y2;
        double radian = Math.atan2(dy, dx);

        dx = SIZE * (float) (Math.cos(radian + 90.f)) / 2;
        dy = SIZE * (float) (Math.sin(radian + 90.f)) / 2;
        this.line1x1 = x1 + dx;
        this.line1y1 = y1 + dy;
        this.line1x2 = x2 + dx;
        this.line1y2 = y2 + dy;

        dx = SIZE * (float) (Math.cos(radian - 90.f)) / 2;
        dy = SIZE * (float) (Math.sin(radian - 90.f)) / 2;
        this.line2x1 = x1 + dx;
        this.line2y1 = y1 + dy;
        this.line2x2 = x2 + dx;
        this.line2y2 = y2 + dy;


        hitmark = HitMark.get(num, color, x1, y1, appeared_timing, start_timing);
        circle1 = new Sprite(resIds[0][color], x1, y1, SIZE, SIZE);

        if (return_num > 0){
            circle2 = new Sprite(resIds[1][color], x2, y2, SIZE, SIZE);
        }
        else {
            circle2 = new Sprite(resIds[0][color], x2, y2, SIZE, SIZE);
        }

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

        ball = Ball.get(x1, y1, x2, y2, start_timing, end_timing, return_num);
    }

    public void touchedHitMark(float x, float y){
        this.isTouching = true;
        if(hitmark != null){
            if(CollisionHelper.collides(hitmark, x, y)) {
                int new_score = hitmark.isTouchedInSlide();
                score += new_score;
                MainScene.score.add(new_score);
                hitmark = null;
            }
        }
        this.cur_x = x;
        this.cur_y = y;
    }

    public void setXY(float x, float y){
        this.isTouching = true;
        this.cur_x = x;
        this.cur_y = y;
    }

    public void TouchUp(){
        this.isTouching = false;
    }

    @Override
    public void update() {
        super.update();

        if (hitmark != null && start_timing - MainScene.song_play_time < -0.3f) {
            hitmark = null;
        }

        if(MainScene.song_play_time > start_timing) {
            if(full_time / 9 * (num + 1) < MainScene.song_play_time - start_timing){
                if(num < 8) {
                    if (isTouching && CollisionHelper.collides(ball, cur_x, cur_y)) {
                        score += 30;
                        MainScene.combo.add(1);
                        if (MainScene.combo.getScore() > MainScene.max_combo) {
                            MainScene.max_combo = MainScene.combo.getScore();
                        }
                        MainScene.gaugeValue += 1;
                        MainScene.score.add(30);
                    }
                    else{
                        MainScene.combo.setScore(0);
                    }
                }
               num++;
            }

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

        if (end_timing - MainScene.song_play_time < 0.f) {
            if(isTouching && CollisionHelper.collides(ball, cur_x, cur_y)) {
                MainScene.score.add(30);
                score += 30;
            }
            BaseScene.getTopScene().remove(MainScene.Layer.slide_mark, this);
            if(score == 300) {
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(ball.getX(), ball.getY(), 3));
            }
            else if(score >= 100){
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(ball.getX(), ball.getY(), 2));
            }
            else if(score >= 50){
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(ball.getX(), ball.getY(), 1));
            }
            else{
                BaseScene.getTopScene().add(MainScene.Layer.score_mark, ScoreMark.get(ball.getX(), ball.getY(), 0));
            }

            //Log.d("Score", ""+ score);
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

        canvas.drawLine(line1x1, line1y1, line1x2, line1y2, whitePaint);
        canvas.drawLine(line2x1, line2y1, line2x2, line2y2, whitePaint);

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
