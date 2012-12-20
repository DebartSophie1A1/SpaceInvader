package fr.dubois.space.invader;

import android.graphics.Bitmap;



public class Missile extends Sprite{

	protected final float vx=0; //vitesse de deplacement en abscisse
	protected final float vy=5; //vitesse de deplacement en ordonnée
//protected float x,x1; // x1 et y1 son les coordonnée de départ, elles ne sont jamais changé, a mettre en static
//	protected float y,y1;


	public Missile(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	@Override
	public void act()
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		x = x+vx; //augmente de 0 car le missile ne bouge pas en abscisse
		y-= vy; //valeur en ordonnée, diminue pour que le missile monte
	}

	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}

}





