package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Cake;
import model.CartCounter;

@WebServlet("/CartIn")
public class CartIn extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定
		request.setCharacterEncoding("UTF-8");
		String cakeId = request.getParameter("cake");

		// セッションから呼ぶ
		HttpSession session = request.getSession();
		List<Cake> cakes = (List<Cake>) session.getAttribute("cakes");
		Account account = (Account) session.getAttribute("Account");

		// リストを走査
		for (Cake c: cakes) {

			// リクエストバラメータと同じ
			if (cakeId.equals(c.getShohin_id())) {

				// カートにいれる
				account.setCake(c);
				session.setAttribute("Account", account);

				// カートの商品、点数を把握
				CartCounter cartCounter =
						(CartCounter) session.getAttribute("cartCounter");
				cartCounter.countUp(c.getShohin_mei());
			}
		}
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"/WEB-INF/CartIn.jsp");
		dispatcher.forward(request, response);
	}

}
