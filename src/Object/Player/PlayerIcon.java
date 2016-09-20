package Object.Player;

import Application.Application;
import Application.Vector2;
import Define.DefineMap;
import Object.Character.CharacterBase;

public class PlayerIcon extends CharacterBase{

	// コンストラクタ
	public PlayerIcon(){

		super();
	}

	// 初期化
	public void initialize( int id ){

		super.initialize( "playerIcon", id, new Vector2(), DefineMap.PLAYER_ICON_SIZE, DefineMap.PLAYER_ICON_RESIZE, 0 );

		mIsRevision = false;
	}

	// 更新
	public void update(){

		Vector2 pos = Application.getObj().getPlayerManager().getPlayer( mID ).getPos();

		mPos.x = pos.x / DefineMap.MINI_REVISION + DefineMap.MINI_MAP_POS.x;
		mPos.y = pos.y / DefineMap.MINI_REVISION + DefineMap.MINI_MAP_POS.y;

	}
}
