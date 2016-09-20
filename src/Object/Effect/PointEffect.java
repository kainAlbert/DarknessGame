package Object.Effect;

import Application.Application;
import Application.Vector2;
import Define.DefineEffect;
import Define.DefineScene;
import Object.Character.CharacterBase;
import Object.Player.Player;

public class PointEffect extends CharacterBase{

	// コンストラクタ
	public PointEffect(){

		super();
	}

	// 初期化
	public void initialize( int id ){

		// 親クラス初期化
		super.initialize(
				"point",
				id,
				new Vector2(),
				DefineEffect.POINT_SIZE,
				DefineEffect.POINT_RESIZE,
				0);

		mPos = new Vector2( DefineEffect.POINT_POS.x, DefineEffect.POINT_POS.y + ( id - 1 ) * DefineEffect.POINT_DISTANCE );
		mIsRevision = false;
		mReSize.y = id * mReSizeDistance.y;
	}

	// 更新
	public void update(){

		// プレイヤー取得
		Player player = Application.getObj().getPlayerManager().getPlayer( mID );

		mReSize.x = mReSizeDistance.x * Math.min( player.getPoint() + 1, 30 );

		// 終了画面での座標
		if( Application.getScene().getScene() != DefineScene.GAME_END ) return;

		mPos = new Vector2( DefineEffect.POINT_END_POS.x + ( mID - 1 ) * DefineEffect.POINT_END_DISTANCE, DefineEffect.POINT_END_POS.y );
	}

}
