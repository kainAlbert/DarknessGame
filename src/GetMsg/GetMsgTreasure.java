package GetMsg;

import Application.Application;
import Application.Vector2;

public class GetMsgTreasure {

	// 宝箱作成
	public static void createTreasure( String[] t ){

		int id = Integer.parseInt( t[2] );
		int treasureID = Integer.parseInt( t[3] );
		int factoryID = Integer.parseInt( t[4] );
		Vector2 pos = new Vector2( Double.parseDouble( t[5] ), Double.parseDouble( t[6] ) );

		Application.getObj().getTreasureManager().addTreasure(id, treasureID, factoryID, pos);
	}

	// 宝箱削除
	public static void removeTreasure( String[] t ){

		int treasureID = Integer.parseInt( t[2] );

		Application.getObj().getTreasureManager().removeTreasure(treasureID);
	}
}
