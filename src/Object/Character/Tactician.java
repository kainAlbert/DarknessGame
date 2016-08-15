package Object.Character;

import Application.Define;
import Application.GSvector2;

public class Tactician extends CharacterBase{

	private NumLabel mManaLabel;
	private NumLabel mHPLabel;
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

		mHP = 10;
		mID = id.ordinal();
		mMaxMana = 0;
		mMana = 10;

		GSvector2 pos = mIsMy ?
				new GSvector2( Define.TACTICIAN_MYPOS.x, Define.TACTICIAN_MYPOS.y ) :
				new GSvector2( Define.TACTICIAN_ENEMYPOS.x, Define.TACTICIAN_ENEMYPOS.y );

		super.initialize(
				Define.TACTICIAN_IMAGE_NAME[ id.ordinal() ],
				pos,
				new GSvector2( Define.TACTICIAN_SIZE.x, Define.TACTICIAN_SIZE.y ),
				new GSvector2( Define.TACTICIAN_RESIZE.x, Define.TACTICIAN_RESIZE.y ),
				0, 0 );

		mManaLabel.initialize( Define.CARD_NUM_TYPE.COST.ordinal() );
		mHPLabel.initialize( Define.CARD_NUM_TYPE.HP.ordinal() );
	}

	// 更新
	public void update(){

		super.update();

		double hpPosX = mPos.x + mSize.x * 0.3;
		double manaPosX = mPos.x + mSize.x * 0.6;
		double posY = mIsMy ? mPos.y - Define.CARD_NUM_IMAGE_SIZE : mPos.y + mSize.y;

		mManaLabel.updateNum( mMana, new GSvector2( manaPosX, posY ) );
		mHPLabel.updateNum( mHP, new GSvector2( hpPosX, posY ) );
	}

	// ダメージ
	public void damage( int damage ){

		mHP = Math.max( mHP - damage, 0 );

		mDamageTimer = Define.DAMAGE_TIME;
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
