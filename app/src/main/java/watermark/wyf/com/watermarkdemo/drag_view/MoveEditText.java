package watermark.wyf.com.watermarkdemo.drag_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import watermark.wyf.com.watermarkdemo.R;

/**
 * @author JuShi
 * Create Time：2018/9/14
 */
public class MoveEditText extends FrameLayout {
    private int lastX;
    private int lastY;
    private InnerEditText editText;
    private int parentWidth, parentHeight;
    private boolean showBottomLine;
    private float textSize;
    private int textColor;
    private String text;
    private boolean focusable;
    private boolean touchMode;
    private float shadowRadius;
    private float shadowX;
    private float shadowY;
    private int shadowColor;
    private int bottomLineColor;
    private int bottomLineHintColor;
    private float bottomLineHeight;

    public MoveEditText(@NonNull Context context) {
        this(context, null);
    }

    public MoveEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        initAttrs(attrs);
        initEditText();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MoveEditText);
        textSize = array.getDimensionPixelSize(R.styleable.MoveEditText_textSize, 13);
        textColor = array.getColor(R.styleable.MoveEditText_textColor, Color.BLACK);
        text = array.getString(R.styleable.MoveEditText_text);
        focusable = array.getBoolean(R.styleable.MoveEditText_focusable, true);
        touchMode = array.getBoolean(R.styleable.MoveEditText_focusableInTouchMode, true);
        shadowRadius = array.getFloat(R.styleable.MoveEditText_shadowRadius, 0f);
        shadowX = array.getFloat(R.styleable.MoveEditText_shadowX, 0f);
        shadowY = array.getFloat(R.styleable.MoveEditText_shadowY, 0f);
        shadowColor = array.getColor(R.styleable.MoveEditText_shadowColor, Color.GRAY);
        bottomLineColor = array.getColor(R.styleable.MoveEditText_bottomLineColor, Color.BLUE);
        bottomLineHintColor = array.getColor(R.styleable.MoveEditText_bottomLineHintColor, Color.GRAY);
        bottomLineHeight = array.getFloat(R.styleable.MoveEditText_bottomLineHeight, 5);
        showBottomLine = array.getBoolean(R.styleable.MoveEditText_showBottomLine, false);
        array.recycle();
    }

    private void initEditText() {
        editText = new InnerEditText(getContext());
        setTextColor(textColor);
        setTextSize(textSize / 2);
        setText(text);
        setFocusable(focusable);
        setFocusableInTouchMode(touchMode);
        setShadowLayer(shadowRadius, shadowX, shadowY, shadowColor);
        setBackground(null);
        editText.setBottomLineHintColor(bottomLineHintColor);
        editText.setBottomLineHeight(bottomLineHeight);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    editText.setBottomLineColor(bottomLineColor);
                } else {
                    editText.setBottomLineHintColor(bottomLineHintColor);
                }
            }
        });
        addView(editText);
    }

    public void setShowBottomLine(boolean isShow) {
        this.showBottomLine = isShow;
    }

    public void setBackground(Drawable background) {
        editText.setBackground(background);
    }

    public void setBackgroundColor(int color) {
        editText.setBackgroundColor(color);
    }

    public void setBackgroundResource(int resId) {
        editText.setBackgroundResource(resId);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setTextColor(int color) {
        editText.setTextColor(color);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setFocusableInTouchMode(boolean touchMode) {
        editText.setFocusableInTouchMode(touchMode);
    }

    public void setFocusable(boolean focusable) {
        editText.setFocusable(focusable);
    }

    public int getCurrentTextColor() {
        return editText.getCurrentTextColor();
    }

    public void setTextSize(float textSize) {
        editText.setTextSize(textSize);
    }

    public float getTextSize() {
        return editText.getTextSize();
    }

    public void setAlpha(float alpha) {
        editText.setAlpha(alpha);
    }

    public float getAlpha() {
        return editText.getAlpha();
    }

    public void setTypeface(Typeface tf) {
        editText.setTypeface(tf);
    }

    public Typeface getTypeface() {
        return editText.getTypeface();
    }

    public void setShadowLayer(float radius, float dx, float dy, int color) {
        editText.setShadowLayer(radius, dx, dy, color);
    }

    public void setGravity(int gravity) {
        editText.setGravity(gravity);
    }

    public int getGraivty() {
        return editText.getGravity();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = (int) ev.getRawX();
            lastY = (int) ev.getRawY();
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getRawX() - lastX);
                int dy = (int) (event.getRawY() - lastY);
                if (parentWidth == 0) {
                    ViewGroup parent = (ViewGroup) getParent();
                    parentWidth = parent.getWidth();
                    parentHeight = parent.getHeight();
                }
                if (getTranslationX() < -getLeft() && dx < 0) dx = 0;
                else if (getTranslationX() > (parentWidth - getRight()) && dx > 0) dx = 0;
                if (getTranslationY() < -getTop() && dy < 0) dy = 0;
                else if (getTranslationY() > (parentHeight - getBottom()) && dy > 0) dy = 0;
                setTranslationX(getTranslationX() + dx);
                setTranslationY(getTranslationY() + dy);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        return true;
    }

    private class InnerEditText extends AppCompatEditText {
        private int downX, downY;
        private Paint paint;

        public InnerEditText(Context context) {
            super(context);
            initialize();
        }

        public InnerEditText(Context context, AttributeSet attrs) {
            super(context, attrs, 0);
            initialize();
        }

        public InnerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initialize();
        }

        private void initialize() {
            initPaint();
        }

        private void initPaint() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = (int) event.getRawX();
                    downY = (int) event.getRawY();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = (int) (event.getRawX() - downX);
                    int dy = (int) (event.getRawY() - downY);
                    if (Math.abs(dx) <= ViewConfiguration.get(getContext()).getScaledTouchSlop() &&
                            Math.abs(dy) <= ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    break;
            }
            return super.dispatchTouchEvent(event);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
//            canvas.drawLine(0,0,getWidth() - 20,0,paint);
//            canvas.drawLine(0,0,0,getHeight() - 20,paint);
            if (showBottomLine) {
                //画底部的横线
                canvas.drawLine(5, getHeight() - 30, getWidth() - 5, getHeight() - 30, paint);
            }
        }

        public void setBottomLineColor(int color) {
            paint.setColor(color);
            invalidate();
        }

        public void setBottomLineHintColor(int color) {
            paint.setColor(color);
            invalidate();
        }

        public void setBottomLineHeight(float height) {
            paint.setStrokeWidth(height);
        }


        //        @Override
//        protected void onSelectionChanged(int selStart, int selEnd) {
//            super.onSelectionChanged(selStart, selEnd);
//            if (selStart == selEnd) {
//                setSelection(getText().length());
//            }
//        }
    }

}
