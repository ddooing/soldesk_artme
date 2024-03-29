<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>ARTMEE</title>
	<link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />

	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
		rel="stylesheet" />
	<link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
		rel="stylesheet" />

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

	<!-- JQuery 자바스크립트-->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


	<!-- CSS -->
	<link href="css/styles.css" rel="stylesheet" />

	<!-- Carousel 자바스크립트-->
	<script src='https://www.gmarwaha.com/script/lib/jquery-1.11.1.js'></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing-1.3.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing.compatibility.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.mousewheel-3.1.12.js"></script>
	<script src="https://www.gmarwaha.com/jquery/jcarousellite/script/jquery.jcarousellite.js"></script>

	<style>
		#jcl-demo {
			text-align: center;
		}

		.custom-container.main,
		.custom-container.main1,
		.custom-container.main2 {
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.prev,
		.next,
		.prev1,
		.next1,
		.prev2,
		.next2 {
			margin: 0 10px;
		}

		.carousel ul li,
		.carousel1 ul li {
			margin-right: 10px;
		}

		.carousel ul li:last-child,
		.carousel1 ul li:last-child {
			margin-right: 0;
		}

		.carousel img,
		.carousel1 img {
			max-width: 100%;
		}

		section#scroll *,
		section#scroll *::before,
		section#scroll *::after {
			box-sizing: content-box;
		}

		.divider:after,
		.divider:before {
			content: "";
			flex: 1;
			height: 1px;
			background: #eee;
		}

		.form-check-input[type="checkbox"] {
			background-color: #fff;
			border: 1px solid #000;
			border-radius: 4px;
		}

		.form-check-input[type="checkbox"]:checked {
			background-color: #000;
			border: 1px solid #000;
			color: #fff;
		}
	</style>

</head>

<body id="page-top">
	<!-- 메뉴바 -->
	<c:import url="/WEB-INF/views/include/header.jsp"/>


	<!--로그인 부분-->
	<section>
	    <div class="container py-5 h-100" style="margin-top:100px;">
	        <div class="row d-flex align-items-center justify-content-center h-100">
	            <div class="col-md-12 col-lg-5 col-xl-5">
					<div class="text-center mb-4">
						<img src="../img/ARTMEE.png" style="width: 250px; height: 100px;">
					</div>
					
					<form:form action="${root }/user/login_pro" method="post" modelAttribute="tempLoginUserBean">
						<div class="form-outline mb-4" style="margin-top:50px;">
							<input name="id" class="form-control form-control-lg" placeholder="아이디" />
						</div>
						
						<div class="form-outline mb-4">
							<input type="password" name="password" class="form-control form-control-lg" placeholder="비밀번호" />
						</div>
						
						<form:button type="submit" class="btn btn-primary btn-lg btn-block w-100" style="background-color: #000; color: #fff; border:none;">로그인</form:button>
					
					
					</form:form>
					
					

				</div>
			</div>
		</div>
	</section>

					<!--밑부분 배너-->
					<!-- 배너 캐러셀 -->
					<section style="margin-top: 100px;">
					    <div class="container px-1" style="width:1100px;">
					        <div id="bannerCarousel" class="carousel slide" data-bs-ride="carousel">
					            <!-- 캐러셀 인디케이터 -->
					            <div class="carousel-indicators">
					                <c:forEach items="${AllSubBannerInfo}" var="subBanner" varStatus="status">
					                    <button type="button" data-bs-target="#bannerCarousel" data-bs-slide-to="${status.index}" 
					                            class="${status.index == 0 ? 'active' : ''}" aria-label="Slide ${status.index + 1}">
					                    </button>
					                </c:forEach>
					            </div>
					
					            <!-- 캐러셀 슬라이드 -->
					            <div class="carousel-inner">
					                <c:forEach items="${AllSubBannerInfo}" var="subBanner" varStatus="status">
					                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
					                        <a href='${root}/exhibition/exhibition_click?exhibition_id=${subBanner.exhibition_id}'>
					                            <img src="${subBanner.sub_banner_path}${subBanner.sub_banner_name}" class="d-block w-100" alt="Banner ${status.index + 1}" style="height:150px;">
					                        </a>
					                    </div>
					                </c:forEach>
					            </div>
					
					            <!-- 캐러셀 컨트롤 -->
					            <button class="carousel-control-prev" type="button" data-bs-target="#bannerCarousel" data-bs-slide="prev">
					                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					                <span class="visually-hidden">Previous</span>
					            </button>
					            <button class="carousel-control-next" type="button" data-bs-target="#bannerCarousel" data-bs-slide="next">
					                <span class="carousel-control-next-icon" aria-hidden="true"></span>
					                <span class="visually-hidden">Next</span>
					            </button>
					        </div>
					    </div>
					</section>
	<!-- 푸터-->
	<c:import url="/WEB-INF/views/include/footer.jsp"/>


</body>

</html>