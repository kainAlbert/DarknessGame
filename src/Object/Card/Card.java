package Object.Card;

import java.awt.Point;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.StringLabel;

public class Card extends CharacterBase{

	private CharacterBase mExplanation;
	private StringLabel mCostLabel;
	private StringLabel mAttackLabel;
	private StringLabel mHPLabel;
	private int mCardID;
	private int mCost;
	private int mAttack;
	private int mHP;
	private int mFieldNumber;
	private int mMouseOnTimer;

	// コンストラクタ
	public Card(){

		mExplanation = new CardExplanation();

		mCardID = 0;
		mCost = 3;
		mAttack = 4;
		mHP = 5;
		mFieldNumber = 0;
		mMouseOnTimer = 0;

		// 各数字の文字ラベル生成
		mCostLabel = new StringLabel( String.valueOf( mCost ), Define.CARD_NUM_SIZE );
		mAttackLabel = new StringLabel( String.valueOf( mAttack ), Define.CARD_NUM_SIZE );
		mHPLabel = new StringLabel( String.valueOf( mHP ), Define.CARD_NUM_SIZE );
	}

	// 初期化
	public void initialize( Application app, String fileName, GSvector2 pos, GSvector2 size, GSvector2 resize,  int id, int type ){

		super.initialize(fileName, pos, size, resize, id, type);

		mExplanation.initialize("bullet", new GSvector2(100,300), new GSvector2( 100, 100 ), new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ), 0, 0);
		mExplanation.initializeLabel(app);

	}

	// 更新
	public void update(){

		moveBack();

		super.update();

		// マウスが置かれていたらタイマーを回す
		mMouseOnTimer = mIsMouseOn ? mMouseOnTimer + 1 : 0;

		// 説明の更新。マウスが一定時間置かれていたら表示する。
		((CardExplanation)mExplanation).update( new GSvector2( mPos.x + mSize.x, mPos.y ), !mIsSelect && mMouseOnTimer > Define.MOUSE_ON_TIME );

		// 各数字の位置設定
		mCostLabel.setCost( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
		mAttackLabel.setAttack( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
		mHPLabel.setHP( new GSvector2( mPos.x, mPos.y ), new GSvector2( mSize.x, mSize.y ) );
	}

	// 元の場所に戻る
	private void moveBack(){

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

	// マウスを離した時
	public void release( Point mousePos ){

		// 選択を解除
		mIsSelect = false;

		// 自分のフィールドのカードの数を取得
		int myFieldNum = Application.getObj().getCardManager().searchTypeNum( Define.CARD_TYPE.MYFIELD );

		// 手を離した場所が手札の位置ではないか
		boolean isPosY = mousePos.y + mPos.y < Define.FIELD_HAND;

		// フィールドのカードの数が5未満か
		boolean isFieldNum =  myFieldNum < Define.MAX_FIELD_CARD;

		// 手札にあるか
		boolean isHand = mType == Define.CARD_TYPE.MYHAND.ordinal();

		// 条件を満たせばカードを置く
		if( isPosY && isFieldNum && isHand ){

			if( putField( mousePos ) ) return;
		}

		super.release( mousePos );
	}

	// フィールドにカードを置く
	private boolean putField( Point mousePos ){

		// 位置xを取得
		double posx = returnPutPos( mousePos );

		if( posx == -1 ) return false;

		// タイプをフィールドに設定
		mType = Define.CARD_TYPE.MYFIELD.ordinal();

		// 終点を設定
		mLastPos.x = posx;
		mLastPos.y = Define.FIELD_MYCARD_POSY;

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

		List<CharacterBase> list = Application.getObj().getCardManager().getCardList();

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

		if( mType != Define.CARD_TYPE.MYHAND.ordinal() ) return;

		super.drag( mousePos );
	}

	// 死亡処理
	public void finish(){

		super.finish();
	}

	// ゲッター
	public int getCardID(){ return mCardID; }
	public int getCost(){ return mCost; }
	public int getAttack(){ return mAttack; }
	public int getHP(){ return mHP; }
	public int getFieldNumber(){ return mFieldNumber; }
}
