package Object.Detail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Application.Define;
import Object.Detail.DetailList.Go.GoFireBall;
import Object.Detail.DetailList.Go.GoFlames;
import Object.Detail.DetailList.Go.GoIntellect;
import Object.Detail.DetailList.Go.GoJohujin;
import Object.Detail.DetailList.Go.GoLightning;
import Object.Detail.DetailList.Go.GoRikuson;
import Object.Detail.DetailList.Go.GoSekihekiBigFire;
import Object.Detail.DetailList.Go.GoSonsyoko;
import Object.Detail.DetailList.Go.GoSyuyu;
import Object.Detail.DetailList.Go.GoTerror;
import Object.Detail.DetailList.Syoku.SyokuGien;
import Object.Detail.DetailList.Syoku.SyokuGigantic;
import Object.Detail.DetailList.Syoku.SyokuKanginpei;
import Object.Detail.DetailList.Syoku.SyokuKankogo;
import Object.Detail.DetailList.Syoku.SyokuKanu;
import Object.Detail.DetailList.Syoku.SyokuKotyu;
import Object.Detail.DetailList.Syoku.SyokuNature;
import Object.Detail.DetailList.Syoku.SyokuReinforcement;
import Object.Detail.DetailList.Syoku.SyokuRyubi;
import Object.Detail.DetailList.Syoku.SyokuTyohi;

public class DetailReader {

	public static DetailStructure readDetail( int cardID ){

		BufferedReader br = null;

		try{
			File file = new File( "txt/" + Define.CARD_LIST_FILE_NAME + ".txt" );
			br = new BufferedReader(new FileReader(file));

		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}

		// 読み込んで返す
		return readText( br, cardID );
	}

	// テキストファイルの読み込み
	private static DetailStructure readText( BufferedReader br, int cardID ){

		if( br == null ) return null;

		String str = "";
		String[] item = null;
		DetailStructure eStr = null;

		// 同じIDが見つかるまで
		while( str != null ){

			try{
				str = br.readLine();

				item = str.split("\t");

				if( Integer.parseInt(item[0]) == cardID ) break;

				if( Integer.parseInt(item[0]) == -1 ) return null;

			}catch( Exception e ){}

		}

		eStr = new DetailStructure(
				cardID,
				item[1],
				Integer.parseInt(item[2]) == 1,
				Integer.parseInt(item[3]),
				Integer.parseInt(item[4]),
				Integer.parseInt(item[5]),
				item[6].equals("無し") ? "" : item[6]
				);

		return eStr;
	}

	// IDによってDetailを生成
	public static DetailBase getDetail( int cardID, boolean isMy ){



		switch( cardID ){

		case 1:
			return new GoSyuyu( isMy );
		case 2:
			return new GoRikuson( isMy );
		case 3:
			return new GoSonsyoko( isMy );
		case 4:
			return new GoJohujin( isMy );
		case 5:
			return new GoLightning( isMy );
		case 6:
			return new GoFireBall( isMy );
		case 7:
			return new GoFlames( isMy );
		case 8:
			return new GoSekihekiBigFire( isMy );
		case 9:
			return new GoIntellect( isMy );
		case 10:
			return new GoTerror( isMy );
		case 11:
			return new SyokuRyubi( isMy );
		case 12:
			return new SyokuKanu( isMy );
		case 13:
			return new SyokuTyohi( isMy );
		case 14:
			return new SyokuKotyu( isMy );
		case 15:
			return new SyokuGien( isMy );
		case 16:
			return new SyokuKanginpei( isMy );
		case 17:
			return new SyokuKankogo( isMy );
		case 18:
			return new SyokuReinforcement( isMy );
		case 19:
			return new SyokuGigantic( isMy );
		case 20:
			return new SyokuNature( isMy );
		default:
			return new DetailBase( isMy );
		}
	}
}
