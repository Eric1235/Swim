package com.scnu.swimmingtrainingsystem.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.scnu.swimmingtrainingsystem.R;

/**
 * 自定义圆形或圆角imageview
 * @author lixinkun
 *
 * 2015年9月17日
 */
public class RoundImageView extends View{

	private int type;
	private static final int TYPE_CIRCLE = 0;
	private static final int TYEP_ROUND = 1;
	
	private Bitmap mSrc;
	private int width;
	private int height;

	public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView,defStyleAttr,0);
		
		int n = a.getIndexCount();
		for(int i = 0 ; i < n ; i++){
			int attr = a.getIndex(i);
			switch(attr){
			case R.styleable.RoundImageView_src:
				mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
				break;
			case R.styleable.RoundImageView_type:
				type = a.getInt(attr, 0);
				break;
			case R.styleable.RoundImageView_borderRadius:
				type = a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
						, 10f, getResources().getDisplayMetrics()));
				break;
			}
		}
		a.recycle();
		
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		if(specMode == MeasureSpec.EXACTLY){
			width = specSize;
		}else{
			int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
			if(specMode == MeasureSpec.AT_MOST){
				width = Math.min(specSize, desireByImg);
			}
		}
		
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		if(specMode == MeasureSpec.EXACTLY){
			height = specSize;
		}else{
			int desire = getPaddingBottom() + getPaddingTop() + mSrc.getHeight();
			if(specMode == MeasureSpec.AT_MOST){
				height = Math.min(specSize, desire);
			}
		}
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		switch(type){
		case TYPE_CIRCLE:
			int min = Math.min(width, height);
			mSrc = Bitmap.createScaledBitmap(mSrc, min, min,false);//进行裁剪
			canvas.drawBitmap(createCircleImage(mSrc,min), 0, 0,null);
		break;
		case TYEP_ROUND :
			canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0,null);
		}
	}

	private Bitmap createRoundConerImage(Bitmap mSrc2) {
		// TODO Auto-generated method stub
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(width,height,Config.ARGB_8888);
		
		Canvas canvas = new Canvas(target);
		RectF rect = new RectF(0, 0, mSrc2.getWidth(), mSrc2.getHeight());
		canvas.drawRoundRect(rect, 50f, 50f, paint);
		canvas.drawBitmap(mSrc2, 0, 0,paint);
		return target;
	}

	private Bitmap createCircleImage(Bitmap mSrc2, int min) {
		// TODO Auto-generated method stub
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(min,min,Config.ARGB_8888);
		
		Canvas canvas = new Canvas(target);
		
		canvas.drawCircle(min/2, min/2, min/2, paint);
		
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		
		canvas.drawBitmap(mSrc2, 0, 0,paint);
		return target;
	}
	
	

	
}
