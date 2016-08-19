package GetMsg;

import Application.Application;
import Application.Define;
import Application.MesgRecvThread;

public class MsgSelectTactician {

	// 軍師設定
	public static void setTactician( String[] t ){

		if( Application.getObj().getIsStart() ) return;

		if( !Application.getObj().getIsSendTactician() ) return;

		if( !Application.getSelectTactician().getIsSelect() ) return;

		Define.TACTICIAN_ID[] id = {
				Define.TACTICIAN_ID.SONKEN, Define.TACTICIAN_ID.SYOKATURYO, Define.TACTICIAN_ID.SIBAI, Define.TACTICIAN_ID.TOTAKU
		};

		Application.getObj().getCharacterManager().setEnemyTactician( id[ Integer.parseInt(t[2])] );

		// 準備完了を知らせる
		String msg = Application.getID() + Define.MSG + Define.MSG_SET_START;
		MesgRecvThread.outServer( msg );
	}
}
