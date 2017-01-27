package space.levan.memory.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import space.levan.memory.R;

/**
 * Created by WangZhiYao on 2017-01-24.
 */

public class BookImageBehavior extends CoordinatorLayout.Behavior<FrameLayout>  {

    private final static String TAG = "behavior";
    private Context mContext;
    private AttributeSet mAttrs;

    private float mCustomFinalHeight;

    private int mStartXPosition;
    private float mStartToolbarPosition;
    private int mStartYPosition;
    private int mFinalYPosition;
    private int mStartHeight;
    private int mFinalXPosition;
    private float mChangeBehaviorPoint;

    public BookImageBehavior(Context context, AttributeSet attrs)
    {
        mContext = context;
        mAttrs = attrs;
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency)
    {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency)
    {
        maybeInitProperties(child, dependency);

        final int maxScrollDistance = (int) (mStartToolbarPosition) -200;
        float expandedPercentageFactor = dependency.getY() / maxScrollDistance;

        if (expandedPercentageFactor < mChangeBehaviorPoint)
        {
            float distanceXToSubtract = mStartXPosition - mFinalXPosition;
            float distanceYToSubtract = mStartYPosition - mFinalYPosition;

            child.setX(mStartXPosition - distanceXToSubtract);
            child.setY(mStartYPosition - distanceYToSubtract);
        }
        else
        {
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (2f - expandedPercentageFactor)) + (mStartHeight/2);

            child.setX(mStartXPosition - child.getWidth()/2);
            child.setY(mStartYPosition - distanceYToSubtract);
        }
        return true;
    }

    private void maybeInitProperties(FrameLayout child, View dependency)
    {
        if (mStartYPosition == 0)
            mStartYPosition = (int) (dependency.getY());

        if (mFinalYPosition == 0)
            mFinalYPosition = (dependency.getHeight() /2);

        if (mStartHeight == 0)
            mStartHeight = child.getHeight();

        if (mStartXPosition == 0)
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));

        if (mFinalXPosition == 0)
            mFinalXPosition = mContext.getResources()
                    .getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material)
                    + ((int) mCustomFinalHeight / 2);

        if (mStartToolbarPosition == 0)
            mStartToolbarPosition = dependency.getY();

        if (mChangeBehaviorPoint == 0)
            mChangeBehaviorPoint = (child.getHeight() - mCustomFinalHeight)
                    / (2f * (mStartYPosition - mFinalYPosition));
    }
}
