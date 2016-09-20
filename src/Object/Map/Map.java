package Object.Map;

import Application.Application;
import Application.Vector2;
import Define.Define;
import Define.DefineMap;
import Define.DefinePlayer;
import Object.Character.CharacterBase;

public class Map extends CharacterBase{

	private MiniMap mMiniMap;
	private CharacterBase mDark;

	// コンストラクタ
	public Map(){

		// ミニマップ生成
		mMiniMap = new MiniMap();

		// 闇
		mDark = new CharacterBase();
	}

	// 初期化
	public void initialize(){

		// 親クラス初期化
		super.initialize( "map", 0, new Vector2(), Define.FIELD_SIZE, DefineMap.MAP_RESIZE, 0);

		// リサイズ幅設定
		mReSizeDistance = new Vector2( Define.FIELD_SIZE.x / DefineMap.MAP_REVISION.x, Define.FIELD_SIZE.y / DefineMap.MAP_REVISION.y );

		// ミニマップ初期化
		mMiniMap.initialize();

		// 闇初期化
		mDark.initialize( "dark", 0, new Vector2(), Define.FIELD_SIZE, DefineMap.DARK_RESIZE, 0);
		mDark.setNotRevision();

	}

	// 更新
	public void update(){

		// プレイヤーの座標
		Vector2 pos = Application.getObj().getPlayerManager().getPlayer( Application.getID() ).getPos();

		// プレイヤーに合わせて移動
		mPos = new Vector2( pos.x - Define.FIELD_SIZE.x / 2 + DefinePlayer.SIZE.x / 2, pos.y - Define.FIELD_SIZE.y / 2 + DefinePlayer.SIZE.y / 2 );

		// リサイズ変更
		mReSize.x = Math.min( Math.max( pos.x + Define.FIELD_SIZE.x, Define.FIELD_SIZE.x ), DefineMap.MAP_SIZE.x + Define.FIELD_SIZE.x ) / DefineMap.MAP_REVISION.x;
		mReSize.y = Math.min( Math.max( pos.y + Define.FIELD_SIZE.y, Define.FIELD_SIZE.y ), DefineMap.MAP_SIZE.y + Define.FIELD_SIZE.y ) / DefineMap.MAP_REVISION.y;
	}

	// ゲッター
	public MiniMap getMiniMap(){ return mMiniMap; }
	public CharacterBase getDark(){ return mDark; }
}
