package GetMsg;

import Application.Application;
import Application.Vector2;

public class GetMsgPlayer {

	// プレイヤー情報
	public static void setPlayerInfo( String[] t ){

		int id = Integer.parseInt(t[2]);
		Vector2 pos = new Vector2( Double.parseDouble(t[3]), Double.parseDouble(t[4]) );
		double angle = Double.parseDouble( t[5] );
		int preparationTimer = Integer.parseInt(t[6]);
		int speedTimer = Integer.parseInt(t[7]);
		int hideTimer = Integer.parseInt(t[8]);
		int berserkerTimer = Integer.parseInt(t[9]);
		int attackSize = Integer.parseInt(t[10]);
		boolean isBerserker = t[11].equals("true");
		int point = Integer.parseInt(t[12]);

		Application.getObj().getPlayerManager().getPlayerInfo(id, pos, angle, preparationTimer, speedTimer, hideTimer, berserkerTimer, attackSize, isBerserker, point);
	}

	// 攻撃エフェクト
	public static void attackEffect( String[] t ){

		Vector2 pos = new Vector2( Double.parseDouble(t[2]), Double.parseDouble(t[3]) );
		int attackSize = Integer.parseInt( t[4] );
		double angle = Double.parseDouble( t[5] );

		Application.getObj().getEffectManager().createAttackEffect(pos, attackSize, angle);
	}

	// 攻撃判定
	public static void collisionAttack( String[] t ){

		int id = Integer.parseInt(t[2]);
		boolean isBerserker = t[3].equals("true");

		Application.getObj().getPlayerManager().collisionAttack(id, isBerserker);
	}

}
