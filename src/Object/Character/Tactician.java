package Object.Character;

import Application.Define;
import Application.GSvector2;

public class Tactician extends CharacterBase{

	private NumLabel mManaLabel;
	private NumLabel mHPLabel;
	private int mMaxHP;
	private int mHP;
	private boolean mIsMy;
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

		GSvector2 pos = mIsMy ?
				new GSvector2( Define.TACTICIAN_MYPOS.x, Define.TACTICIAN_MYPOS.y ) :
					new GSvector2( Define.TACTICIAN_ENEMYPOS.x, Define.TACTICIAN_ENEMYPOS.y );

				super.initialize(
						Define.TACTICIAN_IMAGE_NAME[ id.ordinal() ],
						pos,
						new GSvector2( Define.TACTICIAN_SIZE.x, Define.TACTICIAN_SIZE.y ),
						new GSvector2( Define.TACTICIAN_RESIZE.x, Define.TACTICIAN_RESIZE.y ),
						0, 0 );

				mManaLabel.initialize( Define.CARD_NUM_TYPE.COST.ordinal(), Define.TACTICIAN_NUM_IMAGE_SIZE );
				mHPLabel.initialize( Define.CARD_NUM_TYPE.HP.ordinal(), Define.TACTICIAN_NUM_IMAGE_SIZE );
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

		mMaxMana++;
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
	}

	// 回復
	public void care( int c ){

		mHP = Math.min( mHP + c, Define.TACTICIAN_MAX_HP );
	}

	// マナを消費
	public void useMana( int m ){

		mMana -= m;
	}

	// ゲッター
	public NumLabel getManaLabel(){ return mManaLabel; }
	public NumLabel getHPLabel(){ return mHPLabel; }
	public int getHP(){ return mHP; }
	public int getID(){ return mID; }
	public int getMana(){ return mMana; }
}
