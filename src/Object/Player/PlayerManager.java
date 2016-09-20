package Object.Player;

import java.util.ArrayList;

import Application.Application;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.DefineMsg;
import Define.DefinePlayer;

public class PlayerManager {

	private ArrayList<Player> mPlayerList;
	private int mSendTimer;
	private int mIconTimer;

	// コンストラクタ
	public PlayerManager(){

		// リスト生成
		mPlayerList = new ArrayList<Player>();

		// プレイヤー生成
		for( int i=0; i<4; i++ ){

			Player player = new Player();

			mPlayerList.add( player );
		}
	}

	// 初期化
	public void initialize(){

		for( int i=0; i<mPlayerList.size(); i++){

			mPlayerList.get(i).initialize( i + 1 );
		}

		mSendTimer = DefinePlayer.SEND_INFO_TIME;
		mIconTimer = DefinePlayer.ICON_MAX_TIME;
	}

	// 更新
	public void update(){

		for( int i=0; i<mPlayerList.size(); i++){

			mPlayerList.get(i).update();
		}

		// 全員のプレイヤー情報を送信
		sendPlayerInfo();

		mIconTimer--;

		if( mIconTimer <= 0 ) mIconTimer = DefinePlayer.ICON_MAX_TIME;
	}

	// 全員のプレイヤー情報を送信
	private void sendPlayerInfo(){

		if( Application.getID() != 1 ) return;

		mSendTimer--;

		if( mSendTimer  > 0 ) return;

		mSendTimer = DefinePlayer.SEND_INFO_TIME;

		for( int i=0; i<mPlayerList.size(); i++){

			mPlayerList.get(i).sendPlayerInfo( Application.getID() );
		}
	}

	// プレイヤー情報を受信
	public void getPlayerInfo( int id, Vector2 pos, double angle, int preparationTimer, int speedTimer, int hideTimer,
			int berserkerTimer, int attackSize, boolean isBerserker, int point ){

		if( id == Application.getID() ) return;

		Player player = getPlayer( id );

		if( player == null ) return;

		player.getPlayerInfo(pos, angle, preparationTimer, speedTimer, hideTimer, berserkerTimer, attackSize, isBerserker, point);
	}

	// 攻撃判定
	public void collisionAttack( int id, boolean isBerserker ){

		Player player = getPlayer( id );

		if( player == null ) return;

		player.doDead( isBerserker );

		if( Application.getID() != 1 ) return;

		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.COLLISION_ATTACK + DefineMsg.MSG + id + DefineMsg.MSG + isBerserker;
		MesgRecvThread.outServer( msg );
	}


	// 指定したIDのプレイヤーを返す
	public Player getPlayer( int id ){

		for( int i=0; i<mPlayerList.size(); i++){

			if( mPlayerList.get(i).getID() != id ) continue;

			return mPlayerList.get(i);
		}

		return null;
	}

	// ゲッター
	public ArrayList<Player> getPlayerList(){ return mPlayerList; }
	public int getIconTimer(){ return mIconTimer; }
}
