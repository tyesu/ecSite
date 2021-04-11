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

@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// カート内を初期化
		HttpSession session = request.getSession();
		Account account  = (Account)session.getAttribute("Account");
		List<Cake> cartCakes = account.getCartCakes();
		cartCakes.clear();

	   // セッションスコープに保存
	   session.setAttribute("Account", account);

	   // ホーム画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/HomeScreen.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}