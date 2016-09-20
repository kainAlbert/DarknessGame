package Define;

import Application.Vector2;

public interface DefinePlayer {

	// サイズ
	Vector2 SIZE = new Vector2( 100, 100 );

	// リサイズ
	Vector2 RESIZE = new Vector2( 256, 256 );

	// 移動速度
//	double SPEED = 5;

	// 旋回速度
//	double ANGLE_SPEED = 10;

	// 可動範囲
	Vector2 LIMIT_LEFT = new Vector2( SIZE.x, SIZE.y );
	Vector2 LIMIT_BOTTOM = new Vector2( DefineMap.MAP_SIZE.x - SIZE.x * 2, DefineMap.MAP_SIZE.y - SIZE.y * 2 );

	// タイマー
	int PREPARATION_TIMER = 99999;
	int SPEED_TIMER = 600;
	int HIDE_TIMER = 1200;
//	int ICON_MAX_TIME = 360;
	int ICON_APPEAR_TIME = 60;

	// 攻撃距離
	double ATTACK_DISTANCE = SIZE.x * 1.5;

	// 攻撃範囲
	double ATTACK_SPHERE = SIZE.x * 4;

	// 死亡タイマー
	int DEAD_TIMER = 120;

	// バーサーカー時間
	int BERSERKER_TIME = 600;

	// 情報送信間隔
	int SEND_INFO_TIME = 3;
}
