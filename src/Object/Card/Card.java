package Object.Card;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class Card extends CharacterBase{

	private CharacterBase mExplanation;

	// コンストラクタ
	public Card(){

		mExplanation = new CharacterBase();
	}

	// 初期化
	public void initialize( Application app, String fileName, GSvector2 pos, GSvector2 size, GSvector2 resize,  int id, int type ){

		super.initialize(fileName, pos, size, resize, id, type);

		mExplanation.initialize("guardDistance", new GSvector2(1,1), new GSvector2( 1, 1 ), new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0);
		mExplanation.initializeLabel(app);
	}

	// 更新
	public void update(){

		moveBack();

		super.update();

		mExplanation.update();
	}

	// 元の場所に戻る
	private void moveBack(){

		// 選択されている時は解除
		if( mIsSelect ) return;

		// 終点への移動量
		GSvector2 velocity = Direction.getToVelocity( mPos, mLastPos );

		// 移動
		mPos.x += velocity.x * Define.CARD_BACK_SPEED;
		mPos.y += velocity.y * Define.CARD_BACK_SPEED;

		// 近い位置まできたら修正
		if( Math.abs( mLastPos.x - mPos.x ) < Define.CARD_BACK_SPEED * 1.5 ) mPos.x = mLastPos.x;
		if( Math.abs( mLastPos.y - mPos.y ) < Define.CARD_BACK_SPEED * 1.5 ) mPos.y = mLastPos.y;
	}
}
