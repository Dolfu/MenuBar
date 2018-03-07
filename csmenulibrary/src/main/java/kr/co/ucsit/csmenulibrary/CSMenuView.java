package kr.co.ucsit.csmenulibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.ucsit.csmenulibrary.animations.ImageViewBounceAnimation;
import kr.co.ucsit.csmenulibrary.animations.TextViewBounceAnimation;
import kr.co.ucsit.csmenulibrary.interfaces.CSMenuEventInterface;

/**
 * Created by kimhyobin on 2018. 3. 2..
 */

public class CSMenuView extends LinearLayout{

    //region Variable
    private static String LOG_TAG = "CSMenuView";

    private LinearLayout linearLayoutBackground = null;

    private ImageView imageViewLogo = null;
    private TextView textViewTitle = null;
    private ImageView imageViewMenu = null;

    private LinearLayout linearTopBar = null;
    private FrameLayout frameLayoutLogo = null;
    private FrameLayout frameLayoutMenu = null;

    private static long animationDefaultDuration = 200;

    private float titleTextFontSize = 15;

    private int topBarDPHeight = 40;

    private CSMenuEventInterface CSMenuEventInterface = null;

    private boolean isUsingTitleTextEvent = false;
    private boolean isUsingLeftImageButtonEvent = false;
    private boolean isUsingRightImageButtonEvent = false;
    //endregion

    /**
     * INIT
     * @param context Parent Context
     * */
    public CSMenuView(Context context){
        super(context);
        initView();
    }

    public CSMenuView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        initView();
        getAttrs(attributeSet);
    }

    /**
     * Set Event Interface
     * @param CSMenuEventInterface Event Interface
     * */
    public void setCSMenuEventInterface(CSMenuEventInterface CSMenuEventInterface){
        this.CSMenuEventInterface = CSMenuEventInterface;
    }

    /**
     * initialize view & child view
     * */
    private void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(infService);
        View view = layoutInflater.inflate(R.layout.csmenu_layout, this, false);
        addView(view);

        linearLayoutBackground = (LinearLayout)findViewById(R.id.linearLayoutBackground);

        linearTopBar = (LinearLayout)findViewById(R.id.linearTopBar);
        frameLayoutLogo = (FrameLayout)findViewById(R.id.frameLayoutLogo);
        frameLayoutMenu = (FrameLayout)findViewById(R.id.frameLayoutMenu);

        imageViewLogo = (ImageView)findViewById(R.id.imageViewLogo);
        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        imageViewMenu = (ImageView)findViewById(R.id.imageViewMenu);

        textViewTitle.setOnClickListener(titleTextViewOnClickListener);
        imageViewLogo.setOnClickListener(leftImageOnClickListener);
        imageViewMenu.setOnClickListener(rightImageOnClickListener);
    }

    /**
     * get written attributset from user
     * @param attrs AttributeSet
     * */
    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CSMenuView);
        setTypeArray(typedArray);
    }

    /**
     * @param typedArray
     * */
    private void setTypeArray(TypedArray typedArray) {
        int tmpBackgroundColor = typedArray.getColor(R.styleable.CSMenuView_menuBackgroundColor, Color.WHITE);
        setMenuViewBackgroundColor(tmpBackgroundColor);

        int tmpLogoResourceID = typedArray.getResourceId(R.styleable.CSMenuView_logoImage, R.drawable.ic_launcher2);
        setImageViewLogoResource(tmpLogoResourceID);

        int tmpMenuResourceID = typedArray.getResourceId(R.styleable.CSMenuView_menuImage, R.drawable.ic_launcher2);
        setImageViewMenuResource(tmpMenuResourceID);

        String tmpTitleText = typedArray.getString(R.styleable.CSMenuView_menuTitle);
        setTitle(tmpTitleText);

        int tmpTitleTextColor = typedArray.getColor(R.styleable.CSMenuView_menuTitleTextColor, Color.BLACK);
        textViewTitle.setTextColor(tmpTitleTextColor);

        float tmpTitleTextFontSize = typedArray.getDimensionPixelSize(R.styleable.CSMenuView_menuTitleFontSize, (int) titleTextFontSize);
        setTitleTextFontSize(tmpTitleTextFontSize);

        float tmpTopBarDPHeight = typedArray.getDimensionPixelSize(R.styleable.CSMenuView_topBarHeight, topBarDPHeight);
        setTopBarDPHeight(tmpTopBarDPHeight);

        typedArray.recycle();
    }

    private void setMenuViewBackgroundColor(int tmpBackgroundColor){
        linearLayoutBackground.setBackgroundColor(tmpBackgroundColor);
    }

    private void setTitleTextFontSize(float tmpFontSize){
        try {
            float fontSize = tmpFontSize / 2;
            textViewTitle.setTextSize(fontSize);
            titleTextFontSize = fontSize;
            //Log.e(LOG_TAG, "[setTitleTextFontSize] titleTextFontSize(dp) : " + String.valueOf(titleTextFontSize));
        }catch (Exception ex){
            Log.e(LOG_TAG, "[setTitleTextFontSize]" + ex.getMessage().toString());
        }
    }

    private void setTopBarDPHeight(float tmpTopBarDPHeight){
        try {
            int dpHeight = (int)tmpTopBarDPHeight;
            linearTopBar.getLayoutParams().height = dpHeight;
            frameLayoutLogo.getLayoutParams().width = dpHeight;
            frameLayoutMenu.getLayoutParams().width = dpHeight;
            topBarDPHeight = dpHeight / 2;
            //Log.e(LOG_TAG, "[setTopBarDPHeight] topBarDPHeight(dp) : " + String.valueOf(topBarDPHeight));
        }catch (Exception ex){
            Log.e(LOG_TAG, "[setTopBarDPHeight]" + ex.getMessage().toString());
        }
    }

    public void setImageViewLogoResource(int resource){
        try{
            imageViewLogo.setBackgroundResource(resource);
        }catch (Exception ex){
            Log.e(LOG_TAG, "[setImageViewLogoResource]" + ex.getMessage().toString());
        }
    }

    public void setTitle(String title){
        try{
            textViewTitle.setText(title);
        }catch (Exception ex){
            Log.e(LOG_TAG, "[setTitle]" + ex.getMessage().toString());
        }
    }

    public void setImageViewMenuResource(int resource){
        try{
            imageViewMenu.setBackgroundResource(resource);
        }catch (Exception ex){
            Log.e(LOG_TAG, "[setImageViewMenuResource]" + ex.getMessage().toString());
        }
    }

    public void setUsingTitleTextEvent(boolean isUsing){
        this.isUsingTitleTextEvent = isUsing;
    }

    public void setUsingLeftImageButtonEvent(boolean isUsing){
        this.isUsingLeftImageButtonEvent = isUsing;
    }

    public void setUsingRightImageButtonEvent(boolean isUsing){
        this.isUsingRightImageButtonEvent = isUsing;
    }

    public boolean isUsingTitleTextEvent() {
        return isUsingTitleTextEvent;
    }

    public boolean isUsingLeftImageButtonEvent() {
        return isUsingLeftImageButtonEvent;
    }

    public boolean isUsingRightImageButtonEvent() {
        return isUsingRightImageButtonEvent;
    }

    private View.OnClickListener titleTextViewOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(isUsingTitleTextEvent){
                TextViewBounceAnimation animation = new TextViewBounceAnimation((TextView)view);
                animation.setDuration(animationDefaultDuration);
                view.startAnimation(animation);
            }
        }
    };

    private View.OnClickListener leftImageOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(CSMenuEventInterface != null && isUsingLeftImageButtonEvent){
                ImageViewBounceAnimation animation = new ImageViewBounceAnimation(view);
                animation.setDuration(animationDefaultDuration);
                view.startAnimation(animation);
                CSMenuEventInterface.OnLeftImageClicked();
            }
        }
    };

    private View.OnClickListener rightImageOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(CSMenuEventInterface != null && isUsingRightImageButtonEvent){
                ImageViewBounceAnimation animation = new ImageViewBounceAnimation(view);
                animation.setDuration(animationDefaultDuration);
                view.startAnimation(animation);
                CSMenuEventInterface.OnRightImageClicked();
            }
        }
    };
}
