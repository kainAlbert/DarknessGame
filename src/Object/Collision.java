package Object;

import java.util.ArrayList;

import Application.Application;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.DefineMsg;
import Object.Player.Player;
import Object.Wall.Wall;

public class Collision {

	// 更新
	public void update(){

	}

	// プレイヤーの攻撃の当たり判定
	public static int collisionPlayerAttack( int id, Vector2 pos, double size, boolean isBerserker ){

		ArrayList<Player> list = Application.getObj().getPlayerManager().getPlayerList();

		int count = 0;

		for( int i=0; i<list.size(); i++ ){

			if( list.get(i).getID() == id || list.get(i).getDeadTimer() > 0 || list.get(i).getIsBerserker() ) continue;

			// 円と円の当たり判定
			if( !isCollisionCircle( pos, size / 2, list.get(i).getPos(), list.get(i).getSize().x / 2 ) ) continue;

			list.get(i).doDead( isBerserker );

			// 攻撃判定を送信
			String msg = Application.getID() + DefineMsg.MSG + DefineMsg.COLLISION_ATTACK + DefineMsg.MSG + list.get(i).getID() + DefineMsg.MSG + isBerserker;
			MesgRecvThread.outServer( msg );

			count++;
		}

		return count;
	}

	// 壁との当たり判定
	public static Wall collisionWall( Vector2 pos, Vector2 size ){

		ArrayList<Wall> list = Application.getObj().getWallManager().getWallList();

		for( int i=0; i<list.size(); i++ ){

			if( !isCollisionSquare( pos, size, list.get(i).getPos(), list.get(i).getSize() ) ) continue;

			return list.get(i);
		}

		return null;
	}

	// 正方形同士の判定
	public static boolean isCollisionSquare( Vector2 pos1, Vector2 scale1, Vector2 pos2, Vector2 scale2 ){

		boolean cx =
				( pos1.x <= pos2.x && pos1.x + scale1.x >= pos2.x ) ||
				( pos1.x <= pos2.x + scale2.x && pos1.x + scale1.x >= pos2.x + scale2.x );

		boolean cy =
				( pos1.y <= pos2.y && pos1.y + scale1.y >= pos2.y ) ||
				( pos1.y <= pos2.y + scale2.y && pos1.y + scale1.y >= pos2.y + scale2.y );

		boolean cx2 =
				( pos1.x <= pos2.x && pos1.x + scale1.x >= pos2.x + scale2.x ) ||
				( pos2.x <= pos1.x && pos2.x + scale2.x >= pos1.x + scale1.x );

		boolean cy2 =
				( pos1.y <= pos2.y && pos1.y + scale1.y >= pos2.y + scale2.y ) ||
				( pos2.y <= pos1.y && pos2.y + scale2.y >= pos1.y + scale1.y );

		return ( cx && cy ) || ( cx && cy2 ) || ( cy && cx2 );
	}

	// 点と円の判定
	public static boolean isCollisionPointCircle( Vector2 pos1, Vector2 pos2, double radius ){

		return Math.pow( pos1.x - pos2.x, 2 ) + Math.pow( pos1.y - pos2.y, 2 ) < Math.pow( radius, 2 );
	}

	// 正方形と円の判定
	public static boolean isCollisionSquareCircle( Vector2 pos1, Vector2 scale1, Vector2 pos2, double radius2 ){

		Vector2 pos2_r = new Vector2( pos2.x + radius2, pos2.y + radius2 );

		Vector2[] pos = {
				new Vector2( pos1.x, pos1.y ), new Vector2( pos1.x + scale1.x, pos1.y ),
				new Vector2( pos1.x, pos1.y + scale1.y ), new Vector2( pos1.x + scale1.x, pos1.y + scale1.y )
		};

		for( int i=0; i<4; i++ ){

			if( isCollisionPointCircle( pos[i], pos2_r, radius2 ) ) return true;
		}

		if( pos1.x < pos2_r.x - radius2 &&
				pos1.x + scale1.x > pos2_r.x + radius2 &&
				pos1.y < pos2_r.y - radius2 &&
				pos1.y + scale1.y > pos2_r.y + radius2 ) return true;

		return false;
	}

	// 円と円の判定
	public static boolean isCollisionCircle( Vector2 pos1, double radius1, Vector2 pos2, double radius2 ){

		return Math.pow( pos1.x - pos2.x, 2 ) + Math.pow( pos1.y - pos2.y, 2 ) < Math.pow( radius1 + radius2, 2 );
	}

	// 四角と点の判定
	public static boolean isCollisionSquareDot( Vector2 pos1, Vector2 size1, Vector2 pos2 ){

		if( pos1 == null || size1 == null || pos2 == null ) return false;

		return pos1.x <= pos2.x && pos1.x + size1.x  >= pos2.x &&
				   pos1.y <= pos2.y && pos1.y + size1.y  >= pos2.y;
	}
}
