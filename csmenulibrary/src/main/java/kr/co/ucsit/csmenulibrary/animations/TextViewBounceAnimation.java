package kr.co.ucsit.csmenulibrary.animations;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * Created by kimhyobin on 2018. 3. 6..
 */

public class TextViewBounceAnimation extends Animation {
    float baseTextSize = 10;
    TextView view;
    boolean doSmall = true;

    public TextViewBounceAnimation(TextView view){
        this.view = view;

        this.baseTextSize = view.getTextSize();
        Log.e("Animation", "baseTextSize " + baseTextSize);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        try{
            float tempSize = 0;
            if(doSmall)
                tempSize = (float)(baseTextSize - interpolatedTime * baseTextSize);
            else
                tempSize = (float)(interpolatedTime * baseTextSize);

            if(tempSize <= baseTextSize / 2)
                doSmall = false;

            float resultTextSize = tempSize / 2;
            Log.e("Animation", "resultTextSize " + resultTextSize);
            view.setTextSize(resultTextSize);
            view.requestLayout();
        }catch (Exception ex){
            view.setTextSize(baseTextSize);
            view.requestLayout();
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