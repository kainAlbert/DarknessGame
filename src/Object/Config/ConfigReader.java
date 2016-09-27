package Object.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigReader {

	public static ConfigStructure readConfig(){

		BufferedReader br = null;

		try{
			File file = new File( "txt/config.csv" );
			br = new BufferedReader(new FileReader(file));

		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}

		if( br == null ) return null;

		String str = "";
		String[] item = null;
		final String PAUSE = ",";
		ConfigStructure config = new ConfigStructure();

		try{

			// ゲーム時間
			str = br.readLine();
			item = str.split(PAUSE);
			config.mGameTime = Integer.parseInt( item[1] ) * 60;

			// アイテム最大数
			str = br.readLine();
			item = str.split(PAUSE);
			config.mMaxItemNum = Integer.parseInt( item[1] );

			// アイテム出現間隔
			str = br.readLine();
			item = str.split(PAUSE);
			config.mItemApperTime = Integer.parseInt( item[1] ) * 60;

			// 壁の数
			str = br.readLine();
			item = str.split(PAUSE);
			config.mWallNum = Integer.parseInt( item[1] );

			// 攻撃キルポイント
			str = br.readLine();
			item = str.split(PAUSE);
			config.mKillPoint = Integer.parseInt( item[1] );

			// バーサーカーキルポイント
			str = br.readLine();
			item = str.split(PAUSE);
			config.mBerserkerKillPoint = Integer.parseInt( item[1] );

			// 攻撃死亡時マイナス
			str = br.readLine();
			item = str.split(PAUSE);
			config.mDeadPoint = Integer.parseInt( item[1] );

			// バーサーカー死亡時マイナス
			str = br.readLine();
			item = str.split(PAUSE);
			config.mBerserkerDeadPoint = Integer.parseInt( item[1] );

			// プレイヤー移動速度
			str = br.readLine();
			item = str.split(PAUSE);
			config.mPlayerSpeed = Double.parseDouble( item[1] );

			// プレイヤー旋回速度
			str = br.readLine();
			item = str.split(PAUSE);
			config.mPlayerAngleSpeed = Double.parseDouble( item[1] );

			// ミニマップ間隔
			str = br.readLine();
			item = str.split(PAUSE);
			config.mMiniMapTime = Integer.parseInt( item[1] ) * 60;

			// バーサーカー条件ポイント
			str = br.readLine();
			item = str.split(PAUSE);
			config.mBerserkerConditionPoint = Integer.parseInt( item[1] );

		}catch( Exception e ){

			e.printStackTrace();
		}
		return config;
	}
}
