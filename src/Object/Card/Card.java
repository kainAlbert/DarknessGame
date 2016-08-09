package Object.Card;

import java.awt.Point;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class Card extends CharacterBase{

	private CharacterBase mExplanation;

	// コンストラクタ
	public Card(){

		mExplanation = new CardExplanation();
	}

	// 初期化
	public void initialize( Application app, String fileName, GSvector2 pos, GSvector2 size, GSvector2 resize,  int id, int type ){

		super.initialize(fileName, pos, size, resize, id, type);

		mExplanation.initialize("bullet", new GSvector2(100,300), new GSvector2( 100, 100 ), new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0);
		mExplanation.initializeLabel(app);
	}

	// 更新
	public void update(){

		moveBack();

		super.update();

		((CardExplanation)mExplanation).update( new GSvector2( mPos.x + mSize.x, mPos.y ), !mIsSelect && mIsMouseOn );
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
		if( Math.abs( mLastPos.x - mPos.x ) < Define.CARD_BACK_SPEED * 1.1 ) mPos.x = mLastPos.x;
		if( Math.abs( mLastPos.y - mPos.y ) < Define.CARD_BACK_SPEED * 1.1 ) mPos.y = mLastPos.y;
	}

	// マウスを離した時
	public void release( Point mousePos ){

		// 選択を解除
		mIsSelect = false;

		// 自分のフィールドのカードの数を取得
		int myFieldNum = Application.getObj().getCardManager().searchTypeNum( Define.CARD_TYPE.MYFIELD );

		// 手を離した場所が手札の位置ではないか
		boolean isPosY = mousePos.y + mPos.y < Define.FIELD_HAND;

		// フィールドのカードの数が5未満か
		boolean isFieldNum =  myFieldNum < Define.MAX_FIELD_CARD;

		// 条件を1つでも満たしてなければ親クラスを呼んで返す
		if( !isPosY || !isFieldNum ){

			super.release( mousePos );
			return;
		}

		// タイプをフィールドに設定
		mType = Define.CARD_TYPE.MYFIELD.ordinal();

		// 位置xを取得
		double posx = Define.FIELD_CARD_POSX[ myFieldNum ];

		// 一旦手札の位置に戻す
		mPos.x = mLastPos.x;
		mPos.y = mLastPos.y;

		// 終点を設定
		mLastPos.x = posx;
		mLastPos.y = Define.FIELD_MYCARD_POSY;
	}
}
