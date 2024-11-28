document.addEventListener("DOMContentLoaded", function () {
  // DOM 요소들 가져오기
  const calendarEl = document.getElementById("calendar");
  const schedulePopup = document.getElementById("schedulePopup");
  const addEventBtn = document.getElementById("addEventBtn");
  const closePopupBtn = document.getElementById("closePopupBtn");
  const scheduleBtn = document.getElementById("scheduleBtn");
  const eventTitleInput = document.getElementById("eventTitle");
  const eventDateInput = document.getElementById("eventDate");
  const eventMemoInput = document.getElementById("eventMemo");

  const eventDetailPopup = document.getElementById("eventDetailPopup");
  const detailTitleInput = document.getElementById("detailTitle");
  const detailDateInput = document.getElementById("detailDate");
  const detailMemoInput = document.getElementById("detailMemo");
  const closeDetailPopupBtn = document.getElementById("closeDetailPopupBtn");

  // FullCalendar 초기화
  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    headerToolbar: {
      left: "",
      center: "title",
      right: "",
    },
    events: [], // 기존 이벤트 데이터 없음
    dateClick: function (info) {
      // 날짜 클릭 시 알림 표시 (기능 유지)
      alert("선택된 날짜: " + info.dateStr);
    },
    eventClick: function (info) {
      // 일정 클릭 시 상세 팝업 열기
      const event = info.event; // 클릭한 이벤트 객체

      // 상세 팝업 필드에 데이터 채우기
      detailTitleInput.value = event.title || ""; // 제목
      detailDateInput.value = event.startStr || ""; // 날짜
      detailMemoInput.value = event.extendedProps.description || ""; // 메모

      // 상세 팝업 열기
      eventDetailPopup.classList.remove("hidden");
    },
  });

  // 캘린더 렌더링
  calendar.render();

  // 달을 넘기는 버튼 기능 유지
  document.getElementById("prevMonth").addEventListener("click", function () {
    calendar.prev();
  });
  document.getElementById("nextMonth").addEventListener("click", function () {
    calendar.next();
  });
  document.getElementById("today").addEventListener("click", function () {
    calendar.today();
  });

  // 일정 등록 팝업 닫기 버튼
  closePopupBtn.addEventListener("click", function () {
    schedulePopup.classList.add("hidden");
  });

  // 일정 등록 버튼 클릭 시 팝업 열기
  scheduleBtn.addEventListener("click", function () {
    schedulePopup.classList.remove("hidden");
  });

  // 일정 등록 버튼 클릭 시 일정 추가
  addEventBtn.addEventListener("click", function () {
    const eventTitle = eventTitleInput.value.trim();
    const eventDate = eventDateInput.value;
    const eventMemo = eventMemoInput.value.trim();

    if (eventTitle && eventDate) {
      // 캘린더에 새 이벤트 추가
      calendar.addEvent({
        title: eventTitle,
        start: eventDate,
        description: eventMemo, // 메모 포함
      });

      alert("일정이 등록되었습니다.");
      schedulePopup.classList.add("hidden");

      // 입력 필드 초기화
      eventTitleInput.value = "";
      eventDateInput.value = "";
      eventMemoInput.value = "";
    } else {
      alert("일정 제목과 날짜를 입력하세요.");
    }
  });

  // 상세 팝업 닫기 버튼 클릭 이벤트
  closeDetailPopupBtn.addEventListener("click", function () {
    eventDetailPopup.classList.add("hidden");
  });
});
