package kr.co.ucsit.csmenulibrary.animations;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by kimhyobin on 2018. 3. 6..
 */

public class ImageViewBounceAnimation extends Animation {
    int baseMargin = 10;
    int targetHeight;
    View view;
    boolean doSmall = true;

    public ImageViewBounceAnimation(View view){
        this.view = view;
        this.targetHeight = view.getHeight();
        try{
            if(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                this.baseMargin = marginLayoutParams.getMarginStart();
            }
        }catch (Exception ex){
            this.baseMargin = 0;
        }
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        try{
            float tempSize = 0;
            if(doSmall)
                tempSize = (float)(targetHeight - interpolatedTime * targetHeight);
            else
                tempSize = (float)(interpolatedTime * targetHeight);

            if(tempSize <= targetHeight / 2)
                doSmall = false;

            int margin = (int)((targetHeight - tempSize) / 3) + baseMargin;

            if(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                marginLayoutParams.setMargins(margin, margin, margin, margin);
                view.requestLayout();
            }
        }catch (Exception ex){
            if(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                marginLayoutParams.setMargins(baseMargin, baseMargin, baseMargin, baseMargin);
                view.requestLayout();
            }
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}