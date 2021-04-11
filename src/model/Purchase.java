//package model;
//
//import java.util.HashMap;
//import java.util.Set;
//
//public class Purchase {
//
//	// field
//
//	// 処理対象
//	private Account target;
//	// カートの状況
//	private HashMap<String, Integer> cartData;
//
//	// constructor
//
//	public Purchase(Account account, HashMap<String, Integer> cart) {
//		this.cartData = new HashMap<String, Integer>();
//		this.cartData = cart;
//		this.target = account;
//	}
//
//	// getter
//
//	public Account getAccount() {return this.target;}
//	public HashMap<String, Integer> getCartData() {return this.cartData;}
//
//	// method
//
//	// 購入処理（カート商品）
//	public boolean transaction() {
//
//		// 変数準備
//		int total = this.target.getTotal();		// 購入金額
//		int money = this.target.getMoney();		// ユーザー所持金
//		Set<String> cakes = this.cartData.keySet();
//
//
//		// 購入できるなら
//		if (total <= money) {
//
//			// 支払い処理
//			this.target.setMoney(money - total);
//
//			// カート商品、購入商品を更新
//			this.target.setBoughtCakes();
//			this.target.resetCartCakes();
//
//			return true;
//		}
//
//		// 残高が足りていないなら購入失敗
//		return false;
//	}
//}