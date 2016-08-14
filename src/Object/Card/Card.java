package Object.Card;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class Card extends CharacterBase{

	protected DetailBase mDetail;
	protected int mFieldNumber;
	protected boolean mIsMy;

	// コンストラクタ
	public Card( boolean isMy ){

		mDetail = new DetailBase( isMy );

		mFieldNumber = 0;
		mIsMy = isMy;
	}

	// 初期化
	public void initialize(){

		int cardID = Application.getObj().getCardManager( mIsMy ).getCardID();

		super.initialize( "card_image",
				new GSvector2(),
				new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
				cardID, Define.CARD_TYPE.NONE.ordinal() );
	}

	// 詳細初期化
	public void initializeDetail( int cardID ){

		mDetail.initialize( cardID, new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ), mType );
	}

	// 更新
	public void update(){

		moveBack();

		super.update();

		mDetail.update( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
	}

	// 元の場所に戻る
	protected void moveBack(){

		// 選択されている時は解除
		if( mIsSelect ) return;

		// 終点への移動量
		GSvector2 velocity = Direction.getToVelocity( mPos, mLastPos );

		// 速度
		double distance = Direction.getDistance( mPos, mLastPos );

		mSpeed = Math.min( Math.max( distance / ( Define.WINDOW_SIZE.x / Define.CARD_MAX_SPEED ), Define.CARD_MIN_SPEED ), Define.CARD_MAX_SPEED );

		// 移動
		mPos.x += velocity.x * mSpeed;
		mPos.y += velocity.y * mSpeed;

		// 近い位置まできたら修正
		if( Math.abs( mLastPos.x - mPos.x ) < mSpeed * 1.1 ) mPos.x = mLastPos.x;
		if( Math.abs( mLastPos.y - mPos.y ) < mSpeed * 1.1 ) mPos.y = mLastPos.y;
	}

	// 死亡処理
	public void finish(){

		super.finish();
	}

	// ゲッター
	public DetailBase getDetail(){ return mDetail; }
	public int getFieldNumber(){ return mFieldNumber; }
	protected boolean getIsMy(){ return mIsMy; }
}
