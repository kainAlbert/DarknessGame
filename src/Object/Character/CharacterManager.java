package Object.Character;

import java.util.List;

import Application.Define;

public class CharacterManager {

	private CharacterBase mMyTactician;
	private CharacterBase mEnemyTactician;

	// コンストラクタ
	public CharacterManager(){

		mMyTactician = new Tactician( true );
		mEnemyTactician = new Tactician( false );
	}

	// 初期化
	public void initialize(){

		((Tactician)mEnemyTactician).initialize( Define.TACTICIAN_ID.TOTAKU );
	}

	// 軍師設定
	public void setTactician( Define.TACTICIAN_ID id ){

		((Tactician)mMyTactician).initialize( id );
	}

	// 更新
	public void update(){

		mMyTactician.update();
		mEnemyTactician.update();
	}

	// リスト更新
	private void updateList( List<CharacterBase> list ){

		for( int i=0; i<list.size(); i++ ){

			list.get(i).update();

			if( !list.get(i).getIsDead() ) continue;

			list.remove(i);
		}
	}

	// ゲッター
	public CharacterBase getTactician( boolean isMy ){

		return isMy ? mMyTactician : mEnemyTactician;
	}

}
