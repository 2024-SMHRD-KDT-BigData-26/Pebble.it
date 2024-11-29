// 페이지가 로드되면 실행할 코드
document.addEventListener("DOMContentLoaded", function () {
    // 캘린더를 표시할 요소 가져오기
    const calendarEl = document.getElementById("calendar");
  
    // 팝업 관련 DOM 요소 가져오기
    const schedulePopup = document.getElementById("schedulePopup");
    const addEventBtn = document.getElementById("addEventBtn");
    const closePopupBtn = document.getElementById("closePopupBtn");
    const scheduleBtn = document.getElementById("scheduleBtn");
    const eventTitleInput = document.getElementById("eventTitle");
    const eventDateInput = document.getElementById("eventDate");
    const eventMemoInput = document.getElementById("eventMemo");
    const eventColorSelect = document.getElementById("eventColor");
  
    // FullCalendar 라이브러리를 초기화해서 캘린더를 설정
    const calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth', // 기본 뷰를 월별 보기로 설정
      headerToolbar: {
        left: '', // 왼쪽 버튼 없음
        center: 'title', // 캘린더 제목 표시
        right: '', // 오른쪽 버튼 없음
      },
      events: [], // 초기에 표시될 이벤트(일정)는 비어있음
      dateClick: function (info) {
        // 날짜를 클릭하면 그 날짜를 팝업으로 표시
        alert('선택한 날짜: ' + info.dateStr);
      },
    });
  
    // 캘린더 화면에 표시하기
    calendar.render();
  
    // 이전 달로 이동하는 버튼에 클릭 이벤트 추가
    document.getElementById("prevMonth").addEventListener("click", function () {
      calendar.prev(); // 이전 달로 이동
    });
  
    // 다음 달로 이동하는 버튼에 클릭 이벤트 추가
    document.getElementById("nextMonth").addEventListener("click", function () {
      calendar.next(); // 다음 달로 이동
    });
  
    // 오늘 날짜로 이동하는 버튼에 클릭 이벤트 추가
    document.getElementById("today").addEventListener("click", function () {
      calendar.today(); // 현재 날짜로 이동
    });
  
    // 팝업 닫기 버튼 클릭 시 팝업 닫기
    closePopupBtn.addEventListener("click", function () {
      schedulePopup.classList.add("hidden"); // 팝업을 숨김
    });
  
    // 일정 등록 버튼 클릭 시 팝업 열기
    scheduleBtn.addEventListener("click", function () {
      schedulePopup.classList.remove("hidden"); // 팝업을 보임
    });
  
    // 일정 등록 버튼 클릭 시 새로운 일정 추가
    addEventBtn.addEventListener("click", function () {
      const eventTitle = eventTitleInput.value.trim(); // 입력한 일정 제목 가져오기
      const eventDate = eventDateInput.value; // 선택한 날짜 가져오기
      const eventMemo = eventMemoInput.value.trim(); // 입력한 메모 가져오기
  
      // 제목과 날짜가 입력되었는지 확인
      if (eventTitle && eventDate) {
        // 캘린더에 새로운 일정 추가
        calendar.addEvent({
          title: eventTitle, // 일정 제목
          start: eventDate, // 일정 날짜
          description: eventMemo, // 일정에 대한 메모
        });
  
        // 일정 추가 완료 메시지 표시
        alert("일정이 등록되었습니다!");
  
        // 팝업 닫기
        schedulePopup.classList.add("hidden");
  
        // 입력 필드 초기화 (비우기)
        eventTitleInput.value = "";
        eventDateInput.value = "";
        eventMemoInput.value = "";
      } else {
        // 제목이나 날짜가 입력되지 않았다면 경고 메시지 표시
        alert("일정 제목과 날짜를 입력하세요.");
      }
    });
  });
  