package com.trncic.library;

import com.trncic.library.util.AttrUtils;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.element.Element;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;

/**
 * Created by igortrncic on 6/18/15.
 */
public class DottedProgressBar extends Component implements Component.DrawTask {
    private final float mDotSize;
    private final float mSpacing;
    private final int mJumpingSpeed;
    private Element mActiveDot;
    private Element mInactiveDot;
    private Color mEmptyDotsColor;
    private Color mActiveDotColor;

    private boolean isInProgress;
    private boolean isActiveDrawable = false;
    private boolean isInactiveDrawable = false;

    private int mActiveDotIndex;

    private int mNumberOfDots;
    private Paint mPaint;
    private int mPaddingLeft;
    private EventHandler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNumberOfDots != 0)
                mActiveDotIndex = (mActiveDotIndex + 1) % mNumberOfDots;
            DottedProgressBar.this.invalidate();
            mHandler.postTask(mRunnable, mJumpingSpeed);
        }
    };

    public DottedProgressBar(Context context, AttrSet attrs) {
        super(context, attrs);

        isInProgress = false;
        mHandler = new EventHandler(EventRunner.getMainEventRunner());

        mActiveDot = AttrUtils.getElementFromAttr(attrs, AttrUtils.activeDot, null);
        mInactiveDot = AttrUtils.getElementFromAttr(attrs, AttrUtils.inactiveDot, null);
        mDotSize = AttrUtils.getDimenFromAttr(attrs, AttrUtils.dotSize, 50);
        mSpacing = AttrUtils.getDimenFromAttr(attrs, AttrUtils.spacing, 10);
        mActiveDotIndex = AttrUtils.getIntFromAttr(attrs, AttrUtils.activeDotIndex, 0);
        mJumpingSpeed = AttrUtils.getIntFromAttr(attrs, AttrUtils.jumpingSpeed, 500);

        // Check whether element is media or colour. if it has width, that means it is element
        if(mInactiveDot.getHeight() > 0) {
            isInactiveDrawable = true;
        } else {
            mEmptyDotsColor  = AttrUtils.getColorFromAttr(attrs, AttrUtils.inactiveDot, Color.BLACK);
        }
        if(mActiveDot.getHeight() > 0) {
            isActiveDrawable = true;
        } else {
            mActiveDotColor = AttrUtils.getColorFromAttr(attrs, AttrUtils.activeDot, Color.RED);
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_STYLE);

        addDrawTask(this);
    }

    private int calculateDotsNumber(int width) {
        int number = (int) (width / (mDotSize + mSpacing));
        mPaddingLeft = (int) ((width % (mDotSize + mSpacing)) / 2);
        return number;
    }

    public void startProgress() {
        isInProgress = true;
        mActiveDotIndex = -1;
        mHandler.removeTask(mRunnable);
        mHandler.postTask(mRunnable);
    }

    public void stopProgress() {
        isInProgress = false;
        mHandler.removeTask(mRunnable);
        invalidate();
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        int parentWidth = EstimateSpec.getSize(component.getWidth());
        int widthWithoutPadding = parentWidth - getPaddingLeft() - getPaddingRight();
        mNumberOfDots = calculateDotsNumber(widthWithoutPadding);

        for (int i = 0; i < mNumberOfDots; i++) {
            int x = (int) (getPaddingLeft() + mPaddingLeft + mSpacing / 2 + i * (mSpacing + mDotSize));
            if (isInactiveDrawable) {
                mInactiveDot.setBounds(x, getPaddingTop(), (int) (x + mDotSize), getPaddingTop() + (int) mDotSize);
                mInactiveDot.drawToCanvas(canvas);
            } else {
                mPaint.setColor(mEmptyDotsColor);
                canvas.drawCircle(x + mDotSize / 2, getPaddingTop() + mDotSize / 2, mDotSize / 2, mPaint);
            }
        }
        if (isInProgress) {
            int x = (int) (getPaddingLeft() + mPaddingLeft + mSpacing / 2 + mActiveDotIndex * (mSpacing + mDotSize));
            if (isActiveDrawable) {
                mActiveDot.setBounds(x, getPaddingTop(), (int) (x + mDotSize), getPaddingTop() + (int) mDotSize);
                mActiveDot.drawToCanvas(canvas);
            } else {
                mPaint.setColor(mActiveDotColor);
                canvas.drawCircle(x + mDotSize / 2, getPaddingTop() + mDotSize / 2, mDotSize / 2, mPaint);
            }
        }
    }
}