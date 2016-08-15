package Object.Detail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Application.Define;

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
				item[6]
				);

		return eStr;
	}
}