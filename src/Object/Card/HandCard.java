package Object.Card;

import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

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

		if( fieldCardNum >=  5 ) return;

		// フィールドカードを生成
		CharacterBase card = new SoldierCard( mIsMy );

		((SoldierCard)card).initialize( mDetail.getCardID(), new GSvector2( mPos.x, mPos.y ), new GSvector2( Define.FIELD_CARD_POSX[ fieldCardNum ], Define.FIELD_ENEMYCARD_POSY ) );

		// リストに追加
		Application.getObj().getCardManager( mIsMy ).addCardList( card );

		// この手札は死亡させる
		mIsDead = true;
	}

	// 手札状態でない時の処理
	private void updateNotHand(){

		// 終点でなければ終了
		if( mPos.y != mLastPos.y ) return;

		int handNum = Application.getObj().getCardManager( mIsMy ).searchTypeNum( mIsMy ? Define.CARD_TYPE.MYHAND : Define.CARD_TYPE.ENEMYHAND );

		// 手札がすでに最大なら消去
		if( handNum >= Define.MAX_HAND_CARD ){

			mIsDead = true;
			return;
		}

		mIsHand = true;

		mType = mIsMy ? Define.CARD_TYPE.MYHAND.ordinal() : Define.CARD_TYPE.ENEMYHAND.ordinal();
	}

	// 位置ソート
	public void sortPos( GSvector2 pos ){

		mLastPos = new GSvector2( pos.x, pos.y );
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

		mIsSelect = false;

		// マウス位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		// 自分のフィールドのカードの数を取得
		int myFieldNum = Application.getObj().getCardManager( mIsMy ).searchTypeNum( Define.CARD_TYPE.MYFIELD );

		// 手を離した場所が手札の位置ではないか
		boolean isPosY = mPos.y < Define.FIELD_MYCARD_POSY + Define.CARD_SIZE.y;

		// フィールドのカードの数が5未満か
		boolean isFieldNum =  myFieldNum < Define.MAX_FIELD_CARD;

		// 手札にあるか
		boolean isHand = mType == Define.CARD_TYPE.MYHAND.ordinal();

		// 条件を満たせばクリーチャーを置く
		if( isPosY && isFieldNum && isHand ){

			if( putCreature( mousePos ) ) return;
		}

		// 条件を満たせば呪文を使う
		if( true ){

		}

		super.release();
	}

	// フィールドにカードを置く
	private boolean putCreature( GSvector2 mousePos ){

		// 位置xを取得
		double lastPosX = returnPutPos( mousePos );

		if( lastPosX == -1 ) return false;

		// フィールドカードを生成
		CharacterBase card = new SoldierCard( mIsMy );

		((SoldierCard)card).initialize( mDetail.getCardID(), new GSvector2( mPos.x, mPos.y ), new GSvector2( lastPosX, Define.FIELD_MYCARD_POSY ) );

		// リストに追加
		Application.getObj().getCardManager( mIsMy ).addCardList( card );

		// この手札は死亡させる
		mIsDead = true;

		return true;
	}

	// カードを置く位置を返す
	private double returnPutPos( GSvector2 mousePos ){

		double posx = -1;
		int fieldNumber = 0;

		// フィールドの最大位置より右なら最大位置にする
		if( mPos.x - mSize.x > Define.FIELD_CARD_POSX[ Define.FIELD_CARD_POSX.length - 1 ] ){

			posx = Define.FIELD_CARD_POSX[ Define.FIELD_CARD_POSX.length - 1 ];
			fieldNumber = Define.FIELD_CARD_POSX.length - 1;
		}

		// 各位置より左にマウスがあるならその位置にする
		for( int i=0; i<Define.FIELD_CARD_POSX.length; i++ ){

			if( mPos.x - mSize.x < Define.FIELD_CARD_POSX[i] ){

				posx = Define.FIELD_CARD_POSX[i];
				fieldNumber = i;
				break;
			}
		}

		List<CharacterBase> list = Application.getObj().getCardManager( mIsMy ).getCardList();

		// すでに同じ位置にカードがあれば置かない
		for( int i=0; i<list.size(); i++ ){

			if( list.get(i).getType() == Define.CARD_TYPE.MYFIELD.ordinal() && list.get(i).getPos().x == posx ){

				return -1;
			}
		}

		mFieldNumber = fieldNumber;
		return posx;
	}

	// ドラッグ
	public void drag(){

		if( !mIsHand ) return;

		if( !mIsSelect ) return;

		super.drag();
	}
}
