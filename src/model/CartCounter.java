package model;

import java.util.HashMap;

// カートの中の商品を把握する（商品名とその個数）

public class CartCounter {

	// field

	// key= 商品名  value= 在庫数
	private HashMap<String, Integer> counter;

	// constructor

	public CartCounter() {
		this.counter = new HashMap<String, Integer>();
	}

	// method

	// 現在のカートの商品、商品数を取得
	public HashMap<String, Integer> getCounter() {return this.counter;}

	// カートの商品数をｶｳﾝﾄｱｯﾌﾟする
	public void countUp(String name) {

		boolean bool = this.counter.containsKey(name);

		// 引数がマップに登録されていないなら
		if (bool == false) {

			// 商品xxxxが１個として登録
			this.counter.put(name, 1);

		// 引数にマップが登録されている場合
		} else {

			// 現在のカートの商品数を取得
			int count = this.counter.get(name);
			count += 1; // 同じ商品の数を1つ増やす
			this.counter.put(name, count);
		}
	}
}