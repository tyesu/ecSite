package model;

import java.sql.SQLException;
import java.util.List;

import dao.AccountDAO;

public class LoginLogic {

	AccountDAO dao = new AccountDAO();

	public boolean execute(Account account) throws ClassNotFoundException, SQLException {
		List<Account> li = dao.findAll();			// データベースの登録アカウントを取得してliに代入

		for(Account a : li) {									// liをaに1つずつ取り出す
			if(account.getPassword().equals(a.getPassword()) &&				// ログイン画面で入力された情報がデータベースに登録された情報が
					account.getUser_mei().equals(a.getUser_mei()) ) {			// 同じだった場合(パスワードとユーザー名)
				System.out.println("true");
				return true;			// trueを返す
			}
		}
		System.out.println("false");
		return false;					// 違った場合falseを返す
	}
}