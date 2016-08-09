package Object.Card;

import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class CardExplanation extends CharacterBase{

	// コンストラクタ
	public CardExplanation(){

	}

	// 更新
	public void update( GSvector2 pos, boolean isDraw ){

		super.update();

		mPos = new GSvector2( -1000, -1000 );

		if( !isDraw ) return;

		mPos.x = Math.min( Math.max( pos.x, 0 ), Define.WINDOW_SIZE.x - mSize.x );
		mPos.y = Math.min( Math.max( pos.y - mSize.y, 0 ), Define.WINDOW_SIZE.y - mSize.y );
	}
}
