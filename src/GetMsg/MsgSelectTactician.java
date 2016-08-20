package GetMsg;

import Application.Application;
import Application.Define;
import Application.MesgRecvThread;

public class MsgSelectTactician {

	// 軍師設定
	public static void setTactician( String[] t ){

		if( Application.getObj().getIsStart() ) return;

		if( !Application.getSelectTactician().getIsSelect() ) return;

		Define.TACTICIAN_ID[] id = {
				Define.TACTICIAN_ID.SONKEN, Define.TACTICIAN_ID.SYOKATURYO, Define.TACTICIAN_ID.SIBAI, Define.TACTICIAN_ID.TOTAKU
		};

		Application.getObj().getCharacterManager().setEnemyTactician( id[ Integer.parseInt(t[2])] );

		// 送信完了
		Application.getObj().sendTactician();

		// カード管理者初期化
		Application.getObj().getCardManager( false ).initialize();

		// 準備完了を知らせる
		String msg = Application.getID() + Define.MSG + Define.MSG_SET_START;
		MesgRecvThread.outServer( msg );
	}
}
