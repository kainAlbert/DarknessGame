package Object.Card;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class CardManager {

	private List<CharacterBase> mCardList;
	private CharacterBase mExplanation;
	private GSvector2[] mHandPos;
	private int mCardID;
	private boolean mIsMy;

	// コンストラクタ
	public CardManager( boolean isMy ){

		// リストを生成
		mCardList = new ArrayList<CharacterBase>();

		// 敵か味方か
		mIsMy = isMy;

		// デッキ生成
		CharacterBase deck = new DeckCard();

		mCardList.add( deck );
	}

	// 初期化
	public void initialize(){

		// 手札の位置設定
		final double FIRST_POS_X = 100;
		double[] POS_Y = {
				Define.FIELD_MYHAND+20, Define.FIELD_MYHAND+15, Define.FIELD_MYHAND+10, Define.FIELD_MYHAND+5, Define.FIELD_MYHAND,
				Define.FIELD_MYHAND, Define.FIELD_MYHAND+5, Define.FIELD_MYHAND+10, Define.FIELD_MYHAND+15, Define.FIELD_MYHAND+20
		};

		mHandPos = new GSvector2[Define.MAX_HAND_CARD];

		for( int i=0; i<Define.MAX_HAND_CARD; i++ ){

			mHandPos[i] = new GSvector2( FIRST_POS_X + i * Define.CARD_SIZE.x * 0.9, POS_Y[i] );
		}

		// リスト初期化
		for( int i=0; i<mCardList.size(); i++ ){

			mCardList.get(i).initialize();
		}

		// 変数初期化
		mCardID = 1;
	}

	// 更新
	public void update(){

		// リスト更新
		updateList( mCardList );

		// 手札ソート
		sortHand();

		// 説明更新
		if( mExplanation != null ){

			mExplanation.update();

			if( mExplanation.getIsDead() ){

				mExplanation.finish();
				mExplanation = null;
			}
		}
	}

	// リスト更新
	private void updateList( List<CharacterBase> list ){

		for( int i=0; i<list.size(); i++ ){

			list.get(i).update();

			if( !list.get(i).getIsDead() ) continue;

			list.get(i).finish();

			list.remove(i);

			continue;
		}
	}

	// 手札ソート
	private void sortHand(){

		int handNum = searchTypeNum( Define.CARD_TYPE.MYHAND );
		int index = ( Define.MAX_HAND_CARD - handNum ) / 2;

		for( int i=0; i<mCardList.size(); i++ ){

			if( mCardList.get(i) == null ) continue;

			// 手札のみ
			if( mCardList.get(i).getType() != Define.CARD_TYPE.MYHAND.ordinal() ) continue;

			((HandCard)mCardList.get(i)).sortPos( mHandPos[ index ] );

			index++;
		}
	}

	// リスト内タイプ数検索
	public int searchTypeNum( Define.CARD_TYPE type ){

		int num = 0;

		for( int i=0; i<mCardList.size(); i++ ){

			if( mCardList.get(i).getType() == type.ordinal() ) num++;
		}

		return num;
	}

	// マウスの挙動
	public void mouseMove( String index, Point mousePos, Define.MOUSE_CARD_TYPE type ){

		for( int i=0; i<mCardList.size(); i++ ){

			CharacterBase c = mCardList.get(i);

			if( !c.getButton().getActionCommand().equals(index) ) continue;

			switch( type ){
			case MOUSEON:
				c.mouseONOFF();
				return;
			case MOUSEOFF:
				c.mouseONOFF();
				return;
			case SELECT:
				c.select( mousePos );
				return;
			case RELEASE:
				c.release( mousePos );
				return;
			case DRAG:
				c.drag( mousePos );
			default:
			}
		}
	}

	// カード説明を生成
	public void createExplanation( int id, GSvector2 pos, GSvector2 size){

		if( mExplanation == null ){

			// 説明を生成
			mExplanation = new CardExplanation();
			mExplanation.initialize();
		}

		// ID設定
		((CardExplanation)mExplanation).setID( id, pos, size );
	}

	// カードリストに追加
	public void addCardList( CharacterBase c ){

		mCardList.add( c );

		mCardID++;
	}

	// ゲッター
	public List<CharacterBase> getCardList(){ return mCardList; }
	public CharacterBase getExplanation(){ return mExplanation; }
	public int getCardID(){ return mCardID; }
}
