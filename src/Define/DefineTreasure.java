package Define;

import Application.Vector2;

public interface DefineTreasure {

	// ID
	int RED = 0;
	int BLUE = 1;
	int GREEN = 2;
	int WHITE = 3;

	// 種類出現率
	int[] APPEARANCE = {
			RED, BLUE, BLUE, BLUE, BLUE, GREEN, GREEN, WHITE
	};

	// サイズ
	Vector2 SIZE = new Vector2( 50, 50 );

	// リサイズ
	Vector2 RESIZE = new Vector2( 128, 128 );

	// 出現間隔
	int APPEAR_TREASURE = 600;

	// 1区画の最大数
	int MAX_NUM = 5;
}
