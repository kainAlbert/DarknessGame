package GetMsg;

import Application.Application;
import Define.DefineMsg;

public class GetMsg {

	public static void getMsg( String msg ){

		//入力データ分類
		String[] t = msg.split( DefineMsg.MSG );

		// コマンドが1つだけならIDとみなし、格納する
		if( Application.getID() == 0 && t.length == 1 ){

			int id = ( Integer.parseInt( t[0] ) - 1 ) % 4 + 1;

			System.out.println( id );

			Application.setID( id );
			return;
		}

		int id = Application.getID();

		// IDが1以外なら、1の信号のみ受信。1なら自分以外の信号のみ受信。
		if( !( id != 1 && Integer.parseInt(t[0]) == 1 ) && !( id == 1 && Integer.parseInt(t[0]) != 1 ) ) return;

		System.out.println( msg );

		// エントリー
		if( t[1].equals( DefineMsg.ENTRY ) ) GetMsgScene.entry( t );

		// 次のシーンへ
		if( t[1].equals( DefineMsg.NEXT_SCENE ) ) GetMsgScene.nextScene();

		// プレイヤー情報
		if( t[1].equals( DefineMsg.PLAYER_INFO ) ) GetMsgPlayer.setPlayerInfo( t );

		// 攻撃エフェクト
		if( t[1].equals( DefineMsg.ATTACK_EFFECT ) ) GetMsgPlayer.attackEffect( t );

		// 攻撃判定
		if( t[1].equals( DefineMsg.COLLISION_ATTACK ) ) GetMsgPlayer.collisionAttack( t );

		// 壁の移動
		if( t[1].equals( DefineMsg.MOVE_WALL ) ) GetMsgWall.moveWall( t );

		// アイテム作成
		if( t[1].equals( DefineMsg.CREATE_TREASURE ) ) GetMsgTreasure.createTreasure( t );

		// アイテム削除
		if( t[1].equals( DefineMsg.REMOVE_TREASURE ) ) GetMsgTreasure.removeTreasure( t );

		// 時間設定
		if( t[1].equals( DefineMsg.GAME_TIME ) ) GetMsgTime.setGameTime( t );

	}
}
