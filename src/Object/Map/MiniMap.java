package Object.Map;

import Define.DefineMap;
import Object.Character.CharacterBase;

public class MiniMap extends CharacterBase{

	// コンストラクタ
	public MiniMap(){

		super();
	}

	// 初期化
	public void initialize(){

		super.initialize(
				"miniMap",
				0,
				DefineMap.MINI_MAP_POS,
				DefineMap.MINI_MAP_SIZE,
				DefineMap.MINI_MAP_RESIZE,
				0);

		mIsRevision = false;
	}
}
