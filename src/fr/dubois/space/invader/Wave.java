package fr.dubois.space.invader;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Wave
{
	protected Bitmap bitmap;
	protected ArrayList<Alien> ListeAlien;
	
	public Wave(Bitmap bitmap, ArrayList<Alien> listeAlien)
	{
		super();
		this.bitmap = bitmap;
		ListeAlien = listeAlien;
	}
	
	
	
}
