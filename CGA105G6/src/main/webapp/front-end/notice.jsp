<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shpe.model.*"%>
   
<html>
    <head>
        <meta charset="utf-8">
        <title>FUN購物</title>
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Lato:400,300,700,900,100' rel='stylesheet' type='text/css'>

		<!-- Bootstrap CSS onlineCDN -->
		<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
		integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
		crossorigin="anonymous">

        <!-- Bootstrap CSS -->
        <link
	         href="<%=request.getContextPath()%>/front-end/assets/css/bootstrap.min.css"
	         rel="stylesheet" type="text/css">
	     <link
	         href="<%=request.getContextPath()%>/front-end/assets/css/font-awesome.min.css"
	         rel="stylesheet" type="text/css">
	     <link 
			　href="<%=request.getContextPath()%>/front-end/assets/css/bookstrapchange.css"
			　rel="stylesheet" type="text/css">
        <!-- Custom CSS -->
        <link 
            href="<%=request.getContextPath()%>/front-end/assets/css/style.css" 
            rel="stylesheet" type="text/css">

    </head>
    <body>
    <!-- header-start -->
        <header>
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-xs-12 col-sm-12">
                            <div class="header-top-left">
                                <div class="welcome-msg">
                                        <span class="default-msg hidden-xs">
                                            <img class="default-msg hidden-xs"src="<%=request.getContextPath()%>/front-end/assets/img/icon/logo_small.png">FUN電玩
                                        </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-12">
                            <div class="header-top-right">
                                <ul class="header-links hidden-xs">
                                    <li><a class="my-account" href="#" ><img class="btn-header" src="<%=request.getContextPath()%>/front-end/assets/img/icon/user.png" alt="會員專區" title="會員專區"></a></li>
                                    <li><a class="my-wishlist" href="#"><img class="btn-header" src="<%=request.getContextPath()%>/front-end/assets/img/icon/wish_list.png" alt="我的收藏" title="我的收藏"></a></li>
                                    <li><a class="checkout" href="#"><img class="btn-header" src="<%=request.getContextPath()%>/front-end/assets/img/icon/shopping_cart_y.png" alt="購物車" title="購物車"></a></li>
                                    <li><a class="login" href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp"><img class="btn-header" src="assets/img/icon/login.png" alt="我要登入" title="我要登入"></a>
                                    <ul>
                                        <li>
                                            <a class="logout" href="#"><img class="btn-header" src="<%=request.getContextPath()%>/front-end/assets/img/icon/logout.png" alt="我要登出" title="登出"></a>
                                        </li>
                                    </ul>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="header-middle">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-sm-12 col-xs-8">
                            <div class="logo">
                                <a href="<%=request.getContextPath()%>/front-end/main.jsp"><img class="logo"src="<%=request.getContextPath()%>/front-end/assets/img/icon/funplaylogo.png" alt="FUN電玩" title="FUN電玩" />
                                </a>
                            </div>
                        </div>
                        <div class="col-md-5 col-sm-6 hidden-xs">
                            <div class="search-box">
                                <form action="#">
                                    <input class="form-control search-form" type="text" placeholder="請輸入想要搜尋的商品或遊戲" />
                                    <button class="search-button" value="Search" type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-4 col-sm-6">
                            <div class="header-btn-bar">
                                <div class="shopping-cart">
                                <a href="#" class="btn-shopping-cart">
                                    <img src="<%=request.getContextPath()%>/front-end/assets/img/icon/shopping_cart.png" class="pic-shopping-cart"> 
                                    </a>
                                <div class="top-cart-content">
                                    <div class="media header-middle-checkout">
                                        <div class="media-left check-img">
                                            <a href="#"><img src="<%=request.getContextPath()%>/front-end/assets/img/icon/cart/1.jpg" alt="" /></a>
                                        </div>
                                        <div class="media-body checkout-content">
                                            <h4 class="media-heading">
                                                    <span class="cart-count">2x</span>
                                                    <a href="#">Jacket</a>
                                                    <span class="btn-remove checkout-remove" title="remove this product from my cart"><i class="fa fa-times" aria-hidden="true"></i></span>
                                                </h4>
                                            <p>£ 78.15</p>
                                        </div>
                                    </div>
                                    <div class="media header-middle-checkout last-child">
                                        <div class="media-left check-img">
                                            <a href="#"><img src="<%=request.getContextPath()%>/front-end/assets/img/icon/cart/2.jpg" alt="" /></a>
                                        </div>
                                        <div class="media-body checkout-content">
                                            <h4 class="media-heading">
                                                    <span class="cart-count">1x</span>
                                                    <a href="#">Jacket</a>
                                                    <span class="btn-remove checkout-remove" title="remove this product from my cart"><i class="fa fa-times" aria-hidden="true"></i></span>
                                                </h4>
                                            <p>£ 120.85</p>
                                        </div>
                                    </div>
                                    <div class="cart-total">
                                        <span>Total</span>
                                        <span><b>£ 199.00</b></span>
                                    </div>
                                    <div class="checkout">
                                        <a href="#"><span>checkout<i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i></span></a>
                                    </div>
                                </div>
                                </div>
                                <a href="#" class="btn-my-order">
                                <img src="<%=request.getContextPath()%>/front-end/assets/img/icon/list.png" title="我的訂單" class="pic-my-order">
                                </a>
                                <a href="#" class="btn-support">
                                <img src="<%=request.getContextPath()%>/front-end/assets/img/icon/support.png" title="客服中心" class="pic-support">
                                </a>
                                 <a href="#" class="btn-affiche">
                                <img src="<%=request.getContextPath()%>/front-end/assets/img/icon/affiche.png" title="公告" class="pic-affiche">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main-menu-area hidden-xs hidden-sm">
                <div class="container">
                    <div class="row">
                        <div class="colo-md-12">
                            <div class="main-menu">
                                <ul class="drop-down-menu">
                                    <li>
                                        <a href="#">最新消息</a>
                                    </li>
                                    <li>
                                        <a href="#">商品預購</a>
                                        <ul>
                                        <li>
                                            <a href="#">PS專區</a>
                                            </li>
                                        <li>
                                            <a href="#">NS專區</a>
                                            </li>
                                        <li>
                                            <a href="#">XBOX專區</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="#">商品列表</a>
                                        <ul>
                                             <li><a href="#">PS專區</a>
                                             <ul>
                                                 <li><a href="#">PS5遊戲</a></li>
                                                 <li><a href="#">PS4遊戲</a></li>
                                                 <li><a href="#">PS主機</a></li>
                                             </ul>
                                             </li>
                                             <li><a href="#">NS專區</a>
                                             <ul>
                                                 <li><a href="#">NS遊戲</a></li>
                                                 <li><a href="#">NS主機</a></li>
                                             </ul>
                                             </li>
                                             <li><a href="#">XBOX專區</a>
                                             <ul>
                                                 <li><a href="#">XBOX遊戲</a></li>
                                                 <li><a href="#">XBOX主機</a></li>
                                             </ul>
                                             </li>
                                             <li><a href="<%=request.getContextPath()%>/front-end/secondhandshop/secondHandShop.jsp">二手專區</a></li>
                                         </ul>
                                    </li>
                                        <li>                                       
                                    <li>   
                                        <a href="#" class="drop-down-menu">玩家專區</a>
                                            <ul>
                                            <li>
                                                <a href="#" class="drop-down-menu">遊戲討論區</a>
                                            </li>
                                            <li>
                                                <a href="#" class="drop-down-menu">遊戲新聞</a>
                                            </ul>
                                            </li>
                                    <li>
                                        <a href="#" class="drop-down-menu">二手回收專區</a>
                                           <ul>
                                           <li>
                                           <a href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp" class="drop-down-menu">二手估價</a></li>
                                           <li>
                                           <a href="<%=request.getContextPath()%>/front-end/secondhandrecycle/SHRSListAll.jsp" class="drop-down-menu">二手估價進度查詢</a></li>
                                           </ul>
                                    </li>
                                    <li>
                                        <a href="#">聯絡我們</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- header-end -->
        <!-- heading-banner-start -->
          <div class="heading-banner">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-xs-12">
                        <div class="breadcrumb">
                            <a title="Return to Home" href="<%=request.getContextPath()%>/front-end/index.jsp">
                                <i class="icon-home"></i>
                            </a>
                            <span class="navigation-page">
                                <span class="navigation-pipe">></span>
                                購買須知
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- heading-banner-end -->
        <!-- center block -->
         <div class="center-block">
            <div class="container">
        		<div class="row">
					</div>
					<div class="section-heading">
					<p></p>
                            <h3>購買須知</h3>
                    <p></p>
                    <p>&nbsp;</p>
                        </div>
					<div class="col 8">
						<div class="text_editor_box">
						<h1><strong>【預購商品】</strong></h1>
							<p>商品為預購 / 預約商品，本館保有訂單審查權，如內容、價格、日期有異，將溝通合議方式確認訂單。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【無法出貨】</strong></h1>
							<p>訂單中商品如遇到以下情況，本館有權拒絕履行並取消交易契約：</p>
							<p>１．代理商通知訂單中的商品「<strong>取消發售</strong>」之情形，將取消訂單並。</p>
							<p>２．代理商通知訂單中的商品「<strong>發行量不足</strong>」之情形，將依下單時間排序出貨，數量不足以供應部分將取消訂單。</p>
							<p>３．訂單中的商品「<strong>無庫存</strong>」之情形，將取消訂單。</p>
							<p>４．訂單中的商品售價有「<strong>錯誤瑕疵</strong>」之情形，將取消訂單。</p>
							<p>&nbsp;</p>
							<p>如有造成不便，還請見諒！謝謝配合。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【預估價】</strong></h1>
							<p>商品資訊未明時，商品價格為「預估售價」，為保障您的權益請詳閱以下說明：&nbsp;</p>
							<p>１．「預估售價」為原產地售價經台幣匯率換算及加成後之「暫定價格」。&nbsp;</p>
							<p>２．待代理商公佈官方建議售價後，將以官方建議售價為主並做同步調整。&nbsp;</p>
							<p>３．價格異動的差額採「多退少不補」方式，恕不退還現金，<strong>信用卡付款</strong>如遇到預收金額超過實際金額，<br />
　　								會以購物金的方式退回到個人帳號，購物金如同現金可在下次購買使用，沒有使用期限和金額限制，<br />
　　								使用後若取消訂單系統不會主動復原，但可來訊告知並人工補發，請謹慎使用。</p>
							<p>４．.「預估售價」如遇匯率波動幅度過大，導致成本高於正式定價，本館將會拒絕履行並取消訂單，請客戶再另外重新建立新訂單。</p>
							<p>&nbsp;</p>
							<p>如有造成不便，還請見諒！謝謝配合。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【付款方式】</strong></h1>
							<p><strong>．門市取貨</strong></p>
							<p>商品選購完並選擇北中南部任一門市，可直接到店或等門市通知，可刷卡或付現金取貨；優點-免運費，取貨時間較無限制，拿到實體商品再付款！</p>
							<p>&nbsp;</p>
							<p>※請注意！商品是優先由中央倉庫統一調配出貨，而非指定門市出貨，故需約<strong>1～７工作天</strong>，待指定門市收到訂單後，會再簡訊或電話通知取貨。<br />
							<strong>例外情況：指定門市有訂單中的現貨庫存且可出貨。則不須經由中央倉庫，直接委由門市出貨，這過程就不用等待囉！</strong></p>
							<h2>&nbsp;</h2>
							<h2><strong>例外情況如下：</strong></h2>
							<p>主機類：１．凡有特別標示『【門市取貨付款】訂單無效』，因成本計算不同，該主機方案門市不一定會跟進販售，<br />
　　　　　　					如經下單，會代為詢問門市確認是否同意跟進方案。<br />
　　　　							２．凡有特別標示『【門市取貨付款】訂單無效』，但該主機方案為<strong>【官網限定】</strong>，門市不跟進販售<br />
　　　　　　					如經下單，得直接取消訂單。</p>
							<p>&nbsp;</p>
							<p>軟體類：１．凡有特別標示『【門市取貨付款】需繳納訂金』，如經下單，門市會通知客人到場繳納訂金後並受理此筆訂單。<br />
　　　　							２．【限定 / 限量商品】凡有特別標示『本賣場商品僅限【線上付款】，【門市取貨付款】訂單無效』，<br />
　　　　　　							如經下單，會代為詢問門市確認是否有額量可交付，並考量是否需繳納訂金，訂單始有效。</p>
							<p>&nbsp;</p>
							<p>其他類：凡有特別標示『【門市取貨付款】需繳納訂金』，該商品須到門市繳納訂金後得受理此筆訂單。</p>
							<p>&nbsp;</p>
							<p><strong>．信用卡線上付款</strong><br />
							線上即時刷卡方式進行交易，作業流程將透過SSL 加密機制，保障您的個人隱私資料。</p>
							<p>如遇到預收金額超過實際金額，會以購物金的方式退回到個人帳號，購物金如同現金可在下次購買使用，沒有使用期限和金額限制，<br />
							使用後若取消訂單系統不會主動復原，但可來訊告知並人工補發，請謹慎使用。</p>
							<p>&nbsp;</p>
							<p><strong>．宅配貨到付款</strong></p>
							<p>商品將宅配到指定地點，收件後再以現金或信用卡付款。</p>
							<p>&nbsp;</p>
							<p><strong>．ATM轉帳付款</strong></p>
							<p>訂單成立後系統將產生匯款帳號，將款項透過實體（網路）ATM匯入帳號即完成付款。</p>
							<p>&nbsp;</p>
							<p><strong>．超商付款取貨（即將開放）</strong></p>
							<p>預訂商品直接配送到指定便利商店，付款與取貨超彈性適合不便收貨或沒有信用卡、無法匯款的客群。</p>
							<p>此預購商品目前開放「信用卡線上付款」、「門市取貨付款」，預購不須先支付商品額外預收訂金。待商品到門市後，<br />
								本館將視商品數量以及訂單排序通知，選擇「門市取貨付款」者會接獲該門市該筆訂單通知，再請到門市完成後續付款與取貨動作，敬請安心下訂。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【拒收已出貨之訂單】</strong></h1>
							<p>購買商品，訂單經下訂後，俟訂單轉為「已出貨」狀態時<br />
								客戶將不得無故拒絕取貨。</p>
							<p>&nbsp;</p>
							<p>否則將被網站列為黑名單客戶，拒絕任何一切線上交易（仍可自行到門市購買）。<br />
								如確定不欲領收該商品，請依退貨管道（我可以退貨嗎？）諮詢</p>
							<p>&nbsp;</p>
							<p>如有造成不便，還請見諒！謝謝配合。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【回收卡 / 回收貼紙】</strong></h1>
							<p>Ｑ：甚麼是回收卡 / 回收貼紙？</p>
							<p>Ａ：凡於本館任一網路購物平台或實體門市購買遊戲軟體，均會隨附回收卡或貼紙*。</p>
							<p>　　※請注意！活動搭售或搭贈或特價及特殊商品不隨附回收卡。</p>
							<p>&nbsp;</p>
							<p>Ｑ：回收卡 / 回收貼紙有甚麼用途？</p>
							<p>Ａ：當該遊戲不玩想販售二手時，可於本館門市或線上LINE@進行回收 服務，憑此回收卡可保證回收，回收價折數多算一折。</p>
							<p>ex：PS4《碧血狂殺 2》原價1690 元，販售當天的時價*為1290元。<br />
　　								有、無回收卡的回收價計算如下：<br />
　								【無】回收卡，1290 &times; 0.4 = 516 元<br />
　								【有】回收卡，1290 &times; 0.5&nbsp;= 645 元<br />
　								※如有會員卡，以上兩式會再多算一折。</p>
							<p>&nbsp;</p>
							<p>Ｑ：為何我購買的商品沒有附回收卡 / 回收貼紙？</p>
							<p>Ａ：購買商品如符合以下條件，將不附回收卡 / 回收貼紙。<br />
　　								．活動優惠特價之商品<br />
　　								．贈品<br />
　　								．方案搭售之商品<br />
　　								．使用【折扣金優惠券】之商品<br />
　　								．一次性商品，ex：序號下載版 / 序號經使用無法恢復等&hellip;&hellip;（如有附上一概不回收）<br />
　　								．特殊類型商品，ex：網路連線遊戲 / 耗材型遊戲等&hellip;&hellip;<br />
　　							</p>
							<p>※回收卡　：網購通路提供，會提供一張小卡，貼有產品編號；未免遺失，請收納在盒內保存，恕不補發。<br />
						   	   ※回收貼紙：實體門市提供，會貼在軟體外盒上。<br />
                               ※時　　價：回收當日被販售商品的本館所訂之全新品售價。<br />
                               ※無回收卡：依被販售商品之性質、市場需求、庫存，由本館自行判斷是否回收，調整報價基準。</p>
							<p>&nbsp;</p>
						<p>------------------------------------------------------------------------------------------------------------</p>
						<h1><strong>【退貨須知】</strong></h1>
							<p>依照消費者保護法規定，消費者享有商品<strong>到貨七天猶豫期</strong>之權益。</p>
							<p>但退回商品必須是<strong>全新狀態且包裝完整</strong>（保持商品、附件、包裝、廠商紙箱及所有附隨文件或資料之完整性），否則恕不接受退訂。</p>
							<p><strong>已拆封</strong>之電腦軟體、程式、錄音帶及錄影帶、CD、VCD、DVD、食品、花卉商品、衣服、包包及耗材，以及商品網頁上特別載明之商品，均不接受退訂。</p>
							<p>&nbsp;</p>
							<p>如有造成不便，還請見諒！謝謝配合。</p>
							<p>
							</p>
					</div>
				</div>
			</div>
		</div>
		<!-- notice --> 
<footer>
            <div class="footer-area wow fadeIn" data-wow-duration=".5s" data-wow-delay=".5s">
                <div class="footer-middle">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="row">
                                    <div class="col-md-4 col-sm-3 col-xs-12">
                                        <h4>關於我們</h4>
                                        <ul class="toggle-footer">
                                            <li><a title="Contact us" href="#">聯絡我們</a></li>
                                       <!--     <li><a title="Best sellers" href="#"></a></li>
                                            <li><a title="Our stores" href="#"></a></li> --> 
                                        </ul>
                                    </div>
                                    <div class="col-md-4 col-sm-3 col-xs-12">
                                        <h4>玩家專區</h4>
                                        <ul class="toggle-footer">
                                            <li><a title="FQ" href="#">常見問題</a></li>
                                            <li><a title="SECONDHAND" href="#">二手回收服務</a></li>
                                            <li><a title="FORUM" href="#">討論區</a></li>
                                            <li><a title="NEWS" href="#">遊戲新聞</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4 col-sm-3 col-xs-12">
                                        <h4>購物須知</h4>
                                        <ul class="toggle-footer">
                                            <li><a title="members" href="#">會員申請</a></li>
                                            <li><a title="login" href="#">會員登入</a></li>
                                            <li><a title="notice" href="<%=request.getContextPath()%>/front-end/notice.jsp">購物須知</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <div class="footer-bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12 address">
                                Copyright &copy; 2023.Company name All rights reserved.<a>CGA105</a>
                            </div>
                        </div>
                    </div>
                </div>    
             </div> 
          </div>
       </div>       
        </footer>
        <!-- footer-end -->
        
        <!-- all js here -->
        <!-- jquery latest version -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/vendor/jquery-1.12.0.min.js"></script>
        <!-- bootstrap js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
        <!-- owl.carousel js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/owl.carousel.min.js"></script>
        <!-- meanmenu js -->
        <script type="text/javascript" src="/assets/js/jquery.meanmenu.js"></script>
        <!-- countdown js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/countdown.js"></script>
        <!-- jquery.nivo.slider.pack js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/jquery.nivo.slider.pack.js"></script>
        <!-- jquery-ui.min.js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/jquery-ui.min.js"></script>
        <!-- wow js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/wow.min.js"></script>
        <!-- plugins js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/plugins.js"></script>
        <!-- main js -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/assets/js/main.js"></script>
        <script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script>
    </body>
</html>
