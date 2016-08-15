package Application;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import Object.Card.Card;
import Object.Character.CharacterBase;
import Object.Character.StringLabel;
import Object.Character.Tactician;
import Object.Detail.DetailBase;

public class Panel extends JPanel{

	private Application mApp;

	public Panel( Application app ){

		mApp = app;
	}

	public void paintComponent( Graphics g ){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		List<CharacterBase> myCardList = null;
		List<CharacterBase> enemyCardList = null;
		List<CharacterBase> effectList = null;
		CharacterBase myTactician = null;
		CharacterBase enemyTactician = null;
		CharacterBase pointer = null;
		CharacterBase cardExplanation = null;

		try{

			myCardList = Application.getObj().getCardManager(true).getCardList();
			enemyCardList = Application.getObj().getCardManager(false).getCardList();
			effectList = Application.getObj().getEffectManager().getEffectList();
			myTactician = Application.getObj().getCharacterManager().getTactician(true);
			enemyTactician = Application.getObj().getCharacterManager().getTactician(false);
			pointer = Application.getObj().getEffectManager().getPointer();
			cardExplanation = Application.getObj().getCardManager(true).getExplanation();
		}catch( Exception e ){
			return;
		}

		// 背景描画
		drawBack( g2 );

		// 軍師描画
		draw( g2, myTactician );
		draw( g2, ((Tactician)myTactician).getHPLabel() );
		draw( g2, ((Tactician)myTactician).getManaLabel() );

		draw( g2, enemyTactician );
		draw( g2, ((Tactician)enemyTactician).getHPLabel() );
		draw( g2, ((Tactician)enemyTactician).getManaLabel() );

		// 各カード描画
		for( int i=0; i<myCardList.size(); i++ ){

			CharacterBase card = myCardList.get(i);
			DetailBase detail = ((Card)card).getDetail();

			draw( g2, detail );

			draw( g2, card );

			// 数値描画
			draw( g2, detail.getCostLabel() );
			draw( g2, detail.getAttackLabel() );
			draw( g2, detail.getHPLabel() );
		}
		for( int i=0; i<enemyCardList.size(); i++ ){

			CharacterBase card = enemyCardList.get(i);
			DetailBase detail = ((Card)card).getDetail();

			draw( g2, detail );

			draw( g2, card );

			// 数値描画
			draw( g2, detail.getCostLabel() );
			draw( g2, detail.getAttackLabel() );
			draw( g2, detail.getHPLabel() );
		}

		// エフェクト描画
		for( int i=0; i<effectList.size(); i++ ){

			draw( g2, effectList.get(i) );
		}

		// ポインター描画
		draw( g2, pointer );

		// カード説明描画
		if( cardExplanation != null ) draw( g2, cardExplanation );

		AffineTransform af = new AffineTransform();

		af.rotate( 0, Define.WINDOW_SIZE.x / 2, Define.WINDOW_SIZE.y / 2 );
		g2.setTransform(af);
	}

	// 画像描画
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

		// ダメージ時の振動
		int revision = 0;

		if( c.getDamageTimer() > 0 ){

			revision = (int)( Math.random() * 10 - 5 );
		}

		int posx = (int)c.getPos().x + revision;
		int posy = (int)c.getPos().y + revision;
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

	// 文字描画
	private void drawStr( Graphics g2, StringLabel s ){

		if( s == null ) return;

		if( s.getStr().equals("") ) return;

		BufferedImage readImage = null;

		try{

			readImage = s.getImage();
		}catch( Exception e ){
			return;
		}

		if( readImage == null )return;

		int posx = (int)s.getImagePos().x;
		int posy = (int)s.getImagePos().y;
		int scalex = (int)s.getImageSize().x + posx;
		int scaley = (int)s.getImageSize().y + posy;
		int resizex2 = (int)s.getImageReSize().x;
		int resizey2 = (int)s.getImageReSize().y;

		g2.drawImage(
				readImage,
				posx, posy,
				scalex, scaley,
				0, 0,
				resizex2, resizey2,
				mApp );

		g2.setFont( new Font( "Meiryo", Font.BOLD, s.getStrSize() ));

		g2.drawString( s.getStr(), (int)s.getStrPos().x, (int)s.getStrPos().y );
	}

	// 背景描画
	private void drawBack( Graphics g ){

		Graphics2D g2 = (Graphics2D)g;

		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0, 0, (int)Define.WINDOW_SIZE.x, (int)Define.WINDOW_SIZE.y);

	}
}
