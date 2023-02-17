<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/header.jsp"%>


<style>
.picture {
	height: 500px;
	/* 	  background-color : green; */
	/* 	border: 1px solid black; */
}

.info {
	height: 500px;
	/* 	background-color: blue; */
}

.singleimg {
	max-height: 80%;
	margin: 50px 5px;
}

.itemName {
	margin: 30px;
	font-size: 25px;
	font-weight: bold;
}

.itemPrice {
	font-size: 25px;
	margin: 30px;
	font-weight: bold;
}

.form-group {
	/* 	width: 30%; */
	margin: 30px;
}

.breadcrumb {
	padding: 8px 15px;
	margin-bottom: 20px;
	list-style: none;
	background-color: rgba(0, 0, 0, 0.1);
	border-radius: 4px;
}

.itemNumber {
	font-size: 25px;
	margin-bottom: 30px
}

.shopping {
	margin: 30px;
}

img {
	width: 150px;
	height: 150px;
}

body {
	width: 100% !important;
	overflow-x: hidden !important;
}
</style>


<!-- shop-area-start -->
<div class="shop-area">
	<div class="container">
		<div class="row">
			<div class=" col-xs-12">
				<div class="shop-right-col wow fadeIn" data-wow-duration=".5s"
					data-wow-delay=".5s">
					<div class="category-products">
						<div class="shop-category-product">
							<div class="row">
								<div class="category-product">
									<!-- Tab panes -->
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active fade in"
											id="gried_view">

											<div class="card shadow mb-4">
												<div class="card-body">
													<div class="table-responsive">
														<table class="table table-bordered" id="dataTable"
															width="100%" cellspacing="0">
															<thead class="thead-dark">

																<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderServlet.do">
																	<b>輸入訂單編號 :</b> <input type="text" name="orderNo">
																	<input type="hidden" name="action"
																		value="getOne_For_Front_Display"> <input
																		type="submit" value="送出">
																</FORM>
																
																<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderServlet.do">
																	<input type="hidden" name="memNo"
																		value="${sessionScope.memNo} "> <input
																		type="hidden" name="action"
																		value="getOne_For_MemNo_Front_Display"> <input
																		type="submit" value="查詢">
																</FORM>
														</table>
													</div>
												</div>
											</div>

										</div>
										<%@ include file="/front-end/footer.jsp"%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
