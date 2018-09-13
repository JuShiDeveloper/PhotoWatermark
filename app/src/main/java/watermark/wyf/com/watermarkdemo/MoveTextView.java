package watermark.wyf.com.watermarkdemo;


import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 贺卡中显示文字的View，可拖动
 */
public class MoveTextView extends AppCompatTextView implements View.OnTouchListener {
    private float sX;
    private float sY;
    private int pWidth;
    private int pHeight;

    public MoveTextView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public MoveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public MoveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                sX = motionEvent.getRawX();
                sY = motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (motionEvent.getRawX() - sX);
                int dy = (int) (motionEvent.getRawY() - sY);
                if (pWidth == 0) {
                    ViewGroup parent = (ViewGroup) getParent();
                    pWidth = parent.getWidth() + 100;
                    pHeight = parent.getHeight() + 80;
                }
                if (getTranslationX() < -getLeft() && dx < 0) dx = 0;
                else if (getTranslationX() > (pWidth - getRight()) && dx > 0) dx = 0;
                if (getTranslationY() < -getTop() && dy < 0) dy = 0;
                else if (getTranslationY() > (pHeight - getBottom()) && dy > 0) dy = 0;
                setTranslationX(getTranslationX() + dx);
                setTranslationY(getTranslationY() + dy);
                sX = motionEvent.getRawX();
                sY = motionEvent.getRawY();
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

}
