package Object.Effect;

import java.util.ArrayList;
import java.util.List;

import Object.Character.CharacterBase;

public class EffectManager {

	private List<CharacterBase> mEffectList;

	// コンストラクタ
	public EffectManager(){

		mEffectList = new ArrayList<CharacterBase>();

		// 各エフェクト生成
	}

	// 更新
	public void update(){

		for( int i=0; i<mEffectList.size(); i++ ){

			mEffectList.get(i).update();

			// 死亡処理
			if( !mEffectList.get(i).getIsDead() ) continue;

			mEffectList.remove(i);
		}
	}

	// リストに追加
	public void addEffectList( CharacterBase e ){ mEffectList.add( e ); }

	// ゲッター
	public List<CharacterBase> getEffectList(){ return mEffectList; }
}
