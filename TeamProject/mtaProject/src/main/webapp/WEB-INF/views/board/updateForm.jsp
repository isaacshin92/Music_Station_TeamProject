<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<!-- 문서 유형 : 현재 웹 문서가 어떤 HTML 버전에 맞게 작성되었는지를 알려준다. -->

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
     DTD 선언문이 HTML 페이지의 가장 첫 라인에 명시되어야 웹 브라우저가 HTML 버전을 인식.
     HTML태그나 CSS를 해당 버전에 맞도록 처리하므로 웹 표준 준수를 위하여 반드시 명시되어야 한다.-->
<html lang="ko">
	<head>
		
		<!-- html5 : 파일의 인코딩 방식 지정 - 한국어 처리를 위한 euc-kr과 다국어 처리를 위한 utf-8로 설정.-->
		<meta charset="utf-8" />
		<!-- html4 : 파일의 인코딩 방식 지정 -->
		<!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->

		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<!-- 브라우저의 호환성 보기 모드를 막고, 해당 브라우저에서 지원하는 가장 최신 버전의 방식으로 HTML 보여주도록 설정.-->
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<!--viewport : 화면에 보이는 영역을 제어하는 기술. width는 device-width로 설정(브라우저 너비를 장치 너비에 맞추어 표시). initial-scale는 초기비율(보이는 영역과 웹 페이지를 맞춤). user-scalable는 사용자가 화면축소를 하지 못하도록 설정.-->
      	
		<!-- 모바일 웹 페이지 설정 -->
<link rel="shortcut icon" href="/resources/images/icon.png" />
<link rel="apple-touch-icon" href="/resources/images/icon.png" />
<!-- 모바일 웹 페이지 설정 끝 -->
<link rel="stylesheet"
	href="/resources/include/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/resources/include/dist/css/bootstrap-theme.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>

<script src="/resources/include/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		<script type="text/javascript">

			$(function() {
				$("#boardUpdateBtn").click(function() {
					if(!chkData("#m_title","이름을"))return ;
					else if(!chkData("#m_name","제목을")) return;
					else if(!chkData("#coverImg","커버이미지를 ")) return ; //to-do  파일 등록에 대한 유효성 체크 구문을 다르게 주어, 메서드 생성 
					else if(!chkData("#m_bpm","Bpm 정보를")) return ;
					else if(!chkData("#m_explain","곡 설명을")) return ;
					else if(!chkData("#file","음악파일을")) return ;
					else if(!chkData("#m_price","가격을")) return ;
					else{
						$("#f_updateForm").attr({
							"method": "post",
							"enctype":"multipart/form-data",
							"action": "/board/mBoardUpdate"
						});
						$("#f_updateForm").submit();
					}
				});

				$("#boardCancelBtn").click(function() {
					$("#f_updateForm").each(function(){
						this.reset();
					});
				});
				
				$("#boardListBtn").click(function() {
					location.href = "/board/boardList";
				});
				
				$("input[name ='m_isfree']").click(function(){
					if($("input[name ='m_isfree']:checked").val()== "1"){
						$("#m_price").attr("disabled",true);
						$("#m_price").val("0");
						
						
						
						
					}else{
						$("#m_price").attr("disabled",false);
						
						
						
						
					}
				});
				
			});
		</script>
		<title>UpdateForm.jsp</title>
	</head>
	<body>
	<form id = "hidden">
	
	</form>

	<div class="container">
		<h2 class="text-center">글수정</h2>
		<div class="text-cencter">
			<form id="f_updateForm" name="f_updateForm">
				<input type="hidden" id="m_no" name="m_no" value="${updateData.m_no }"/>
				<input type ="hidden" id ="user_id" name = "user_id" value ="${updateData.user_id }"/>
				<table class="table table-bordered">
					<colgroup>
						<col width="17%" />
						<col width="33%" />
						<col width="17%" />
						<col width="33%" />
						<colgroup>
					<col width="17%" />
					<col width="33%" />
					<col width="17%" />
					<col width="33%" />
				</colgroup>
				<tbody>
					<tr>
						<th><label for="m_title" class="text-left">Title : </label></th>
						<td><input type="text" name="m_title" id="m_title" value = "${updateData.m_title }"
							placeholder="게시글 제목을 입력하세요"></td>
					</tr>
					<tr>
						<th><label for="m_name" class="text-left">Track Title
								: </label></th>
						<td><input type="text" name="m_name" id="m_name" value = "${updateData.m_name }"
							placeholder="곡 제목을 입력하세요"></td>
					</tr>
					<tr>
						<th><label for="m_coverimage" class="text-left">Cover
								Image Attachment : </label></th>
						<td><input type="file" name="coverImg" id="coverImg"></td>
						<!-- 업로드 할 파일 미리보기가 출력될 영역 설정 예정  -->
						<%--  <td ><span class = "preview">
							 <img id ="" src="/uploadStorage/coverImg/${updateData.m_coverimage}" style="width: 100px;height: 100px;"/>
						</span></td>  --%>
					</tr>
					<tr>
						<th><label for="m_bpm">BPM : </label></th>
						<td><input type="number" name="m_bpm" id="m_bpm" pattern="[0-9]+"value = "0" value ="${updateData.m_bpm }"
							placeholder="BPM 정보 입력"></td>
					</tr>
					<tr>
						<th><label for="m_explain">Description : </label></th>
						<td><textarea name="m_explain" id="m_explain" cols="30"
								rows="10" style="resize: none;" placeholder="곡 설명"> ${updateData.m_explain}</textarea></td>
					</tr>
					<tr>
						<th><label for="m_file">File(wav,mp3) : </label></th>
						<td><input type="file" name="file" id="file" ></td>
						<%--  <td><audio id="player" controls controlsList="nodownload"
											src="/uploadStorage/audioFile/${updateData.m_file}"></audio></td>  --%>
					</tr>

					<tr>
						<th><label for="m_isfree">Distribute for Free : </label></th>
						<td>
								<input type="radio" name = "m_isfree" value = "1" ><label class = " ">Y</label>
								
								<input type="radio" name = "m_isfree" value ="0"><label class = " ">N</label>
								
							</td>
					</tr>
					<tr>
						<th><label for="m_price">Price : </label></th>
						<td><input type="text" name="m_price" id="m_price" value = "${updateData.m_price}"
							placeholder="판매 희망 금액" onkeyup="chkNumber(this)" pattern="[0-9]+"></td>
					</tr>
					
					<tr>
						<th><label for ="m_genre">Genre : </label></th>
						<td><select name ="m_genre" id = "m_genre" >
							<option value = "genre">Genre</option>
							<option value = "ballad">Ballad</option>
							<option value ="Elelctronic">Elelctronic</option>
							<option value ="r&b">R&B</option>
							<option value ="new age">New Age</option>
							<option value ="rap">Rap/Hip-hop</option>
							
						</select>

				</tbody>

				</table>
			</form>
		</div>
	</div>

	<div class="text-center">
		<input type="button" value="수정" id="boardUpdateBtn" class="btn btn-success"/> 
		<input type="button" value="취소" id="boardCancelBtn" class="btn btn-success"/> 
		<input type="button" value="목록" id="boardListBtn" class="btn btn-success"/>
	</div>
</body>
</html>