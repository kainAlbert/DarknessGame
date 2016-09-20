package Define;

import Application.Vector2;

public interface DefineEffect {

	// 爆発
	Vector2 IMPACT_RESIZE = new Vector2( 240, 240 );
	int IMPACT_ANIM_TIMER = 5;
	int IMPACT_IMAGE_NUM = 7;

	// サイドメニュー
	Vector2 SIDE_RESIZE = new Vector2( 250, 750 );

	// 攻撃準備
	Vector2 PREPARATION_RESIZE = new Vector2( 120, 120 );
	Vector2 PREPARATION_SIZE = new Vector2( DefinePlayer.SIZE.x * 1.5, DefinePlayer.SIZE.y * 1.5 );
	int PREPARATION_ANIM_TIMER = 5;
	int PREPARATION_IMAGE_NUM = 10;

	// 速度アップ
	Vector2 SPEED_RESIZE = new Vector2( 120, 120 );
	Vector2 SPEED_SIZE = new Vector2( DefinePlayer.SIZE.x * 1.3, DefinePlayer.SIZE.y * 1.3 );
	int SPEED_ANIM_TIMER = 5;
	int SPEED_IMAGE_NUM = 10;

	// 隠身
	Vector2 HIDE_RESIZE = new Vector2( 120, 120 );
	Vector2 HIDE_SIZE = new Vector2( DefinePlayer.SIZE.x * 1.5, DefinePlayer.SIZE.y * 1.5 );
	int HIDE_ANIM_TIMER = 8;
	int HIDE_IMAGE_NUM = 10;

	// 攻撃
	Vector2 ATTACK_RESIZE = new Vector2( 320, 240 );
	Vector2 ATTACK_SIZE = new Vector2( DefinePlayer.SIZE.x * 4, DefinePlayer.SIZE.y * 6 );
	int ATTACK_ANIM_TIMER = 2;
	int ATTACK_IMAGE_NUM = 27;

	// 鎌
	Vector2 FALX_RESIZE = new Vector2( 240, 240 );
	Vector2 FALX_SIZE = new Vector2( DefinePlayer.SIZE.x * 4, DefinePlayer.SIZE.y * 4 );
	int FALX_ANIM_TIMER = 10;
	int FALX_IMAGE_NUM = 5;

	// ポイント
	Vector2 POINT_RESIZE = new Vector2( 64, 64 );
	Vector2 POINT_SIZE = new Vector2( 40, 40 );
	Vector2 POINT_POS = new Vector2( DefineMap.MINI_MAP_POS.x + 80, 425 );
	double POINT_DISTANCE = 70;
	Vector2 POINT_END_POS = new Vector2( 190, 450 );
	double POINT_END_DISTANCE = 181;
}
