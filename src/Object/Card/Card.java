package Object.Card;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;
import Object.Detail.DetailReader;
import Object.Effect.PointerEffect;

public class Card extends CharacterBase{

	protected DetailBase mDetail;
	protected int mFieldNumber;
	protected boolean mIsMy;

	// コンストラクタ
	public Card( boolean isMy ){

		mDetail = null;

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

		mDetail = DetailReader.getDetail( cardID, mIsMy );

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

	// 位置ソート
	public void sortPos( GSvector2 pos ){

		mLastPos = new GSvector2( pos.x, pos.y );
	}

	// 選択
	public void select(){

		super.select();

		CharacterBase p = Application.getObj().getEffectManager().getPointer();

		((PointerEffect)p).setFirstPos( new GSvector2( mPos.x + mSize.x / 2, mPos.y + mSize.y / 2 ) );
	}

	// 選択解除
	public void release(){

		super.release();

		// ポインターリセット
		CharacterBase p = Application.getObj().getEffectManager().getPointer();

		((PointerEffect)p).reset();
	}

	// ドラッグ
	public void drag(){

		// マウス位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		CharacterBase p = Application.getObj().getEffectManager().getPointer();

		((PointerEffect)p).setTargetPos( new GSvector2( mousePos.x, mousePos.y ) );
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
