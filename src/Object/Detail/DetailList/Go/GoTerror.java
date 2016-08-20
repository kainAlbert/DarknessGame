package Object.Detail.DetailList.Go;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class GoTerror extends DetailBase{

	// コンストラクタ
	public GoTerror( boolean isMy ) {

		super( isMy );

		mSelectCharacter = null;
	}

	// プレイ
	public void play(){

		if( mIsPlay ) return;

		mIsPlay = true;

		mSelectCharacter.damage( 99999 );
	}

	// 条件
	public boolean useCondition( GSvector2 mousePos, CharacterBase tactician, boolean isHand  ){

		// 親クラス条件
		if( !super.useCondition(mousePos, tactician, isHand) ) return false;

		// 選択している敵兵士を取得
		mSelectCharacter = getSelectSoldier( false );

		if( mSelectCharacter != null ) return true;

		Application.getStringLabel().setType( Define.STRING_TYPE.SELECT_ENEMY_SOLDIER );
		Application.getStringLabel().setPos();

		return false;
	}
}
