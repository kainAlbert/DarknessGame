package Object.Detail;

public class DetailStructure {

	public int mCardID;
	public String mName;
	public boolean mIsCreature;
	public int mCost;
	public int mAttack;
	public int mHP;
	public String mExplanation;

	// コンストラクタ
	public DetailStructure( int cardID, String name, boolean isCreature, int cost, int attack, int hp, String explanation ){

		mCardID = cardID;
		mName = name;
		mIsCreature = isCreature;
		mCost = cost;
		mAttack = attack;
		mHP = hp;
		mExplanation = explanation;
	}
}
