package GetMsg;

import Application.Application;
import Application.Define;

public class GetMsg {

	public static void getMsg( String msg ){

		//入力データ分類
		String[] t = msg.split( Define.MSG );

		// コマンドが1つだけならIDとみなし、格納する
		if( Application.getID() == 0 && t.length == 1 ){

			int id = Integer.parseInt( t[0] ) % 2;

			System.out.println(id + 1);

			Application.setID( id + 1 );
			return;
		}

		// IDが自分と同じなら終了
		if( Integer.parseInt(t[0]) == Application.getID() ) return;

		// 開始ターン設定
		if( t[1].equals( Define.MSG_START_TURN ) ){ MsgTurn.setStartTurn(t); }

		// 軍師設定
		if( t[1].equals( Define.MSG_SET_TACTICIAN ) ){ MsgSelectTactician.setTactician(t); }

		// ゲーム開始
		if( t[1].equals( Define.MSG_SET_START ) ){ MsgObjectManager.start(); }

		// カードを引く
		if( t[1].equals( Define.MSG_DRAW_CARD ) ){ MsgDeckCard.drawCard( t ); }
	}
}
