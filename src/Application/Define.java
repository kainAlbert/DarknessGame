package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 800, 600 );
	GSvector2 WINDOW_REVISION = new GSvector2( 8, 34 );

	// エフェクト
	GSvector2 IMPACT_EFFECT_RESIZE = new GSvector2( 240, 240 );
	int IMPACT_EFFECT_TIME = 5;

	// フィールド関連
	double FIELD_HAND = 450;
	double FIELD_MYCARD_POSY = 350;
	double FIELD_ENEMYCARD_POSY = 200;
	double[] FIELD_CARD_POSX = { 230, 300, 370, 440, 510 };
	int MAX_FIELD_CARD = 5;

	// カード本体関連
	GSvector2 CARD_SIZE = new GSvector2( 60, 90 );
	GSvector2 CARD_RESIZE = new GSvector2( 128, 128 );
	double CARD_MAX_SPEED = 100.0;
	double CARD_MIN_SPEED = 1.0;
	int MOUSE_ON_TIME = 40;
	double CARD_NUM_SIZE = 15;
	enum MOUSE_CARD_TYPE{
		MOUSEON, MOUSEOFF, SELECT, RELEASE, DRAG
	}

	// カード情報関連
	enum CARD_TYPE{
			NONE, MYHAND, ENEMYHAND, MYFIELD, ENEMYFIELD
	}
}
