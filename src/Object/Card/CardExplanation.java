package Object.Card;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class CardExplanation extends CharacterBase{

	private int mTimer;
	final int FIRST_TIME = 2;

	// コンストラクタ
	public CardExplanation(){

		super();

		mTimer = FIRST_TIME;
	}

	// 初期化
	public void initialize(){

		super.initialize( "card_explanation",
				new GSvector2( 0, Define.CARD_EXPLANATION_Y ),
				new GSvector2( Define.CARD_EXPLANATION_SIZE.x, Define.CARD_EXPLANATION_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0
				);

		super.initializeLabel();
	}

	// 更新
	public void update(){

		super.update();

		mTimer--;

		if( mTimer <= 0 ) mIsDead = true;
	}

	// ID設定
	public void setID( int id, GSvector2 pos, GSvector2 size ){

		// タイマー設定
		mTimer = FIRST_TIME;

		// 位置設定
		mPos.x = pos.x - mSize.x;// Define.CARD_EXPLANATION_LEFT_X;
		mPos.y = Define.CARD_EXPLANATION_Y;

		// 画面半分より左にいるなら右に出す
		if( pos.x + size.x / 2 < Define.WINDOW_SIZE.x / 2 ){

			mPos.x = pos.x + size.x;// Define.CARD_EXPLANATION_RIGHT_X;
		}
		
		
	}
}
