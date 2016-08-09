package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 800, 600 );

	// エフェクト
	GSvector2 IMPACT_EFFECT_RESIZE = new GSvector2( 240, 240 );
	int IMPACT_EFFECT_TIME = 5;

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
