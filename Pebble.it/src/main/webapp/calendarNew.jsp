<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- FullCalendar Library -->
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js"></script>

  <!-- jQuery 추가 -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <!-- Stylesheets -->
  <link rel="stylesheet" href="resources/css/Sidebar.css"> <!-- 기존 사이드바 스타일 시트 -->
  <link rel="stylesheet" href="resources/css/we_calender.css"> <!-- 캘린더와 메인 레이아웃 관련 스타일 -->
</head>
<body>
<!-- 레이아웃 컨테이너 -->
  <div class="layout">
    <!-- 사이드바 로드 -->
    <div class="sidebar">
        <script>
                $(document).ready(function () {
                    $(".sidebar").load("Sidebar.html", function(response, status, xhr) {
                        if (status == "error") {
                            // 만약 Sidebar.html이 로드되지 않으면 Sidebar.htm으로 대체 시도
                            $(".sidebar").load("/Pebble.it/Sidebar.htm");
                        }
                    });
                });
            </script>
    </div>

    <!-- 메인 콘텐츠 영역 -->
    <div class="main-content">
      <!-- 캘린더 상단 툴바 -->
      <div class="calendar-toolbar">
        <!-- 일정 등록 버튼  -->
        <button id="scheduleBtn" class="schedule-btn">+ 일정 등록</button>

        <div class="calendar-nav">
          <!-- 달을 넘기는 버튼  -->
          <button id="prevMonth" class="calendar-nav-btn">◁</button>
          <button id="nextMonth" class="calendar-nav-btn">▷</button>
          <button id="today" class="calendar-nav-btn">오늘</button>
        </div>
      </div>

      <!-- 캘린더 영역 -->
      <div id="calendar-wrap">
        <div id="calendar"></div>
      </div>
    </div>
  </div>

  <!-- 팝업 -->
  <div id="schedulePopup" class="popup hidden">
    <div class="popup-content">
      <h3>일정 등록</h3>
      <form action="Calendar" method="post">
      <input id="eventTitle" name="title" type="text" placeholder="일정 제목" />
      <input id="eventDate" name="datetime" type="date"/>
      <textarea id="eventMemo" name="memo" type="text" placeholder="메모"></textarea>
      <div class="button-group">
        <button id="closePopupBtn">닫기</button>
        <button id="addEventBtn">등록</button>
      </div>
      </form>
    </div>
  </div>

  <!-- 일정 상세 팝업 -->
  <div id="eventDetailPopup" class="popup hidden">
    <div class="popup-content">
      <h3>일정 상세</h3>
      <input id="detailTitle" type="text" placeholder="일정 제목" readonly />
      <input id="detailDate" type="date" readonly />
      <textarea id="detailMemo" placeholder="메모" readonly></textarea>
      <div class="button-group">
        <button id="closeDetailPopupBtn">닫기</button>
      </div>
    </div>
  </div>
  </div>

  <script src="resources/js/we_calender.js"></script>
</body>
</html>