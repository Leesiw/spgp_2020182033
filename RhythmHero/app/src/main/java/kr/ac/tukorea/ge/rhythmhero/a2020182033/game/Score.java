package kr.ac.tukorea.ge.rhythmhero.a2020182033.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.rhythmhero.a2020182033.R;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.rhythmhero.a2020182033.framework.res.BitmapPool;

public class Score implements IGameObject {
    private final Bitmap bitmap;
    private final int srcCharWidth, srcCharHeight;
    private final float right, top;
    private final float dstCharWidth, dstCharHeight;
    private Rect srcRect = new Rect();

    private int num;
    private RectF dstRect = new RectF();
    private int score, displayScore;

    public Score(float top, float right, int num) {
        this.bitmap = BitmapPool.get(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
        this.num = num;
        this.dstCharWidth = 0.5f;
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;
    }

    public void setScore(int score) {
        this.score = score;
        this.displayScore = score;
    }
    public int getScore() {
        return score;
    }

    @Override
    public void update() {
        int diff = score - displayScore;
        if (diff == 0) return;
        if (-10 < diff && diff < 0) {
            displayScore--;
        } else if (0 < diff && diff < 10) {
            displayScore++;
        } else {
            displayScore += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        float x = right;

        int loop_num = 0;

        while (value >= 0) {
            ++loop_num;
            if(value == 0 && this.displayScore != 0){return;}
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);

            if(value != 0) {
                value /= 10;
            }
            else{
                if(loop_num >= num) {
                    return;
                }
            }
        }
    }

    public void add(int amount) {
        score += amount;
    }
}
