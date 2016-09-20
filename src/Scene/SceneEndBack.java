package Scene;

import Application.Vector2;
import Define.Define;
import Define.DefineScene;
import Object.Character.CharacterBase;

public class SceneEndBack extends CharacterBase{

	// コンストラクタ
	public SceneEndBack(){

		super();
	}

	// 初期化
	public void initialize(){

		super.initialize("endBack", 0, new Vector2(), Define.WINDOW_SIZE, DefineScene.END_RESIZE, 0);
	}
}
