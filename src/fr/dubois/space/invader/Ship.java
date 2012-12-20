package fr.dubois.space.invader;

import android.graphics.Bitmap;

public class Ship extends Sprite
{
	private boolean pair; //a sup
	
	public Ship(Bitmap bitmap, float x, float y)
	{
		super(bitmap, x, y);
		pair=true; // a sup
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() //Juste pour qu'il bouge a changer
	{
		if(x>420) // Si on arrive en bout de ligne à droite on descend de 10 remplacer le 240 par la position maximal que peut obtenir un alien, et faire un > ou =
		{
			pair=false;
		}
		
		if(x<0) // Si on arrive en bout de ligne à gauche on descend de 10
		{
			pair=true;
		}		
		if(pair)
		{
			x+=5;
		}
		else
		{
			x-=5;
		}	
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void MoveLeft()
	{
		x-=10;
	}
	
	public void MoveRight()
	{
		x+=10;
	}
}