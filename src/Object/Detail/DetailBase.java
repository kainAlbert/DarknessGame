package Object.Detail;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.StringLabel;

public class DetailBase extends CharacterBase{

	protected StringLabel mCostLabel;
	protected StringLabel mAttackLabel;
	protected StringLabel mHPLabel;
	protected int mCardID;
	protected String mName;
	protected int mCost;
	protected int mAttack;
	protected int mHP;
	protected boolean mIsCreature;
	protected String mExplanation;
	protected boolean mIsMy;

	// コンストラクタ
	public DetailBase( boolean isMy ){

		mIsMy = isMy;

		// 各数字の文字ラベル生成
		mCostLabel = new StringLabel();
		mAttackLabel = new StringLabel();
		mHPLabel = new StringLabel();
	}

	// 初期化
	public void initialize( int cardID, GSvector2 pos, GSvector2 size, int type ){

		mCardID = cardID;

		// 詳細を読み込む
		DetailStructure str = DetailReader.readDetail( cardID );

		mCost = str.mCost;
		mName = str.mName;
		mAttack = str.mAttack;
		mHP = str.mHP;
		mIsCreature = str.mIsCreature;
		mExplanation = str.mExplanation;

		// 敵の場のカードでなければ裏面画像を使う
		String fileName = "detail/" + mCardID;

		if( !mIsMy && type != Define.CARD_TYPE.ENEMYFIELD.ordinal() ){

			fileName = "card_back";
		}

		// 親クラスの初期化
		super.initialize( fileName, pos, size,
				new GSvector2( Define.CARD_RESIZE.x / 2, Define.CARD_RESIZE.y / 2 ), 0, 0);

		mType = type;

		mCostLabel.initialize( "attackNum",
				new GSvector2( pos.x, pos.y ),
				Define.CARD_NUM_SIZE,
				new GSvector2( pos.x, pos.y ),
				new GSvector2( Define.CARD_NUM_IMAGE_SIZE, Define.CARD_NUM_IMAGE_SIZE ),
				new GSvector2( Define.CARD_NUM_IMAGE_RESIZE.x, Define.CARD_NUM_IMAGE_RESIZE.y ) );

		mAttackLabel.initialize( "attackNum",
				new GSvector2( pos.x, pos.y + size.y - Define.CARD_NUM_SIZE ),
				Define.CARD_NUM_SIZE,
				new GSvector2( pos.x, pos.y + size.y - Define.CARD_NUM_SIZE ),
				new GSvector2( Define.CARD_NUM_IMAGE_SIZE, Define.CARD_NUM_IMAGE_SIZE ),
				new GSvector2( Define.CARD_NUM_IMAGE_RESIZE.x, Define.CARD_NUM_IMAGE_RESIZE.y ) );

		mHPLabel.initialize( "attackNum",
				new GSvector2( pos.x + size.x - Define.CARD_NUM_SIZE, pos.y + size.y - Define.CARD_NUM_SIZE ),
				Define.CARD_NUM_SIZE,
				new GSvector2( pos.x + size.x - Define.CARD_NUM_SIZE, pos.y + size.y - Define.CARD_NUM_SIZE ),
				new GSvector2( Define.CARD_NUM_IMAGE_SIZE, Define.CARD_NUM_IMAGE_SIZE ),
				new GSvector2( Define.CARD_NUM_IMAGE_RESIZE.x, Define.CARD_NUM_IMAGE_RESIZE.y ) );
	}

	// 更新
	public void update( GSvector2 pos, GSvector2 size ){

		mPos = pos;
		mSize = size;

		// 敵の手札なら表示しない
		if( !mIsMy && mType != Define.CARD_TYPE.ENEMYFIELD.ordinal() ) return;

		// 各数字の位置設定
		mCostLabel.updateNum( mCost, new GSvector2( pos.x, pos.y ) );

		// 兵士以外は表示しない
		if( !mIsCreature ) return;

		mAttackLabel.updateNum( mAttack, new GSvector2( pos.x, pos.y + size.y - Define.CARD_NUM_IMAGE_SIZE ) );
		mHPLabel.updateNum( mHP, new GSvector2( pos.x + size.x - Define.CARD_NUM_IMAGE_SIZE, pos.y + size.y - Define.CARD_NUM_IMAGE_SIZE ) );
	}

	// ダメージ
	public void damage( int d ){

		mHP = Math.max( mHP - d, 0 );
	}

	// ゲッター
	public StringLabel getCostLabel(){ return mCostLabel; }
	public StringLabel getAttackLabel(){ return mAttackLabel; }
	public StringLabel getHPLabel(){ return mHPLabel; }
	public int getCardID(){ return mCardID; }
	public String getName(){ return mName; }
	public int getCost(){ return mCost; }
	public int getAttack(){ return mAttack; }
	public int getHP(){ return mHP; }
	public boolean getIsCreature(){ return mIsCreature; }
	public String getExplanation(){ return mExplanation; }
}
