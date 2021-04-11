package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cake;

public class CakeDAO {


	// 接続情報
	private final String URL = "jdbc:postgresql://localhost:5432/h2oshop";
	private final String USER = "postgres";
	private final String PASS = "ok";


	// 検索したキーワードで部分一致検索を行う
	public List<Cake> findItem(String item) throws SQLException, ClassNotFoundException {

		// 接続を安全に
		Class.forName("org.postgresql.Driver");

		// 接続する
		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		// 命令文の用意。
		StringBuilder sql = new StringBuilder("select * ");
		sql.append("from cake ");
		sql.append("where shohin_mei like ?;");

		// 引数の文字列を埋め込む
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, "%" + item + "%");

		// sql文実行、取得した表の確保。リストへの詰め込み
		ResultSet rs = ps.executeQuery();
		List<Cake> cakes = new ArrayList<Cake>();
		while (rs.next()) {

			// インスタンスにレコードを詰め込む
			Cake cake = new Cake(
					rs.getString("shohin_id"),
					rs.getString("shohin_mei"),
					rs.getInt("hanbai_tanka"),
					rs.getInt("zaikosu"),
					rs.getString("bun")
			);

			// インスタンスをリストに詰め込む
			cakes.add(cake);
		}

		return cakes;
	}


// 検索したキーワードで部分一致検索を行う
	public List<Cake> findAll() throws SQLException, ClassNotFoundException {

		// 接続を安全に
		Class.forName("org.postgresql.Driver");

		// 接続する
		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		// 命令文の用意。
		StringBuilder sql = new StringBuilder("select * ");
		sql.append("from cake ; ");

		// 引数の文字列を埋め込む
		PreparedStatement ps = conn.prepareStatement(sql.toString());

		// sql文実行、取得した表の確保。リストへの詰め込み
		ResultSet rs = ps.executeQuery();
		List<Cake> cakes = new ArrayList<Cake>();
		while (rs.next()) {

			// インスタンスにレコードを詰め込む
			Cake cake = new Cake(
					rs.getString("shohin_id"),
					rs.getString("shohin_mei"),
					rs.getInt("hanbai_tanka"),
					rs.getInt("zaikosu"),
					rs.getString("bun")
			);

			// インスタンスをリストに詰め込む
			cakes.add(cake);
		}

		return cakes;
	}

	// 購入した商品のDBの在庫数を減らすメソッド
	public void ReduceInventory(int items) throws SQLException, ClassNotFoundException {
		// 接続を安全に
		Class.forName("org.postgresql.Driver");

		// 接続する
		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		// 在庫数を指定した数に変更するsql文の用意。
		String Reduce = "update cake set zaikosu = ? where shohin_id = '001';";

		// 引数の文字列を埋め込む
		PreparedStatement ps = conn.prepareStatement(Reduce);
		ps.setInt(1, items);

		// sql文の実行
		ps.executeUpdate();

	}

}