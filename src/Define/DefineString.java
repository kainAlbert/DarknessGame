package Define;

import Application.Vector2;

public interface DefineString {

	// 大きさ
	Vector2 SIZE = new Vector2( Define.FIELD_SIZE.x * 0.6, 200 );

	// リサイズ
	Vector2 RESIZE = new Vector2( 256, 128 );

	// 初期位置
	Vector2 FIRST_POS = new Vector2( Define.FIELD_SIZE.x / 2 - SIZE.x / 2, -SIZE.y );

	// 上の方の位置
	Vector2 UP_POS = new Vector2( Define.FIELD_SIZE.x / 2 - SIZE.x / 2, 100 );

	// 真ん中の位置
	Vector2 CENTER_POS = new Vector2( Define.FIELD_SIZE.x / 2 - SIZE.x / 2, Define.FIELD_SIZE.y / 2 - SIZE.y / 2 );

	// 速度
	double SPEED = 15;

	// 文字種類
	int KILLED_BERSERKER = 0;
	int IS_DEAD = 1;
	int SPEED_UP = 2;
	int HIDE = 3;
	int STRONG_ATTACK = 4;
	int ATTACK = 5;
	int WAIT = 6;
	int MAIN = 7;
	int GAME_START = 8;
	int GAME_END = 9;

	// 各停滞時間
	int STOP_TIME[] = { 90, 90, 90, 90, 90, 90, 99999, 99999, 99999, 99999 };

	// 各最終位置
	Vector2 LAST_POS[] = {
			new Vector2( CENTER_POS.x, CENTER_POS.y ), new Vector2( CENTER_POS.x, CENTER_POS.y ), new Vector2( UP_POS.x, UP_POS.y ), new Vector2( UP_POS.x, UP_POS.y ),
			new Vector2( UP_POS.x, UP_POS.y ), new Vector2( UP_POS.x, UP_POS.y ), new Vector2( CENTER_POS.x, CENTER_POS.y ), new Vector2( CENTER_POS.x, CENTER_POS.y ),
			 new Vector2( CENTER_POS.x, CENTER_POS.y ), new Vector2( CENTER_POS.x, CENTER_POS.y )
	};

}
