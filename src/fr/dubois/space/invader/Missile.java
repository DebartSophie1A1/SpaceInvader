package fr.dubois.space.invader;

import android.graphics.Bitmap;


public class Missile extends Sprite{
	protected float vx;
	protected float vy;
	protected float x,x1; // x1 et y1 son les coordonnée de départ, elles ne sont jamais changé, a mettre en static
	protected float y,y1;

	public Missile(Bitmap bitmap, float x, float y, float vy, float vx) {
		super(bitmap, x, y);
		// TODO Auto-generated constructor stub
		this.vx = vx;
		this.vy = vy;
		this.x1 = x;
		this.y1 = y;
		this.x = x;
		this.y = y;
		
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

		y=y+1;
	}

}