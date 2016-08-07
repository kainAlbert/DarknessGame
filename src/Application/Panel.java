package Application;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Object.Character.CharacterBase;

public class Panel extends JPanel{

	private Application mApp;

	public Panel( Application app ){

		mApp = app;
	}

	public void paintComponent( Graphics g ){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		try{

		}catch( Exception e ){
			return;
		}

		// 背景描画
		drawBack( g2 );


		AffineTransform af = new AffineTransform();

		af.rotate( 0, Define.WINDOW_SIZE.x / 2, Define.WINDOW_SIZE.y / 2 );
		g2.setTransform(af);
	}

	private void draw( Graphics2D g2, CharacterBase c ){

		BufferedImage readImage = null;

		try{

			readImage = c.getImage();
		}catch( Exception e ){
			return;
		}

		if( readImage == null )return;

		AffineTransform af = new AffineTransform();

		af.rotate(c.getAngle() * Math.PI / 180, c.getPos().x + c.getSize().x / 2, c.getPos().y + c.getSize().y / 2);
		g2.setTransform(af);

		int posx = (int)c.getPos().x;
		int posy = (int)c.getPos().y;
		int scalex = (int)c.getSize().x + posx;
		int scaley = (int)c.getSize().y + posy;
		int resizex1 = (int)c.getReSize().x - (int)c.getFirstReSize().x;
		int resizey1 = (int)c.getReSize().y - (int)c.getFirstReSize().y;
		int resizex2 = (int)c.getReSize().x;
		int resizey2 = (int)c.getReSize().y;

		g2.drawImage(
				readImage,
				posx, posy,
				scalex, scaley,
				resizex1, resizey1,
				resizex2, resizey2,
				mApp );
	}

	// 背景描画
	private void drawBack( Graphics g ){

		Graphics2D g2 = (Graphics2D)g;

		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0, 0, (int)Define.WINDOW_SIZE.x, (int)Define.WINDOW_SIZE.y);

	}
}
