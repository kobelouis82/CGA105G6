<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer>
	<div class="footer-area wow fadeIn" data-wow-duration=".5s"
		data-wow-delay=".5s">
		<div class="footer-middle">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="row">
							<div class="col-md-4 col-sm-3 col-xs-12">
								<h4>關於我們</h4>
								<ul class="toggle-footer">
									<li><a title="Contact us" href="#">聯絡我們</a></li>
								</ul>
							</div>
							<div class="col-md-4 col-sm-3 col-xs-12">
								<h4>玩家專區</h4>
								<ul class="toggle-footer">
									<li><a title="FQ" href="#">常見問題</a></li>
									<li><a title="SECONDHAND"
										href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp">二手回收服務</a></li>
									<li><a title="FORUM" href="#">討論區</a></li>
									<li><a title="NEWS" href="#">遊戲新聞</a></li>
								</ul>
							</div>
							<div class="col-md-4 col-sm-3 col-xs-12">
								<h4>購物須知</h4>
								<ul class="toggle-footer">
									<li><a title="members"
										href="<%=request.getContextPath()%>/front-end/memLogin/memRegister.jsp?action=register">會員申請</a></li>
									<li><a title="login"
										href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">會員登入</a></li>
									<li><a title="notice"
										href="<%=request.getContextPath()%>/front-end/notice.jsp">購物須知</a></li>
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
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/vendor/jquery-1.12.0.min.js"></script>
<!-- bootstrap js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
<!-- owl.carousel js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/owl.carousel.min.js"></script>
<!-- meanmenu js -->
<script src="/front-end/assets/js/jquery.meanmenu.js"></script>
<!-- countdown js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/countdown.js"></script>
<!-- jquery.nivo.slider.pack js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/jquery.nivo.slider.pack.js"></script>
<!-- jquery-ui.min.js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/jquery-ui.min.js"></script>
<!-- wow js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/wow.min.js"></script>
<!-- plugins js -->
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/plugins.js"></script>
<!-- main js -->
<script src="<%=request.getContextPath()%>/front-end/assets/js/main.js"></script>
<script src="https://kit.fontawesome.com/yourcode.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
                                            function addStandBy() {
                                                setTimeout(addStandByAlert(), 5000);
                                            }

                                            function addStandByAlert() {
                                                const swalWithBootstrapButtons = Swal.mixin({
                                                    customClass: {
                                                        confirmButton: 'btn btn-outline-primary m-5 fs-5',

                                                    },
                                                    buttonsStyling: false
                                                })

                                                swalWithBootstrapButtons.fire({
                                                    position: 'middle',
                                                    icon: 'success',
                                                    title: '商品已放進購物車',
                                                    showConfirmButton: false,
                                                    timer: 1500
                  })
                                            }
</script>
<script type="text/javascript">

var totalPrices = [];
$('.totalPrice').each(function() {
totalPrices.push($(this).text());
});
let total = 0;
for(let i of totalPrices){		
total += Number(i);
}
$('#price123').val(total);

var total1 = $('#price123').val();
if(total1 == null){		
	document.getElementById("displayTotal").innerHTML = "您的購物車還未有商品 " ;
}else{		
	document.getElementById("displayTotal").innerHTML = "小記: NT$" + total1;
}	 
</script>
</script>
</body>
</html>