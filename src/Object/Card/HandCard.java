package Object.Card;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.Tactician;
import Object.Detail.DetailReader;

public class HandCard extends Card{

	private boolean mIsHand;
	private final double FIRST_MOVE_Y = 150;

	// コンストラクタ
	public HandCard( boolean isMy ){

		super( isMy );
	}

	// 初期化
	public void initialize( int cardID, GSvector2 pos ){

		mIsHand = false;

		super.initialize();

		mDetail = DetailReader.getDetail( cardID, mIsMy );

		super.initializeDetail( cardID );

		mPos = pos;
		mLastPos.x = pos.x;
		mLastPos.y = pos.y + FIRST_MOVE_Y * ( mIsMy ? 1 : -1 );
	}

	// 更新
	public void update(){

		super.update();

		enemyAction();

		// 手札状態でない時の処理
		if( mIsHand ) return;

		updateNotHand();
	}

	// 敵だけの処理
	private void enemyAction(){

		if( mIsMy || !mIsHand ) return;

		if( mPos.y != mLastPos.y ) return;

		int fieldCardNum = Application.getObj().getCardManager( mIsMy ).searchTypeNum( Define.CARD_TYPE.ENEMYFIELD );

		if( fieldCardNum >=  Define.MAX_FIELD_CARD ) return;

		// フィールドカードを生成
		CharacterBase card = new SoldierCard( mIsMy );

		((SoldierCard)card).initialize(
				mDetail,
				new GSvector2( mPos.x, mPos.y )
				);

		// リストに追加
		Application.getObj().getCardManager( mIsMy ).addCardList( card );

		// この手札は死亡させる
		mIsDead = true;
	}

	// 手札状態でない時の処理
	private void updateNotHand(){

		// 終点でなければ終了
		if( mPos.x != mLastPos.x || mPos.y != mLastPos.y ) return;

		int handNum = Application.getObj().getCardManager( mIsMy ).searchTypeNum( mIsMy ? Define.CARD_TYPE.MYHAND : Define.CARD_TYPE.ENEMYHAND );

		// 手札がすでに最大なら消去
		if( handNum >= Define.MAX_HAND_CARD ){

			mIsDead = true;
			return;
		}

		mIsHand = true;

		mType = mIsMy ? Define.CARD_TYPE.MYHAND.ordinal() : Define.CARD_TYPE.ENEMYHAND.ordinal();
	}

	// クリックした時
	public void click(){

		Application.getObj().getCardManager( mIsMy ).createExplanation( mID, mPos, mSize );
	}

	// 選択した時
	public void select(){

		if( !mIsHand ) return;

		super.select();
	}

	// マウスを離した時
	public void release(){

		if( !mIsHand ) return;

		if( !mIsSelect ) return;

		super.release();

		// マウス位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		// 軍師取得
		CharacterBase tactician = Application.getObj().getCharacterManager().getTactician( mIsMy );

		// 条件確認
		if( !mDetail.useCondition( mousePos, tactician, mType == Define.CARD_TYPE.MYHAND.ordinal() ) ) return;

		// マナを消費
		((Tactician)tactician).useMana( mDetail.getCost() );

		// この手札は死亡させる
		mIsDead = true;

		// 兵士召喚
		if( mDetail.getIsSoldier() ){

			putCreature( mousePos, tactician );
		}

		if( !mDetail.getIsSoldier() ){

			mDetail.play();
		}
	}

	// フィールドにカードを置く
	private void putCreature( GSvector2 mousePos, CharacterBase tactician ){

		// フィールドカードを生成
		CharacterBase card = new SoldierCard( mIsMy );

		((SoldierCard)card).initialize(
				mDetail,
				new GSvector2( mPos.x, mPos.y )
				);

		// リストに追加
		Application.getObj().getCardManager( mIsMy ).addCardList( card );
	}

	// ドラッグ
	public void drag(){

		if( !mIsHand ) return;

		if( !mIsSelect ) return;

		super.drag();
	}
}
