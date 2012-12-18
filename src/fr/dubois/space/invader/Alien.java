package fr.dubois.space.invader;

import android.graphics.Bitmap;

public class Alien extends Sprite
{

	private boolean pair;
	
	public Alien(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		pair=true;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void act()
	{
		// TODO Auto-generated method stub
		if(x>x1+90) // Si on arrive en bout de ligne à droite on descend de 10 remplacer le 240 par la position maximal que peut obtenir un alien, et faire un > ou =
		{
			y+=10;
			pair=false;
		}
		
		if(x<x1) // Si on arrive en bout de ligne à gauche on descend de 10
		{
			y+=10;
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

}
