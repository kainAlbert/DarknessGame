package Define;

import Application.Vector2;

public interface DefineTime {

	// ゲーム時間
	int GAME_TIME = 7200;

	// 時間の大きさ
	Vector2 TIME_SIZE = new Vector2( 50, 50 );

	// 時間のリサイズ
	Vector2 TIME_RESIZE = new Vector2( 64, 64 );

	// 時間の場所
	Vector2 MINUTE_POS = new Vector2( Define.FIELD_SIZE.x / 2 - TIME_SIZE.x * 1.5, TIME_SIZE.y );
	Vector2 SECOND_POS = new Vector2( Define.FIELD_SIZE.x / 2 + TIME_SIZE.x / 2, TIME_SIZE.y );

	// コロンの大きさ
	Vector2 COLOGNE_SIZE = new Vector2( 25, 50 );

	// コロンのリサイズ
	Vector2 COLOGNE_RESIZE = new Vector2( 64, 128 );

	// コロンの場所
	Vector2 COLOGNE_POS = new Vector2( Define.FIELD_SIZE.x / 2 - COLOGNE_SIZE.x / 2, TIME_SIZE.y );
}
