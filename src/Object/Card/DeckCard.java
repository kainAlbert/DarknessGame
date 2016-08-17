package Object.Card;

import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.Tactician;
import Object.Detail.DetailBase;

public class DeckCard extends Card{

	private List<CharacterBase> mDeckList;
	private boolean mIsStartHand;

	// コンストラクタ
	public DeckCard( boolean isMy ){

		super( isMy );

		mDeckList = new ArrayList<CharacterBase>();

		mDetail = new DetailBase( isMy );
	}

	// 初期化
	public void initialize(){

		GSvector2 pos = mIsMy ?
				new GSvector2( Define.MYDECK_POS.x, Define.MYDECK_POS.y ) :
				new GSvector2( Define.ENEMYDECK_POS.x, Define.ENEMYDECK_POS.y );

		super.initialize( "card_back",
				pos,
				new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
				Application.getObj().getCardManager( mIsMy ).getCardID(),
				Define.CARD_TYPE.DECK.ordinal()
				);

		CharacterBase t = Application.getObj().getCharacterManager().getTactician( mIsMy );

		mDeckList = DeckReader.readDeck( ((Tactician)t).getID(), mIsMy, new GSvector2( mPos.x, mPos.y ) );

		mIsStartHand = false;
	}

	// 更新
	public void update(){

		if( mIsStartHand ) return;

		for( int i=0; i<Define.FIRST_HAND_CARD; i++ ){

			drawCard();
		}

		mIsStartHand = true;
	}

	// カードを引く
	public void drawCard(){

		if( mDeckList.size() == 0 ){

			mSize.x = 0;
			return;
		}

		CharacterBase card = mDeckList.get(0);

		Application.getObj().getCardManager( mIsMy ).addCardList( card );

		mDeckList.remove(0);

		if( mIsMy ){
			((DeckCard)Application.getObj().getCardManager( false ).getCardList().get(0)).click();
		}
	}

	// クリック
	public void click(){}

	// 選択
	public void select(){}

	// 選択解除
	public void release(){}

	// ドラッグ
	public void drag(){}

	// ゲッター
	public List<CharacterBase> getList(){ return mDeckList; }
}
