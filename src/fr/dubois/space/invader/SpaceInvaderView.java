package fr.dubois.space.invader;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SpaceInvaderView extends View
{
	
	// Dimensions souhait√©es
	private static final int TARGET_HEIGHT = 800;
	private static final int TARGET_WIDTH = 600;

	private Paint paint; // Style pour le texte	
	private String text; // texte a† afficher


	private Bitmap missile2bitmap;
	 
	private Bitmap alienbitmap;
	private Alien alien;
	
	private ArrayList<Missile> ListeMissile;
	private ArrayList<Alien> ListeAlien;
	
	// Alien tabAlien[]= new Alien[16]; // Creer un tableau d'alien
//	Missile tabMissile[] = new Missile[16]; // Creer un tableau de missile
	Matrix transform;

	private Bitmap shipbitmap;
	private Ship ship;


	public SpaceInvaderView(Context context) {
		super(context);
		init();
	}

	public SpaceInvaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SpaceInvaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ListeMissile =new ArrayList<Missile>(10);
		ListeAlien = new ArrayList<Alien>(10);
		init();
	}

	 public Bitmap loadImage(int id) {
	
		 Drawable tmp =  this.getContext().getResources().getDrawable(id);
		int x = tmp.getIntrinsicWidth();
		int y = tmp.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tmp.setBounds(0, 0, x, y);
        tmp.draw(canvas);

        
        return bitmap;
    }
	 
  
	 public void Matrix () {
		
	}
	 
	  private RefreshHandler mRedrawHandler = new RefreshHandler();

	  class RefreshHandler extends Handler
	  {

		   @Override
		   public void handleMessage(Message msg)
		   {
		       SpaceInvaderView.this.update();
		       SpaceInvaderView.this.invalidate();
		   }
	
		   public void sleep(long delayMillis)
		   {
			   this.removeMessages(0);
		       sendMessageDelayed(obtainMessage(0), delayMillis);   
		   }
	   
	   };
	    
	       
	void init(){
		
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		paint.setTypeface(Typeface.SANS_SERIF);
		paint.setTextSize(36);
		paint.setTextAlign(Paint.Align.CENTER);
		text = "Game Over LOOOSER !!?";
		
		
		alienbitmap = loadImage(R.drawable.alien1);
		
		for(int x=50;x<=300;x+=80) // position dans la ligne
		{
			for(int y=50;y<=300;y+=80) // position dans la colonne
			{
				ListeAlien.add(new Alien (alienbitmap,x,y));
			}
		}
		
	/* tabMissile[j] = new Missile (missile2bitmap, j, j, j, j);
		 for(int x=50;x<=300;x+=80) // position dans la ligne
			{
				for(int y=50;y<=300;y+=80) // position dans la colonne
				{
					tabMissile[j] = new Missile (missile2bitmap,x,y, y, y);
					j++; // position dans le tableau
				}
			}
		*/
		// alien = new Alien(alienbitmap,80,80); // creer un seul alien
		
		shipbitmap =loadImage(R.drawable.ship);
		ship = new Ship (shipbitmap,220,615);
		
		missile2bitmap = loadImage(R.drawable.missile2);
		
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()-150,ship.getY()));
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()-100,ship.getY()));
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()-50,ship.getY()));
		ListeMissile.add(new Missile (missile2bitmap,ship.getX(),ship.getY()));// a appeller dans update lorsqu'il y a pression sur une touche
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()+50,ship.getY()));
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()+100,ship.getY()));
		ListeMissile.add(new Missile (missile2bitmap,ship.getX()+150,ship.getY()));
		this.update();
	}


	public void update()
	{
		// TODO Auto-generated method stub
		//ship.act();
		mRedrawHandler.sleep(40); // Attend une certaine durÈe
		// alien.act(); // Pour un seul alien
		
		for(Alien a: ListeAlien)
		{
			a.act();
		}
		
		boolean b=false;
		ship.act();
		
		Iterator<Missile> it1;
		it1=ListeMissile.iterator();
		Iterator<Alien> it2;
		it2=ListeAlien.iterator();
		while(it1.hasNext())
		{
			b=true;
			Missile m=it1.next();
			m.act();//deplace le missile
			if(m.getY()<0) 
			{
				it1.remove(); //supprime le missile
			}
			while(it2.hasNext() && b)
			{
				Alien a=it2.next();
				if(m.getY()<a.getY()+50 && m.getX()<a.getX()+35 && m.getX()>a.getX()-35)
				{
					it1.remove(); //supprime le missile si il croise un alien
					it2.remove(); //supprime l'alien
					b=false;
				}
			}	
		}	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
		
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawRGB(0, 0, 0);
		canvas.drawRect(0, 0, TARGET_WIDTH-1, TARGET_HEIGHT-1, paint);
		
		for(Missile m: ListeMissile)
		{
			m.draw(canvas);
		}
		
		for(Alien a: ListeAlien)
		{
			a.draw(canvas);
		}
		
		/*for(int j=0;j<tabMissile.length;j++)
		{
			tabMissile[j].draw(canvas);
		}
		// alien.draw(canvas); // Affichage d'un seul alien*/
		
		ship.draw(canvas);
		
		/* Affichage du texte qui sert a rien.... Remplacer par Ready ! 3 2 1;
		if (text != null){
			canvas.drawText(Game Over, canvas.getWidth()/2,canvas.getHeight()/2, paint);
		}
		*/
		for(Alien a: ListeAlien)
		{
			if(a.getY()>600)
			{
				if (text != null)
				{
					canvas.drawText(text, canvas.getWidth()/2,canvas.getHeight()/2, paint);
				}
			}
		}	
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
	}

	private int computeSize(int spec,int def){
		int mode = View.MeasureSpec.getMode(spec);
		if (mode == View.MeasureSpec.UNSPECIFIED) return def;
		int size = View.MeasureSpec.getSize(spec);
		if (mode == View.MeasureSpec.EXACTLY) {
			return size;
		}
		//		MeasureSpec.AT_MOST
		if (size < def ) return size;
		return def;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
		int x = computeSize(widthMeasureSpec,TARGET_WIDTH);
		int y = computeSize(heightMeasureSpec,TARGET_HEIGHT);
		this.setMeasuredDimension(x,y);
	}

}
