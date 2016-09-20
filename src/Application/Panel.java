package Application;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Define.Define;
import Define.DefinePlayer;
import Define.DefineScene;
import Object.ObjectManager;
import Object.Character.CharacterBase;
import Object.Map.MiniMap;
import Object.Player.Player;
import Object.StringLabel.StringLabel;
import Object.Time.GameTime;
import Object.Treasure.Treasure;
import Object.Wall.Wall;
import Scene.Scene;
import Scene.ScenePlayer;


public class Panel extends JPanel{

	private Application mApp;

	public Panel( Application app ){

		mApp = app;
	}

	public void paintComponent( Graphics g ){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		preparation( g2 );

		playGame( g2 );

		endGame( g2 );
	}

	// ゲーム準備
	private void preparation( Graphics2D g2 ){

		ObjectManager obj = Application.getObj();

		Scene scene = null;
		ScenePlayer[] player = null;
		StringLabel label = null;

		try{

			scene = Application.getScene();
			player = scene.getPlayer();
			label = obj.getStringLabel();
		}catch( Exception e ){
			return;
		}

		if( scene.getScene() != DefineScene.PREPARATION ) return;

		// 背景描画
		drawBack( g2 );

		// プレイヤー画像描画
		for( int i=0; i<player.length; i++ ){

			draw( g2, player[i], new Vector2() );
		}

		// 文字
		draw( g2, label, new Vector2() );
	}

	// ゲームプレイ
	private  void playGame( Graphics2D g2 ){

		ObjectManager obj = Application.getObj();

		Scene scene = null;
		Player player = null;
		ArrayList<Player> playerList = null;
		ArrayList<Wall> wallList = null;
		ArrayList<Treasure> treasureList = null;
		ArrayList<CharacterBase> effectList = null;
		MiniMap miniMap = null;
		CharacterBase dark = null;
		GameTime gameTime = null;
		StringLabel label = null;

		try{

			scene = Application.getScene();
			player =  obj.getPlayerManager().getPlayer( Application.getID() );
			playerList = obj.getPlayerManager().getPlayerList();
			wallList = obj.getWallManager().getWallList();
			treasureList = obj.getTreasureManager().getTreasureList();
			effectList = obj.getEffectManager().getEffectList();
			miniMap = obj.getMap().getMiniMap();
			dark = obj.getMap().getDark();
			gameTime = obj.getGameTime();
			label = obj.getStringLabel();
		}catch( Exception e ){
			return;
		}

		if( scene.getScene() != DefineScene.GAME_PLAY ) return;

		if( player == null ) return;

		// 表示補正
		Vector2 revision = new Vector2( Define.FIELD_SIZE.x / 2 - DefinePlayer.SIZE.x / 2 - player.getPos().x, Define.FIELD_SIZE.y / 2 - DefinePlayer.SIZE.y / 2 - player.getPos().y );

		// 背景描画
		drawBack( g2 );

		// マップ描画
		draw( g2, obj.getMap(), revision );

		// プレイヤー描画
		for( int i=0; i<playerList.size(); i++ ){

			if( playerList.get(i).getID() != Application.getID() && playerList.get(i).getHideTimer() > 0 ) continue;

			draw( g2, playerList.get(i), revision );
		}

		// 壁描画
		for( int i=0; i<wallList.size(); i++ ){

			draw( g2, wallList.get(i), revision );
		}

		// 宝箱描画
		for( int i=0; i<treasureList.size(); i++ ){

			draw( g2, treasureList.get(i), revision );
		}

		// エフェクト描画
		for( int i=0; i<effectList.size(); i++ ){

			draw( g2, effectList.get(i), revision );
		}

		// ミニマップ描画
		draw( g2, miniMap, revision );

		// プレイヤーアイコン描画
		for( int i=0; i<playerList.size(); i++ ){

			if( playerList.get(i).getID() != Application.getID() && playerList.get(i).getHideTimer() > 0 ) continue;

			if( playerList.get(i).getID() != Application.getID() && obj.getPlayerManager().getIconTimer() > DefinePlayer.ICON_APPEAR_TIME ) continue;

			draw( g2, playerList.get(i).getIcon(), revision );
		}

		// 宝箱アイコン描画
		for( int i=0; i<treasureList.size(); i++ ){

			draw( g2, treasureList.get(i).getIcon(), revision );
		}

		// 闇
		draw( g2, dark, revision );

		// ゲーム時間
		draw( g2, gameTime, revision );
		draw( g2, gameTime.getMinute(), revision );
		draw( g2, gameTime.getSecond(), revision );

		// 文字
		draw( g2, label, revision );
	}

	// ゲーム終了
	private void endGame( Graphics2D g2 ){

		ObjectManager obj = Application.getObj();

		Scene scene = null;
		CharacterBase endBack = null;
		List<CharacterBase> effectList = null;

		try{

			scene = Application.getScene();
			endBack = scene.getEndBack();
			effectList = obj.getEffectManager().getEffectList();
		}catch( Exception e ){
			return;
		}

		if( scene.getScene() != DefineScene.GAME_END ) return;

		// 背景描画
		drawBack( g2 );

		// 背景画像描画
		draw( g2, endBack, new Vector2() );

		// エフェクト描画
		for( int i=0; i<effectList.size(); i++ ){

			draw( g2, effectList.get(i), new Vector2() );
		}
	}

	// 画像描画
	private void draw( Graphics2D g2, CharacterBase c, Vector2 revision ){

		BufferedImage readImage = null;

		try{

			readImage = c.getImage();
		}catch( Exception e ){
			return;
		}

		if( readImage == null ) return;

		// 位置修正
		Vector2 revisionPos = c.getIsRevision() ? new Vector2( revision.x, revision.y ) : new Vector2();

		AffineTransform af = new AffineTransform();

		af.rotate(c.getAngle() * Math.PI / 180, c.getPos().x + c.getSize().x / 2 + revisionPos.x, c.getPos().y + c.getSize().y / 2 + revisionPos.y);
		g2.setTransform(af);

		g2.setColor( Color.RED );

		int posx = (int)c.getPos().x + (int)revisionPos.x;
		int posy = (int)c.getPos().y + (int)revisionPos.y;
		int scalex = (int)c.getSize().x + posx;
		int scaley = (int)c.getSize().y + posy;
		int resizex1 = (int)c.getReSize().x - (int)c.getReSizeDistance().x;
		int resizey1 = (int)c.getReSize().y - (int)c.getReSizeDistance().y;
		int resizex2 = (int)c.getReSize().x;
		int resizey2 = (int)c.getReSize().y;

		g2.drawImage(
				readImage,
				posx, posy,
				scalex, scaley,
				resizex1, resizey1,
				resizex2, resizey2,
				mApp );

		af.rotate( 0, Define.WINDOW_SIZE.x / 2, Define.WINDOW_SIZE.y / 2 );
		g2.setTransform(af);
		g2.setColor(null);
	}

	// 背景描画
	private void drawBack( Graphics g ){

		Graphics2D g2 = (Graphics2D)g;

		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0, 0, (int)Define.WINDOW_SIZE.x, (int)Define.WINDOW_SIZE.y);

	}
}
