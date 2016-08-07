package Object.Character;

import java.util.List;

import Application.Application;
import Application.Panel;

public class CharacterManager {

	// コンストラクタ
	public CharacterManager( Application app, Panel p ){

		// リストを生成

	}

	// 初期化
	public void initialize( Application app, Panel p ){

	}

	// 更新
	public void update(){

	}

	// リスト更新
	private void updateList( List<CharacterBase> list ){

		for( int i=0; i<list.size(); i++ ){

			list.get(i).update();

			if( !list.get(i).getIsDead() ) continue;

			list.remove(i);
		}
	}

}
