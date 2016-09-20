package GetMsg;

import Application.Application;

public class GetMsgScene {

	// エントリー
	public static void entry( String[] t ){

		Application.getScene().doEntry( Integer.parseInt( t[2] ) );
	}

	// 次のシーンへ
	public static  void nextScene(){

		Application.getScene().nextScene();
	}
}
