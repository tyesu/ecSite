package model;

import java.util.ArrayList;
import java.util.List;

public class Account implements java.io.Serializable {


	private String user_mei;		// ユーザー名
	private String password;		// パスワード
	private int money;				// 所持金
	private List<Cake> cartCakes;	// 購入検討中のケーキ
	private List<Cake> boughtCakes;	// 購入したケーキ
	private String search;			// 検索したキーワード

	public Account() {}
	public Account(String user_mei, String password) {
		this.user_mei = user_mei;
		this.password = password;
		this.money = 2000;
		this.cartCakes = new ArrayList<Cake>();
		this.boughtCakes = new ArrayList<Cake>();
	}


	public String getUser_mei() {return this.user_mei;}
	public String getPassword() {return this.password;}
	public int getMoney() {return this.money;}
	public List<Cake> getCartCakes() {return this.cartCakes;}
	public List<Cake> getBoughtCakes() {return this.boughtCakes;}
	public String getSearch() {return this.search;}

	// カートに入れる
	public void setCake(Cake cake) {
		this.cartCakes.add(cake);
	}
	// 検索したキーワードをフィールドに保持する
	public void setSearch(String search) {
		this.search = search;
	}

	// 支払い。所持金が減少
	public void payment(int price) {
		this.money -= price;

		// カートの商品を持ち物に移す
		for (Cake cake: this.cartCakes) {
			this.boughtCakes.add(cake);
		}

		// カートは空に
		this.cartCakes.clear();
	}

}
