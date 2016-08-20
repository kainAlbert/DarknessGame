package Object.Detail.DetailList.Syoku;

import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class SyokuGigantic extends DetailBase{

	// コンストラクタ
	public SyokuGigantic( boolean isMy ) {

		super( isMy );

		mSelectCharacter = null;
	}

	// プレイ
	public void play(){

		mSelectCharacter.care( 3 );

		mSelectCharacter.powerChange( 3 );
	}

	// 条件
	public boolean useCondition( GSvector2 mousePos, CharacterBase tactician, boolean isHand  ){

		// 親クラス条件
		if( !super.useCondition(mousePos, tactician, isHand) ) return false;

		// 選択している味方兵士を取得
		mSelectCharacter = getSelectSoldier( true );

		if( mSelectCharacter != null ) return true;

		// 選択している味方軍師を取得
		mSelectCharacter = getSelectTactician( true );

		return mSelectCharacter != null;
	}
}
