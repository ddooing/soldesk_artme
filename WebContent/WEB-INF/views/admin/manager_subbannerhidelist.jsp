<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }" />	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />
<title>관리자 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="../css/styles_manager.css" rel="stylesheet" />
<!--부트스트랩 아이콘 사용-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
	rel="stylesheet">

<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>

<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>	
	
<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
<!-- 부트스트랩 아이콘 CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">




</head>

<body class="sb-nav-fixed">
	<c:import url="/WEB-INF/views/include/admin_header.jsp" />

		<!--실제 내용(대시보드) 부분-->
		<div id="layoutSidenav_content">
			<main style="background-color: ivory;">
				<div class="container-fluid px-4">
					<div style="margin-top: 30px;">
						<h3>서브 배너 관리</h3>
					</div>
					<div style="position: relative; display: flex; justify-content: start; height: 80px; align-items: center; border: 0.2px solid black; background-color: white; margin-top: 20px;">
					<div style="display:flex;  margin-right:60px; width: 450px; float: left;">
						<span class="badge text-bg-success rounded-pill" style="font-size: 15px; margin-right: 10px; margin-left: 50px; padding:10px;">노출배너 ${BadgeCnt.banner_show_Cnt }건</span> 
						<span class="badge text-bg-danger rounded-pill" style="font-size: 15px; margin-right: 10px; padding:10px;">숨김배너 ${BadgeCnt.banner_hide_Cnt }건</span> 
						<span class="badge bg-success-subtle text-success-emphasis rounded-pill" style="background-color: black; font-size: 15px; padding:10px;">배너 총 ${BadgeCnt.banner_all_Cnt}건</span>
					</div>

					<form action="${root }/admin/manager_subbannershowlist" method="get" onsubmit="return validateForm()">
						<select name="type" id="selectCombo" style="width: 150px; height: 40px; margin-right: 30px;">
							<option value="title" selected>제목</option>
						</select>
						
						<input type="text" name="keyword" id="searchKeyword" style="width: 500px; height: 40px; margin-right: 30px;" placeholder="검색어를 입력해주세요" />
							
						
						<button class="btn btn-dark" style="width: 80px; height: 40px;">검색</button>
						<script src="../js/searchAdmin.js"></script>
					</form>
					
					
					<button class="button-39" id="resetButton" role="button" style="width: 80px; height: 44px;  margin-left: 30px;">초기화</button>
					
					<script>
					    document.addEventListener('DOMContentLoaded', function() {
					        var resetButton = document.getElementById('resetButton');

					        resetButton.addEventListener('click', function() {
					            window.location.href = '${root}/admin/manager_subbannerhidelist';
					        });
					    });
					</script>
					
				</div>
				
				<div style="display: flex; margin-top:30px;">
				<h3 onclick="window.location='${root}/admin/manager_subbannershowlist'" style="cursor: pointer; color:#e2e2e2;">노출중</h3>
				<h3 onclick="window.location='${root}/admin/manager_subbannerhidelist'" style=" margin-left:30px; color:black;  cursor: pointer;">숨김</h3>
				</div>
					<div style="background-color: white; margin-top:20px;" >
						
						<table class="table table-striped" style="text-align: center; ">
							<thead>
								<tr style="vertical-align: middle;">
									
									<th scope="col" style="width:50px;">No</th>
									<th scope="col" style="width:500px;">제목</th>
									<th scope="col" style="width:320px;">배너이미지</th>
									<th scope="col">노출기간</th>
									<th scope="col">상태</th>
									<th scope="col">관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${AllSubBannerInfo }" var="bannerBean">
									<tr id="banner-${bannerBean.sub_banner_id}" style="vertical-align: middle; height:150px;">
										
										<td style="width:50px;">${bannerBean.sub_banner_id }</td>
										<td style="width:500px; text-align: left;"><a href="${root }/exhibition/exhibition_click?exhibition_id=${bannerBean.exhibition_id}" style="color:black;">${bannerBean.title }</a></td>
										<td style="width:320px;"><img src="${bannerBean.sub_banner_path }${bannerBean.sub_banner_name }" style="width:300px; height:120px;" /></td>
										<td>${bannerBean.start_date } ~ ${bannerBean.end_date }</td>
										<c:choose>
											<c:when test="${bannerBean.state  == 1}">
												<td>노출중</td>		
											</c:when>
											<c:when test="${bannerBean.state  == 2}">
												<td>숨김</td>		
											</c:when>
										</c:choose>
										<td>
											<button class="btn btn-dark" onclick="location.href='${root}/admin/manager_subbannermodify?sub_banner_id=${bannerBean.sub_banner_id}'">수정</button>
											<button class="btn btn-danger" type="button" onclick="confirmDelete(${bannerBean.sub_banner_id},${bannerBean.expose_order },${bannerBean.state })">삭제</button>
										</td>
									</tr>
								</c:forEach>
							
							</tbody>
						</table>
						<div style="display:flex; margin-top: 20px; margin-bottom: 20px; float: right;">
						
							<button class="btn btn-dark" onclick="location.href='${root}/admin/manager_subbanneradd'">추가</button>
						</div>

					</div>

					
				</div>
			</main>

			
		</div>
	</div>
	
	<script>	// 삭제 관련
		function confirmDelete(bannerId, exposeOrder, state) {
		    Swal.fire({
		        title: '서브 배너를 삭제하시겠습니까?',
		        text: "삭제하면 복구가 불가능합니다.",
		        icon: 'warning',
		        showCancelButton: true,
		        confirmButtonColor: '#3085d6',
		        cancelButtonColor: '#d33',
		        confirmButtonText: '삭제',
		        cancelButtonText: '취소'
		    }).then((result) => {
		        if (result.isConfirmed) {
		            $.ajax({
		                url: '${root}/admin/DeleteSubBanner?sub_banner_id=' + bannerId + '&expose_order=' + exposeOrder + '&state=' + state,
		                type: 'GET',
		                success: function(response) {
		                    Swal.fire(
		                        '삭제되었습니다!',
		                        '서브 배너가 삭제되었습니다.',
		                        'success'
		                    ).then((result) => {
		                        if (result.isConfirmed) {
		                            location.reload(); // 페이지 새로고침
		                        }
		                    });
		                },
		                error: function(xhr, status, error) {
		                    Swal.fire(
		                        '오류 발생',
		                        '오류가 발생했습니다: ' + xhr.responseText,
		                        'error'
		                    );
		                }
		            });
		        }
		    });
		}

		</script>
		
		
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>

</body>

</html>