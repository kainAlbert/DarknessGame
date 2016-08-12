package Object.Card;

import java.awt.Point;

import Application.Define;
import Application.GSvector2;

public class FieldCard extends Card{

	// コンストラクタ
	public FieldCard(){

		super();
	}

	// 初期化
	public void initialize( GSvector2 pos, GSvector2 lastPos ){

		super.initialize();

		mPos = pos;
		mLastPos = lastPos;
		mType = Define.CARD_TYPE.MYFIELD.ordinal();
	}

	// 更新
	public void update(){

		super.update();
	}

	// 選択
	public void select( Point mousePos ){

		mIsSelect = true;
	}

	// 選択解除
	public void release( Point mousePos ){

		mIsSelect = false;
	}

	// ドラッグ
	public void drag( Point mousePos ){

	}
}
