package Object.Card;

import java.awt.Point;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class DeckCard extends CharacterBase{

	// コンストラクタ
	public DeckCard(){

		super();
	}

	// 初期化
	public void initialize(){

		super.initialize( "card_back",
				new GSvector2( Define.MYDECK_POS.x, Define.MYDECK_POS.y ),
				new GSvector2( Define.CARD_SIZE.x, Define.CARD_SIZE.y ),
				new GSvector2( Define.CARD_RESIZE.x, Define.CARD_RESIZE.y ),
				0, 0 );

		super.initializeButton();
	}

	// 選択
	public void select( Point mousePos ){

		CharacterBase card = new HandCard();
		((HandCard)card).initialize( new GSvector2( mPos.x, mPos.y ) );

		Application.getObj().getMyCardManager().addCardList( card );
	}

	// 選択解除
	public void release( Point mousePos ){}

	// ドラッグ
	public void drag( Point mousePos ){}
}
