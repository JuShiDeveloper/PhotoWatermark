package watermark.wyf.com.watermarkdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MoveEditText extends FrameLayout {
    private int lastX;
    private int lastY;
    private InnerEditText editText;
    private int parentWidth, parentHeight;

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
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MoveEditText);
        float textSize = array.getDimensionPixelSize(R.styleable.MoveEditText_textSize, 12);
        int textColor = array.getColor(R.styleable.MoveEditText_textColor, Color.BLACK);
        String text = array.getString(R.styleable.MoveEditText_text);
        array.recycle();
        editText = new InnerEditText(getContext());
        editText.setTextColor(textColor);
        editText.setTextSize(textSize);
        editText.setText(text);
        editText.setBackground(null);
        addView(editText);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setTextColor(int color) {
        editText.setTextColor(color);
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

        public InnerEditText(Context context) {
            super(context, null);
        }

        public InnerEditText(Context context, AttributeSet attrs) {
            super(context, attrs, 0);
        }

        public InnerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
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

//        @Override
//        protected void onSelectionChanged(int selStart, int selEnd) {
//            super.onSelectionChanged(selStart, selEnd);
//            if (selStart == selEnd) {
//                setSelection(getText().length());
//            }
//        }
    }
}
