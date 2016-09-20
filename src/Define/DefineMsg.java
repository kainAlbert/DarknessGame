package Define;

public interface DefineMsg {

	// 接続詞
	String MSG = "___";

	// プレイヤー情報
	String PLAYER_INFO = "PlayerInfo";

	// 攻撃エフェクト
	String ATTACK_EFFECT = "AttackEffect";

	// 攻撃判定
	String COLLISION_ATTACK = "CollisionAttack";

	// 壁移動
	String MOVE_WALL = "MoveWall";

	// 宝箱作成
	String CREATE_TREASURE = "CreateTreasure";

	// 宝箱削除
	String REMOVE_TREASURE = "RemoveTreasure";

	// ゲーム時間
	String GAME_TIME = "GameTime";

	// エントリー
	String ENTRY = "Entry";

	// 次のシーンへ
	String NEXT_SCENE = "NextScene";
}
