package Object.Detail.DetailList.Go;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class GoSonsyoko extends DetailBase{

	// コンストラクタ
	public GoSonsyoko( boolean isMy ) {

		super( isMy );

		mSelectCharacter = null;
	}

	// 初期化
	public void initialize( int cardID, GSvector2 pos, GSvector2 size, int type ){

		super.initialize(cardID, pos, size, type);

		mAbility1 = Define.CARD_ABILITY.BATTLECRY;
	}

	// プレイ
	public void play(){

		if( mIsPlay ) return;

		mPlayTimer--;

		if( mPlayTimer > 0 ) return;

		mIsPlay = true;

		mSelectCharacter.damage( mAttack );
	}


	// 条件
	public boolean useCondition( GSvector2 mousePos, CharacterBase tactician, boolean isHand  ){

		// 親クラス条件
		if( !super.useCondition(mousePos, tactician, isHand) ) return false;

		if( !condition() ){

			Application.getStringLabel().setType( Define.STRING_TYPE.SELECT_ENEMY );
			Application.getStringLabel().setPos();
			return false;
		}

		return true;
	}

	// 条件
	private boolean condition(){

		// 選択している敵兵士を取得
		mSelectCharacter = getSelectSoldier( false );

		if( mSelectCharacter != null ) return true;

		// 選択している軍師を取得
		mSelectCharacter = getSelectTactician( false );

		return mSelectCharacter != null;
	}
}
