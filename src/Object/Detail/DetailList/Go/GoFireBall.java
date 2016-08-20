package Object.Detail.DetailList.Go;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;

public class GoFireBall extends DetailBase{

	// コンストラクタ
	public GoFireBall( boolean isMy ) {

		super( isMy );

		mSelectCharacter = null;
	}

	// プレイ
	public void play(){

		if( mIsPlay ) return;

		mIsPlay = true;

		// タイプ
		Define.CARD_TYPE type = mIsMy ? Define.CARD_TYPE.MYFIELD : Define.CARD_TYPE.ENEMYFIELD;

		// 徐夫人の数だけ威力を上げる
		int revision = Application.getObj().getCardManager( mIsMy ).searchAbilityNum( Define.CARD_ABILITY.SPELL, type );

		mSelectCharacter.damage( mAttack + revision );
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
