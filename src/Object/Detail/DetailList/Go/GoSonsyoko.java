package Object.Detail.DetailList.Go;

import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class GoSonsyoko extends DetailBase{

	private CharacterBase mSelectCharacter;

	// コンストラクタ
	public GoSonsyoko( boolean isMy ) {

		super( isMy );

		mSelectCharacter = null;
	}

	// プレイ
	public void play(){

		mSelectCharacter.damage( mAttack );
	}

	// 条件
	public boolean useCondition( GSvector2 mousePos, CharacterBase tactician, boolean isHand  ){

		// 親クラス条件
		if( !super.useCondition(mousePos, tactician, isHand) ) return false;

		// 選択している敵兵士を取得
		mSelectCharacter = getSelectSoldier( false );

		if( mSelectCharacter != null ) return true;

		mSelectCharacter = getSelectTactician( false );

		return mSelectCharacter != null;
	}
}
