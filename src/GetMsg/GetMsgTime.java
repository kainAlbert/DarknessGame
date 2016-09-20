package GetMsg;

import Application.Application;

public class GetMsgTime {

	// 時間設定
	public static void setGameTime( String[] t ){

		Application.getObj().getGameTime().setGameTimer( Integer.parseInt( t[2] ) );
	}
}
