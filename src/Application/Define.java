package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 800, 600 );

	// エフェクト
	GSvector2 IMPACT_EFFECT_RESIZE = new GSvector2( 240, 240 );
	int IMPACT_EFFECT_TIME = 5;

	// フィールド関連
	double FIELD_HAND = 500;
	double FIELD_MYCARD_POSY = 350;
	double FIELD_ENEMYCARD_POSY = 200;
	double[] FIELD_CARD_POSX = { 230, 300, 370, 440, 510 };
	int MAX_FIELD_CARD = 5;

	// カード本体関連
	GSvector2 CARD_SIZE = new GSvector2( 60, 90 );
	GSvector2 CARD_RESIZE = new GSvector2( 128, 128 );
	double CARD_BACK_SPEED = 30.0;
	int MOUSE_ON_TIME = 2;
	enum MOUSE_CARD_TYPE{
		MOUSEON, MOUSEOFF, SELECT, RELEASE, DRAG
	}

	// カード情報関連
	enum CARD_TYPE{
			NONE, MYHAND, ENEMYHAND, MYFIELD, ENEMYFIELD
	}
}
