package Define;

import Application.Vector2;

public interface DefineMap {

	// マップ
	Vector2 MAP_SIZE = new Vector2( 5000, 5000 );
	Vector2 MAP_RESIZE = new Vector2( 800, 600 );
	Vector2 MAP_REVISION = new Vector2( ( Define.FIELD_SIZE.x + MAP_SIZE.x ) / MAP_RESIZE.x, ( Define.FIELD_SIZE.y + MAP_SIZE.y ) / MAP_RESIZE.y );

	// ミニマップ
	Vector2 MINI_MAP_RESIZE = new Vector2( 256, 256 );
	Vector2 MINI_MAP_SIZE = new Vector2( Define.WINDOW_SIZE.x - Define.FIELD_SIZE.x - 20, Define.WINDOW_SIZE.x - Define.FIELD_SIZE.x - 20 );
	Vector2 MINI_MAP_POS = new Vector2( Define.FIELD_SIZE.x + 10, 30 );
	double MINI_REVISION = MAP_SIZE.x / MINI_MAP_SIZE.x;

	// プレイヤーアイコン
	Vector2 PLAYER_ICON_SIZE = new Vector2( 10, 10 );
	Vector2 PLAYER_ICON_RESIZE = new Vector2( 64, 64 );

	// 宝箱アイコン
	Vector2 TREASURE_ICON_SIZE = new Vector2( 12, 12 );
	Vector2 TREASURE_ICON_RESIZE = new Vector2( 128, 128 );

	// 闇
	Vector2 DARK_RESIZE = new Vector2( 512, 512 );
}
