package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 1000, 750 );
	GSvector2 WINDOW_REVISION = new GSvector2( 8, 34 );

	// エフェクト
	GSvector2 IMPACT_EFFECT_RESIZE = new GSvector2( 240, 240 );
	int IMPACT_EFFECT_TIME = 5;

	// カード本体関連
	GSvector2 CARD_SIZE = new GSvector2( 80, 120 );
	GSvector2 CARD_RESIZE = new GSvector2( 400, 600 );
	double CARD_MAX_SPEED = 100.0;
	double CARD_MIN_SPEED = 1.0;
	int MOUSE_ON_TIME = 40;
	double CARD_NUM_SIZE = 15;
	GSvector2 MYDECK_POS = new GSvector2( WINDOW_SIZE.x - CARD_SIZE.x - 30, WINDOW_SIZE.y / 2 + 10 );
	GSvector2 ENEMYDECK_POS = new GSvector2( WINDOW_SIZE.x - CARD_SIZE.x - 30, WINDOW_SIZE.y / 2 - 10 - CARD_SIZE.y );
	enum MOUSE_CARD_TYPE{
		MOUSEON, MOUSEOFF, SELECT, RELEASE, DRAG
	}

	// フィールド関連
	double FIELD_MYHAND = WINDOW_SIZE.y - CARD_SIZE.y;
	double FIELD_ENEMYHAND = CARD_SIZE.y;
	double FIELD_MYCARD_POSY = FIELD_MYHAND - CARD_SIZE.y - 100;
	double FIELD_ENEMYCARD_POSY = CARD_SIZE.y + 100;
	double[] FIELD_CARD_POSX = { 280 ,370 ,460 ,550 ,640 };
	int MAX_FIELD_CARD = 5;

	// カード情報関連
	GSvector2 CARD_EXPLANATION_SIZE = new GSvector2( 360, 480 );
	double CARD_EXPLANATION_RIGHT_X = WINDOW_SIZE.x - CARD_EXPLANATION_SIZE.x;
	double CARD_EXPLANATION_LEFT_X = 0;
	double CARD_EXPLANATION_Y = ( WINDOW_SIZE.y - CARD_EXPLANATION_SIZE.y ) / 2;
	int MAX_HAND_CARD = 10;
	enum CARD_TYPE{
			NONE, MYHAND, ENEMYHAND, MYFIELD, ENEMYFIELD
	}

}
