package Object.Treasure;

import Application.Vector2;
import Define.DefineMap;
import Object.Character.CharacterBase;

public class TreasureIcon extends CharacterBase{

	// コンストラクタ
	public TreasureIcon(){

		super();
	}

	// 初期化
	public void initialize( Vector2 pos, int id ){

		super.initialize(
				"treasure",
				id,
				new Vector2( pos.x / DefineMap.MINI_REVISION + DefineMap.MINI_MAP_POS.x, pos.y / DefineMap.MINI_REVISION + DefineMap.MINI_MAP_POS.y ),
				new Vector2( DefineMap.TREASURE_ICON_SIZE.x, DefineMap.TREASURE_ICON_SIZE.y ),
				new Vector2( DefineMap.TREASURE_ICON_RESIZE.x, DefineMap.TREASURE_ICON_RESIZE.y ),
				0 );

		mIsRevision = false;

		mReSize.x = DefineMap.TREASURE_ICON_RESIZE.x * ( id + 1 );
	}

	// 更新
	public void update(){

	}
}
