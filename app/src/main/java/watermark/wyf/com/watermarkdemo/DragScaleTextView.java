package watermark.wyf.com.watermarkdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 贺卡中可拖动、缩放显示文字的控件
 */
public class DragScaleTextView extends AppCompatTextView implements View.OnTouchListener {

    private static final String TAG = "ZoomTextView";

    private float textSize;
    private float oldDist;
    private float sX;
    private float sY;
    private int pWidth;
    private int pHeight;
    //是否可双指操控缩放，默认为false
    private boolean enabledScale;


    public DragScaleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        initAttr(context, attrs, defStyle);
    }

    public DragScaleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        initAttr(context, attrs, 0);
    }

    public DragScaleTextView(Context context) {
        super(context);
        setOnTouchListener(this);
    }


    private void initAttr(Context context, AttributeSet attrs, int defStyle) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.greeting_cards_DragScaleTextView);
        enabledScale = attributes.getBoolean(R.styleable.greeting_cards_DragScaleTextView_greeting_cards_enabled_scale, false);
    }

    /**
     * 设置是否可以缩放
     *
     * @param enabledScale
     */
    public void setEnabledScale(boolean enabledScale) {
        this.enabledScale = enabledScale;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //在一开始，计算当前字体的大小
        if (textSize == 0) {
            textSize = this.getTextSize();
        }
        // 获取触摸事件的类型，如果是单点是event.getAction()，当涉及到多点触控时，就使用getActionMasked来获取触摸事件类型
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                sX = event.getRawX();
                sY = event.getRawY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 当手指按下的时候，就去获取当前手指的间距
                oldDist = spacing(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (pWidth == 0) {
                    ViewGroup parent = (ViewGroup) getParent();
                    pWidth = parent.getWidth();
                    pHeight = parent.getHeight();
                }
                if (enabledScale) {
                    // 获取当前触摸点的个数
                    if (event.getPointerCount() >= 2) {
                        // 如果触摸点>=2 获取当前两个手指的距离，然后进行缩放
                        float newDist = spacing(event);
                        zoom(newDist / oldDist);
                        //重新置位
                        oldDist = newDist;
                        return true;
                    }
                }
                if (event.getPointerCount() == 1) {
                    int dx = (int) (event.getRawX() - sX);
                    int dy = (int) (event.getRawY() - sY);
                    if (getTranslationX() < -getLeft() && dx < 0) dx = 0;
                    else if (getTranslationX() > (pWidth - getRight()) && dx > 0) dx = 0;
                    if (getTranslationY() < -getTop() && dy < 0) dy = 0;
                    else if (getTranslationY() > (pHeight - getBottom()) && dy > 0) dy = 0;
                    setTranslationX(getTranslationX() + dx);
                    setTranslationY(getTranslationY() + dy);
                    sX = event.getRawX();
                    sY = event.getRawY();
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * 不断进行缩放
     *
     * @param f
     */
    private void zoom(float f) {
        textSize *= f;
        this.setTextSize(px2sp(getContext(), textSize));
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 计算两个手指的大小
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        //获取第一个点的x坐标和第二个点的x坐标
        float x = event.getX(0) - event.getX(1);
        //分别获取y坐标
        float y = event.getY(0) - event.getY(1);
        //使用勾股定理计算两点距离
        return (float) Math.sqrt(x * x + y * y);
    }
}