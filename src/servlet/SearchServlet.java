package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CakeDAO;
import model.Account;
import model.Cake;

// 検索機能を提供する
// 検索キーワードを使用してＤＢから商品データを取得する
// 取得した商品データをスコープへ

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//エンコ設定、検索ワードの受け取り等

		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		HttpSession session = request.getSession();

		// Accountにキーワードを登録
		Account account = new Account();
		account.setSearch(search);
		session.setAttribute("search", account); //リクエストスコープでも可

		// 商品をDBから呼び込む
		CakeDAO dao = new CakeDAO();
		List<Cake> cakes = null;

		// 部分一致でヒットしたものだけ取得
		try {
			// 検索で入力したキーワードが空白か判定
			if(search.strip().length() == 0) { // 入力がない場合ははじく
				cakes = dao.findItem(null);
			} else {
			cakes = dao.findItem(search.strip());
			}
			session.setAttribute("cakes", cakes);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 商品検索画面へ

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"WEB-INF/SearchProduct.jsp"
		);
		dispatcher.forward(request, response);
	}

}