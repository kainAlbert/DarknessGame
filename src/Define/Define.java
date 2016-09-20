package Define;

import Application.Rect;
import Application.Vector2;

public interface Define {

	// ウィンドウ関連
	Vector2 WINDOW_REVISION = new Vector2( 8, 34 );
	Vector2 WINDOW_SIZE = new Vector2( 1000, 750 );

	// 可視フィールドサイズ
	Vector2 FIELD_SIZE = new Vector2( 750, 750 );

	// 出現場所
	double APPEAR_DISTANCE = DefinePlayer.SIZE.x * 2;

	Rect[] APPEAR_RECT= {
			new Rect( APPEAR_DISTANCE, APPEAR_DISTANCE, DefineMap.MAP_SIZE.x / 2 - APPEAR_DISTANCE, DefineMap.MAP_SIZE.y / 2 - APPEAR_DISTANCE ),
			new Rect( DefineMap.MAP_SIZE.x / 2, APPEAR_DISTANCE, DefineMap.MAP_SIZE.x - APPEAR_DISTANCE, DefineMap.MAP_SIZE.y / 2 - APPEAR_DISTANCE ),
			new Rect( APPEAR_DISTANCE, DefineMap.MAP_SIZE.y / 2, DefineMap.MAP_SIZE.x / 2 - APPEAR_DISTANCE, DefineMap.MAP_SIZE.y - APPEAR_DISTANCE ),
			new Rect( DefineMap.MAP_SIZE.x / 2, DefineMap.MAP_SIZE.y / 2, DefineMap.MAP_SIZE.x - APPEAR_DISTANCE, DefineMap.MAP_SIZE.y - APPEAR_DISTANCE )
	};
}




