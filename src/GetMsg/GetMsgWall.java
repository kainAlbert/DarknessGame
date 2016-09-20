package GetMsg;

import Application.Application;
import Application.Vector2;

public class GetMsgWall {

	// 壁の移動
	public static void moveWall( String[] t ){

		int id = Integer.parseInt( t[2] );
		int wallID = Integer.parseInt( t[3] );
		Vector2 pos = new Vector2( Double.parseDouble( t[4] ), Double.parseDouble( t[5] ) );

		Application.getObj().getWallManager().moveWall(id, wallID, pos);
	}
}
