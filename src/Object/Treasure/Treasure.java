package Object.Treasure;

import Application.Vector2;
import Define.DefineMap;
import Define.DefineTreasure;
import Object.Character.CharacterBase;

public class Treasure extends CharacterBase{

	private TreasureIcon mIcon;
	private int mTreasureID;
	private int mFactoryID;

	// コンストラクタ
	public Treasure(){

		super();

		// アイコン生成
		mIcon = new TreasureIcon();
	}

	// 初期化
	public void initialize( Vector2 pos, int id, int treasureID, int factoryID ){

		// 親クラス初期化
		super.initialize(
				"treasure",
				id,
				pos,
				DefineTreasure.SIZE,
				DefineTreasure.RESIZE,
				0);

		// リサイズ変更
		mReSize.x = DefineMap.TREASURE_ICON_RESIZE.x * ( id + 1 );

		// アイコン初期化
		mIcon.initialize( pos, id );

		mTreasureID = treasureID;
		mFactoryID = factoryID;
	}

	// ゲッター
	public TreasureIcon getIcon(){ return mIcon; }
	public int getTreasureID(){ return mTreasureID; }
	public int getFactoryID(){ return mFactoryID; }
}
