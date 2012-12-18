package fr.dubois.space.invader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class Sprite {
	
	protected Bitmap bitmap;
	protected float x,x1; // x1 et y1 son les coordonnée de départ, elles ne sont jamais changé, a mettre en static
	protected float y,y1;
	protected Paint paint;

	public Sprite(Bitmap bitmap,float x,float y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.x1 = x;
		this.y1 = y;
		
		paint = new Paint();
		
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, paint);
	}
	
	public RectF getBbox(){
		return new RectF(x,y,x+bitmap.getWidth()-1,y+bitmap.getHeight()-1); 
	}
	
	public abstract void act();

		

}
