package Object.Card;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.StringLabel;

public class Card extends CharacterBase{

	private StringLabel mCostLabel;
	private StringLabel mAttackLabel;
	private StringLabel mHPLabel;
	protected int mCardID;
	protected int mCost;
	protected int mAttack;
	protected int mHP;
	protected int mFieldNumber;
	protected boolean mIsCreature;
	protected int mMouseOnTimer;

	// コンストラクタ
	public Card(){

		mCardID = 0;
		mCost = 3;
		mAttack = 4;
		mHP = 5;
		mFieldNumber = 0;
		mIsCreature = true;
		mMouseOnTimer = 0;

		// 各数字の文字ラベル生成
		mAttackLabel = null;
		mHPLabel = null;

		mCostLabel = new StringLabel( String.valueOf( mCost ), Define.CARD_NUM_SIZE );

		// クリーチャーカード以外は生成しない
		if( !mIsCreature ) return;

		mAttackLabel = new StringLabel( String.valueOf( mAttack ), Define.CARD_NUM_SIZE );
		mHPLabel = new StringLabel( String.valueOf( mHP ), Define.CARD_NUM_SIZE );
	}

	// 初期化
	public void initialize(){

		int cardID = Application.getObj().getMyCardManager().getCardID();

		super.initialize( "card_image",
				new GSvector2(),
				new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
				cardID, Define.CARD_TYPE.NONE.ordinal() );

		super.initializeButton();
	}

	// 更新
	public void update(){

		moveBack();

		super.update();

		// 未選択でマウスが置かれていたらタイマーを回す
		mMouseOnTimer = mIsMouseOn && !mIsSelect ? mMouseOnTimer + 1 : 0;

		// 一定時間マウスが置かれたら説明を出す
		if( mMouseOnTimer > Define.MOUSE_ON_TIME ){

			Application.getObj().getMyCardManager().createExplanation( mID, mPos, mSize );
		}

		// 各数字の位置設定
		mCostLabel.setCost( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );

		if( !mIsCreature ) return;
		mAttackLabel.setAttack( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
		mHPLabel.setHP( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
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

		mCostLabel.finish();
		mAttackLabel.finish();
		mHPLabel.finish();

		super.finish();
	}

	// ゲッター
	public int getCardID(){ return mCardID; }
	public int getCost(){ return mCost; }
	public int getAttack(){ return mAttack; }
	public int getHP(){ return mHP; }
	public int getFieldNumber(){ return mFieldNumber; }
}
