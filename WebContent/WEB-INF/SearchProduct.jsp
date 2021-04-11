<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Cake, model.Account"%>

<%
List<Cake> cakes = (List<Cake>) session.getAttribute("cakes");
// 検索に入力したキーワードをAccountから取得
Account account = (Account) session.getAttribute("search");
String search = account.getSearch();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>検索結果</title>
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>

	<style> <%-- 内部スタイルシート --%>
	<%@ include file="./SearchProduct.css"%>

	.box {
		display: flex;
  		justify-content:space-around;
	}

	</style>

	<body>
		<header>
			<h1>h2oShop</h1>
			<i class="fas fa-tint"></i>
		</header>
		<nav>
			<div class="genre">
				<h2>ジャンル</h2>
				<a>一覧</a>
			</div>
			<div class="list">
				<ul>
					<li>ファッション・インナー</li>
					<li>ファッション小物</li>
					<li>キッズ・ベビー玩具</li>
					<li>スポーツ・ゴルフ</li>
					<li>家電・テレビ・カメラ</li>
					<li>PC・スマホ・通信</li>
					<li>食品・スイーツ</li>
					<li>ドリンク・お酒</li>
					<li>インテリア・寝具</li>
				</ul>
			</div>
		</nav>
		<main>
			<form action="/h2o/SearchServlet" method="post">
				<input class="search" type="search" name="search" placeholder="キーワードを入力">
				<input class="submit" type="submit" value="検索">
			</form>
			<p>検索ワード:<%= search.strip() %> <%= cakes.size() %>件</p>
			<div class= "box">
			<%-- スクリプトレットの中はCSS(内部、外部スタイルシート)が反映されない。インラインスタイルシートは反映される --%>
			<% for (Cake c : cakes) { %>
		        <div class="result">
				    <img src= "./img/<%=c.getShohin_id() %>.jpg" width= "150" height= "100">
					<p><%= c.getShohin_mei() %></p>
					<p>金額 : <%= c.getHanbai_tanka() %>円</p>
		      		<p>在庫数 : <%= c.getZaikosu() %></p>
					<form action="/h2o/ToItem" method="post">
						<input class="btn" type="submit" value="詳しく見る">
						<input type="hidden" name="select" value="<%= c.getShohin_id() %>">
					</form>
			  	</div>
			<% }; %>
			</div>
			<form action="Login" method="post">
			      	<input class="home" type="submit" value="ホーム画面に戻る">
			</form>
		</main>
		<aside>
			<img src="https://source.unsplash.com/random" alt="unsplash random" width="193" height="300">
		</aside>
		<footer>
			<div class="corporate-container">
				<ul>
					<li>会社概要</li>
					<li>投資家向け情報 [Investor Relations]</li>
					<li>社会的責任[CSR]</li>
					<li>プレスルーム</li>
					<li>採用情報</li>
					<li>個人情報保護方針</li>
				</ul>
			</div>
			<div class="ft-global-container">
				<p class="ri-copyright">
					<font size="-1"> © h2o Group, Inc.</font>
				</p>
			</div>
		</footer>
	</body>
</html>