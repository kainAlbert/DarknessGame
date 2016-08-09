package Object.Card;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class CardManager {

	List<CharacterBase> mCardList;

	// コンストラクタ
	public CardManager( Application app ){

		// リストを生成
		mCardList = new ArrayList<CharacterBase>();

		// カードを生成
		for( int i=0; i<5; i++ ){

			CharacterBase c = new Card();
			((Card)c).initialize( app, "bullet", new GSvector2( 100 + i * 50, 100 ),
					new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
					new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
					i, Define.CARD_TYPE.ENEMYHAND.ordinal() );
			c.initializeLabel(app);

			mCardList.add( c );
		}
		for( int i=0; i<10; i++ ){

			final double FIRST_POSY = 490;
			double[] posy = { FIRST_POSY+20, FIRST_POSY+15, FIRST_POSY+10, FIRST_POSY+5, FIRST_POSY, FIRST_POSY, FIRST_POSY+5, FIRST_POSY+10, FIRST_POSY+15, FIRST_POSY+20 };

			CharacterBase c = new Card();
			((Card)c).initialize(app, "bullet", new GSvector2( 100 + i * Define.CARD_SIZE.x * 0.9, posy[i] ),
					new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
					new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
					i, Define.CARD_TYPE.MYHAND.ordinal() );
			c.initializeButton(app);

			mCardList.add( c );
		}
	}

	// 更新
	public void update(){

		updateList( mCardList );
	}

	// リスト更新
	private void updateList( List<CharacterBase> list ){

		for( int i=0; i<list.size(); i++ ){

			list.get(i).update();

			if( !list.get(i).getIsDead() ) continue;

			list.remove(i);
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

			if( mCardList.get(i).getButton().getActionCommand().equals(index) ){

				switch( type ){
				case MOUSEON:
					mCardList.get(i).mouseONOFF();
					return;
				case MOUSEOFF:
					mCardList.get(i).mouseONOFF();
					return;
				case SELECT:
					mCardList.get(i).select( mousePos );
					return;
				case RELEASE:
					mCardList.get(i).release( mousePos );
					return;
				case DRAG:
					mCardList.get(i).drag( mousePos );
				default:
				}

			}
		}
	}

	// ゲッター
	public List<CharacterBase> getCardList(){ return mCardList; }
}
