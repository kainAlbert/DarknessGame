package Define;

import Application.Vector2;

public interface DefineScene {

	// シーン種類
	int PREPARATION = 0;
	int GAME_PLAY = 1;
	int GAME_END = 2;

	// リサイズ
	Vector2 RESIZE = new Vector2( 256, 256 );

	// プレイヤー画像
	Vector2 PLAYER_SIZE = new Vector2( Define.WINDOW_SIZE.x / 3, Define.WINDOW_SIZE.y / 3 );
	Vector2 PLAYER_POS = new Vector2( Define.WINDOW_SIZE.x / 3 - PLAYER_SIZE.x / 2, 30 );

	// その他プレイヤー画像
	Vector2 OTHER_SIZE = new Vector2( PLAYER_SIZE.x / 2, PLAYER_SIZE.y / 2 );
	double OTHER_DISTANCE = OTHER_SIZE.x;
	Vector2 OTHER_POS = new Vector2( 100, DefineString.CENTER_POS.y + DefineString.SIZE.y + 30 );

	// 終了時次のシーンまでの時間
	int END_TIME = 240;

	// 終了画面の画像
	Vector2 END_RESIZE = new Vector2( 512, 330 );
}
