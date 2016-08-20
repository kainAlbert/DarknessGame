package Object.Character;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Detail.DetailBase;
import Object.Detail.DetailReader;
import Object.Effect.PointerEffect;

public class Tactician extends CharacterBase{

	private DetailBase mDetail;
	private NumLabel mManaLabel;
	private NumLabel mHPLabel;
	private int mMaxHP;
	private int mHP;
	private int mID;
	private int mMaxMana;
	private int mMana;

	// コンストラクタ
	public Tactician( boolean isMy ){

		mManaLabel = new NumLabel();
		mHPLabel = new NumLabel();

		mIsMy = isMy;
	}

	// 初期化
	public void initialize( Define.TACTICIAN_ID id ){

		mHP = Define.TACTICIAN_MAX_HP;
		mMaxHP = Define.TACTICIAN_MAX_HP;
		mID = id.ordinal();
		mMaxMana = 1;
		mMana = 1;

		GSvector2 pos = mIsMy ? new GSvector2( Define.TACTICIAN_MYPOS.x, Define.TACTICIAN_MYPOS.y ) :
			new GSvector2( Define.TACTICIAN_ENEMYPOS.x, Define.TACTICIAN_ENEMYPOS.y );

		super.initialize(
				Define.TACTICIAN_IMAGE_NAME[ id.ordinal() ],
				pos,
				new GSvector2( Define.TACTICIAN_SIZE.x, Define.TACTICIAN_SIZE.y ),
				new GSvector2( Define.TACTICIAN_RESIZE.x, Define.TACTICIAN_RESIZE.y ),
				0, 0 );



		int[] cardID = {
				Define.CARD_ID.TACTICIAN_SONKEN.ordinal(),
				Define.CARD_ID.TACTICIAN_SYOKATURYO.ordinal(),
				Define.CARD_ID.TACTICIAN_SIBAI.ordinal(),
				Define.CARD_ID.TACTICIAN_TOTAKU.ordinal()
		};

		// Detail生成
		mDetail = DetailReader.getDetail( cardID[ id.ordinal() ], mIsMy );

		// Detail初期化
		mDetail.initialize( cardID[ id.ordinal() ] , mPos, mSize, 0 );

		mManaLabel.initialize( Define.CARD_NUM_TYPE.COST.ordinal(), Define.TACTICIAN_NUM_IMAGE_SIZE );
		mHPLabel.initialize( Define.CARD_NUM_TYPE.HP.ordinal(), Define.TACTICIAN_NUM_IMAGE_SIZE );

		mFieldNumber = Define.MAX_FIELD_CARD;
	}

	// 更新
	public void update(){

		try{

			super.update();

			double hpPosX = mPos.x;
			double manaPosX = mPos.x + mSize.x - Define.TACTICIAN_NUM_IMAGE_SIZE * 1.5;
			double posY = mIsMy ? mPos.y + mSize.y : mPos.y - Define.TACTICIAN_NUM_IMAGE_SIZE;

			mManaLabel.updateNum( mMana, new GSvector2( manaPosX, posY ) );
			mHPLabel.updateNum( mHP, new GSvector2( hpPosX, posY ) );

		}catch( Exception e ){

		}
	}

	// ターン開始時の処理
	public void startTurn(){

		mMaxMana = Math.min( mMaxMana + 1, 10 );
		mMana = mMaxMana;
	}

	// マナ加算
	public void addMaxMana(){

		mMaxMana++;
	}

	// ダメージ
	public void damage( int d ){

		mHP = Math.max( mHP - d, 0 );

		mDamageTimer = Define.DAMAGE_TIME;

		super.damage(d);
	}

	// 回復
	public void care( int c ){

		mHP = Math.min( mHP + c, Define.TACTICIAN_MAX_HP );

		super.care(c);
	}

	// マナを消費
	public void useMana( int m ){

		mMana = Math.max( mMana - m, 0 );
	}

	// クリック
	public void click(){

		GSvector2 pos = new GSvector2( mPos.x + mSize.x, Define.CARD_EXPLANATION_Y );

		Application.getObj().getCardManager( true ).createExplanation( mDetail.getCardID(), pos, 1 );
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

	// ゲッター
	public NumLabel getManaLabel(){ return mManaLabel; }
	public NumLabel getHPLabel(){ return mHPLabel; }
	public int getHP(){ return mHP; }
	public int getID(){ return mID; }
	public int getMana(){ return mMana; }
}
