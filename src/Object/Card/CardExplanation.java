package Object.Card;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class CardExplanation extends CharacterBase{

	private int mTimer;

	// コンストラクタ
	public CardExplanation(){

		super();

		mTimer = Define.CARD_EXPLANATION_TIME;
	}

	// 初期化
	public void initialize( int id, GSvector2 pos, GSvector2 size ){

		super.initialize( "card_explanation",
				new GSvector2( 0, Define.CARD_EXPLANATION_Y ),
				new GSvector2( Define.CARD_EXPLANATION_SIZE.x, Define.CARD_EXPLANATION_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0
				);

		// 位置設定
		mPos.x = pos.x - mSize.x;

		// 画面半分より左にいるなら右に出す
		if( pos.x + size.x / 2 < Define.WINDOW_SIZE.x / 2 ){

			mPos.x = pos.x + size.x;
		}
	}

	// 更新
	public void update(){

		super.update();

		mTimer--;

		if( mTimer <= 0 ) mIsDead = true;
	}
}
