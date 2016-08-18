package Object.Card;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.NumLabel;
import Object.Detail.DetailReader;
import Object.Detail.DetailStructure;

public class CardExplanation extends CharacterBase{

	private CharacterBase mDetailImage;
	private NumLabel mCostLabel;
	private NumLabel mAttackLabel;
	private NumLabel mHPLabel;
	private DetailStructure mStr;
	private int mTimer;
	private boolean mIsInitialize;

	// コンストラクタ
	public CardExplanation(){

		super();

		mTimer = Define.CARD_EXPLANATION_TIME;

		mDetailImage = new CharacterBase();
		mStr = new DetailStructure();

		// 各ラベル生成
		mCostLabel = new NumLabel();
		mAttackLabel = new NumLabel();
		mHPLabel = new NumLabel();

		mIsInitialize = false;
	}

	// 初期化
	public void initialize( int cardID, GSvector2 pos ){

		super.initialize( "card_explanation",
				pos,
				new GSvector2( Define.CARD_EXPLANATION_SIZE.x, Define.CARD_EXPLANATION_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0
				);

		mDetailImage.initialize(
				"detail/" + cardID,
				new GSvector2( pos.x + Define.CARD_EXPLANATION_SIZE.x * 0.1, pos.y ),
				new GSvector2( Define.CARD_EXPLANATION_SIZE.x * 0.8, Define.CARD_EXPLANATION_SIZE.y * 0.8 ),
				new GSvector2( Define.CARD_RESIZE.x / 2, Define.CARD_RESIZE.y / 2 ),
				0, 0);

		mStr = DetailReader.readDetail( cardID );

		// ラベルクラス初期化
		mCostLabel.initialize( Define.CARD_NUM_TYPE.COST.ordinal(), Define.CARD_NUM_IMAGE_SIZE * 2 );
		mAttackLabel.initialize( Define.CARD_NUM_TYPE.ATTACK.ordinal(), Define.CARD_NUM_IMAGE_SIZE * 2 );
		mHPLabel.initialize( Define.CARD_NUM_TYPE.HP.ordinal(), Define.CARD_NUM_IMAGE_SIZE * 2 );

		mIsInitialize = true;
	}

	// 更新
	public void update(){

		if( !mIsInitialize ) return;

		super.update();

		// 各数字の位置設定
		mCostLabel.updateNum( mStr.mCost, new GSvector2( mPos.x, mPos.y ) );

		// 兵士以外は表示しない
		if( mStr.mIsSoldier ){

			mAttackLabel.updateNum( mStr.mAttack, new GSvector2( mPos.x, mPos.y + mSize.y - Define.CARD_NUM_IMAGE_SIZE * 2 ) );
			mHPLabel.updateNum( mStr.mHP, new GSvector2( mPos.x + mSize.x - Define.CARD_NUM_IMAGE_SIZE * 2, mPos.y + mSize.y - Define.CARD_NUM_IMAGE_SIZE * 2 ) );
		}

		mTimer--;

		if( mTimer <= 0 ) mIsDead = true;
	}

	// ゲッター
	public CharacterBase getDetailImage(){ return mDetailImage; }
	public NumLabel getCostLabel(){ return mCostLabel; }
	public NumLabel getAttackLabel(){ return mAttackLabel; }
	public NumLabel getHPLabel(){ return mHPLabel; }
	public boolean getIsInitialize(){ return mIsInitialize; }
}
