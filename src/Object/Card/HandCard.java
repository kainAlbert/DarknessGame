package Object.Card;

import java.awt.Point;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class HandCard extends Card{

	private boolean mIsHand;
	private final double FIRST_MOVE_X = 200;

	// コンストラクタ
	public HandCard(){

		super();
	}

	// 初期化
	public void initialize( GSvector2 pos ){

		mIsHand = false;

		super.initialize();

		mPos = pos;
		mLastPos.x = pos.x - FIRST_MOVE_X;
		mLastPos.y = pos.y;
	}

	// 更新
	public void update(){

		super.update();

		// 手札状態でない時の処理
		if( mIsHand ) return;

		updateNotHand();
	}

	// 手札状態でない時の処理
	private void updateNotHand(){

		// 終点でなければ終了
		if( mPos.x != mLastPos.x ) return;

		int handNum = Application.getObj().getMyCardManager().searchTypeNum( Define.CARD_TYPE.MYHAND );

		// 手札がすでに最大なら消去
		if( handNum >= Define.MAX_HAND_CARD ){

			mIsDead = true;
			return;
		}

		mIsHand = true;

		mType = Define.CARD_TYPE.MYHAND.ordinal();
	}

	// 位置ソート
	public void sortPos( GSvector2 pos ){

		mLastPos = new GSvector2( pos.x, pos.y );
	}

	// 選択した時
	public void select( Point mousePos ){

		if( !mIsHand ) return;

		super.select( mousePos );
	}

	// マウスを離した時
	public void release( Point mousePos ){

		if( !mIsHand ) return;

		// 選択を解除
		mIsSelect = false;

		// 自分のフィールドのカードの数を取得
		int myFieldNum = Application.getObj().getMyCardManager().searchTypeNum( Define.CARD_TYPE.MYFIELD );

		// 手を離した場所が手札の位置ではないか
		boolean isPosY = mousePos.y + mPos.y < Define.FIELD_MYHAND;

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

		super.release( mousePos );
	}

	// フィールドにカードを置く
	private boolean putCreature( Point mousePos ){

		// 位置xを取得
		double lastPosX = returnPutPos( mousePos );

		if( lastPosX == -1 ) return false;

		// フィールドカードを生成
		CharacterBase card = new FieldCard();

		((FieldCard)card).initialize( new GSvector2( mPos.x, mPos.y ), new GSvector2( lastPosX, Define.FIELD_MYCARD_POSY ) );

		// リストに追加
		Application.getObj().getMyCardManager().addCardList( card );

		// この手札は死亡させる
		mIsDead = true;

//		// タイプをフィールドに設定
//		mType = Define.CARD_TYPE.MYFIELD.ordinal();
//
//		// 終点を設定
//		mLastPos.x = lastPosX;
//		mLastPos.y = Define.FIELD_MYCARD_POSY;

		return true;
	}

	// カードを置く位置を返す
	private double returnPutPos( Point mousePos ){

		double posx = -1;
		int fieldNumber = 0;

		// フィールドの最大位置より右なら最大位置にする
		if( mousePos.x + mPos.x - mSize.x > Define.FIELD_CARD_POSX[ Define.FIELD_CARD_POSX.length - 1 ] ){

			posx = Define.FIELD_CARD_POSX[ Define.FIELD_CARD_POSX.length - 1 ];
			fieldNumber = Define.FIELD_CARD_POSX.length - 1;
		}

		// 各位置より左にマウスがあるならその位置にする
		for( int i=0; i<Define.FIELD_CARD_POSX.length; i++ ){

			if( mousePos.x + mPos.x - mSize.x < Define.FIELD_CARD_POSX[i] ){

				posx = Define.FIELD_CARD_POSX[i];
				fieldNumber = i;
				break;
			}
		}

		List<CharacterBase> list = Application.getObj().getMyCardManager().getCardList();

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
	public void drag( Point mousePos ){

		if( !mIsHand ) return;

		super.drag( mousePos );
	}
}
