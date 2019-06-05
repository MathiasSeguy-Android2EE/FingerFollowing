package com.android2ee.formation.ludequip.draganddrop;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView imageView;
	ImageView imB1, imB2, imB3;
	int imX, imY;
	int eventX, eventY;
	int w, h;
	FrameLayout frame;
	boolean onMove = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//ce sont les valeurs dans le fichier de layout
		imX = 72;
		imY = 75;
		//instanciation des composants
		imageView = (ImageView) findViewById(R.id.imageview);
		imB1 = (ImageView) findViewById(R.id.imvB1);
		imB2 = (ImageView) findViewById(R.id.imvB2);
		imB3 = (ImageView) findViewById(R.id.imvB3);
		w = imageView.getWidth();
		h = imageView.getHeight();
		frame = (FrameLayout) findViewById(R.id.framelayout);
		imageView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Log.e("ludequip.draganddrop", "X :" + (int)event.getX() + ", y" +
				// (int)event.getY());
				moveButton((int) event.getX(), (int) event.getY());
				return true;
			}
		});
	}

	/**
	 * La méthode qui bouge le imageView en fonction du MotionEvent
	 * @param x
	 * @param y
	 */
	private void moveButton(int x, int y) {
		if (h == 0) {
			h = imageView.getHeight();
			w = imageView.getWidth();
		}
		//le motion event renvoie le mouvement pas la position absolue
		//donc c'est le delta a appliquer sur la position de l'element
		imX = imX + x - 72;
		imY = imY + y - 72;
		AbsoluteLayout.LayoutParams OBJ = new AbsoluteLayout.LayoutParams(w, h, imX, imY);
		Rect rec = new Rect();
		if (imB1 != null) {
			imB1.getDrawingRect(rec);
			if (rec != null) {
				if (rec.contains(imX, imY)) {
					imB1.setBackgroundResource(R.drawable.ic_image2);
				} else {
					imB1.setBackgroundResource(R.drawable.ic_image);
				}
			}
		}
		imageView.setLayoutParams(OBJ);
		// manageDropLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
