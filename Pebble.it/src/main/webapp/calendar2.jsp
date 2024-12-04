<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
<title>FullCalendar with JSP</title>
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#eventModal {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 9999;
	background: white;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

#modalOverlay {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: 9998;
}

#calendarEventModal {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 9999;
    background: white;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.5);
}

#calendarModalOverlay {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: 9998;
}

</style>
</head>
<body>
	<div id="calendar"></div>

	<!-- Modal Overlay -->
	<div id="modalOverlay"></div>

	<!-- Modal for Event -->
	<div id="eventModal">
		<form id="eventForm">
			<input type="hidden" id="cal_idx" name="cal_idx"> <label
				for="cal_title">Title:</label> <input type="text" id="cal_title"
				name="cal_title" required><br>
			<br> <label for="cal_content">Details:</label>
			<textarea id="cal_content" name="cal_content" required></textarea>
			<br>
			<br> <label for="cal_st_dt">Start Date:</label> <input
				type="date" id="cal_st_dt" name="cal_st_dt" required><br>
			<br> <label for="cal_ed_dt">End Date:</label> <input type="date"
				id="cal_ed_dt" name="cal_ed_dt" required><br>
			<br> <label for="cal_color">Color:</label> <input type="color"
				id="cal_color" name="cal_color" required><br>
			<br>

			<button type="button" id="saveEvent">Save</button>
			<button type="button" id="closeModal">Close</button>
		</form>
	</div>
	
	<!-- 상세 모달 -->
	<div id="calendarEventModal">
    <form id="calendarEventForm">
        <input type="hidden" id="event_cal_idx" name="cal_idx">
        
        <!-- 제목 -->
        <label for="event_cal_title">Title:</label>
        <input type="text" id="event_cal_title" name="cal_title" required>
        <br><br>
        
        <!-- 내용 -->
        <label for="event_cal_content">Details:</label>
        <textarea id="event_cal_content" name="cal_content" required></textarea>
        <br><br>
        
        <!-- 시작 날짜 -->
        <label for="event_cal_st_dt">Start Date:</label>
        <input type="date" id="event_cal_st_dt" name="cal_st_dt" required>
        <br><br>
        
        <!-- 종료 날짜 -->
        <label for="event_cal_ed_dt">End Date:</label>
        <input type="date" id="event_cal_ed_dt" name="cal_ed_dt" required>
        <br><br>
        
        <!-- 색상 선택 -->
        <label for="event_cal_color">Color:</label>
        <input type="color" id="event_cal_color" name="cal_color">
        <br><br>

        <!-- 버튼 -->
        <button type="button" id="updateEvent">Update</button>
        <button type="button" id="deleteCalendarEvent">Delete</button>
        <button type="button" id="closeCalendarModal">Close</button>
    </form>
</div>

<!-- Overlay for the Modal -->
<div id="calendarModalOverlay"></div>
	

	<script>
	
    // JSP에서 컨텍스트 경로를 가져와 JavaScript 변수에 저장
    var contextPath = '${pageContext.request.contextPath}';
    console.log("Context Path: " + contextPath);
    
    $(document).ready(function () {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            events: contextPath + '/events', // 이벤트 데이터 가져오기

            // 일정이 없는 영역 클릭 시 등록 모달 열기
            selectable: true,
            select: function (info) {
                $('#cal_idx').val(''); // 새로운 일정으로 초기화
                $('#cal_title').val('');
                $('#cal_content').val('');
                $('#cal_st_dt').val(info.startStr);
                $('#cal_ed_dt').val(info.endStr);
                $('#cal_color').val('#000000'); // 기본 색상 설정

                // 등록 모달 표시
                $('#eventModal').show();
                $('#modalOverlay').show();
            },

            // 기존 일정 클릭 시 상세 모달 열기
            eventClick: function (info) {
                // 선택된 일정의 상세 정보 가져오기
                $.get(contextPath + '/events?cal_idx=' + info.event.id, function (data) {
                    $('#event_cal_idx').val(data.cal_idx);
                    $('#event_cal_title').val(data.cal_title);
                    $('#event_cal_content').val(data.cal_content);
                    $('#event_cal_st_dt').val(data.cal_st_dt);
                    $('#event_cal_ed_dt').val(data.cal_ed_dt);
                    $('#event_cal_color').val(data.cal_color);

                    // 상세 모달 표시
                    $('#calendarEventModal').show();
                    $('#calendarModalOverlay').show();
                }).fail(function () {
                    alert('Failed to load event details.');
                });
            }
        });

        calendar.render();

        // Save (등록 및 수정)
        $('#saveEvent').on('click', function () {
            var formData = {
                cal_idx: $('#cal_idx').val() || 0, // 등록 시 0
                cal_title: $('#cal_title').val(),
                cal_content: $('#cal_content').val(),
                cal_st_dt: $('#cal_st_dt').val(),
                cal_ed_dt: $('#cal_ed_dt').val(),
                cal_color: $('#cal_color').val(),
            };

            $.ajax({
                url: contextPath + '/events',
                type: formData.cal_idx === 0 ? 'POST' : 'PUT', // 등록인지 수정인지 확인
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function () {
                    calendar.refetchEvents();
                    closeEventModal();
                    alert('Event saved successfully!');
                },
                error: function () {
                    alert('Failed to save the event.');
                }
            });
        });
        
        // Update Event (수정)
        $('#updateEvent').on('click', function () {
            var formData = {
                cal_idx: $('#event_cal_idx').val(),
                cal_title: $('#event_cal_title').val(),
                cal_content: $('#event_cal_content').val(),
                cal_st_dt: $('#event_cal_st_dt').val(),
                cal_ed_dt: $('#event_cal_ed_dt').val(),
                cal_color: $('#event_cal_color').val(),
            };

            $.ajax({
                url: contextPath + '/events', // 수정 요청 경로
                type: 'PUT',  // 수정 요청
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function () {
                    calendar.refetchEvents(); // 일정 갱신
                    closeCalendarModal(); // 모달 닫기
                    alert('Event updated successfully!'); // 알림
                },
                error: function () {
                    alert('Failed to update the event.');
                }
            });
        });

        // Delete Event
        $('#deleteCalendarEvent').on('click', function () {
            var cal_idx = $('#event_cal_idx').val();

            if (!cal_idx) {
                alert('No event selected for deletion.');
                return;
            }

            $.ajax({
                url: contextPath + '/events?cal_idx=' + cal_idx,
                type: 'DELETE',
                success: function () {
                    calendar.refetchEvents();
                    closeCalendarModal();
                    alert('Event deleted successfully!');
                },
                error: function () {
                    alert('Failed to delete the event.');
                }
            });
        });

        // Close Modals
        $('#closeModal, #modalOverlay').on('click', function () {
            closeEventModal();
        });

        $('#closeCalendarModal, #calendarModalOverlay').on('click', function () {
            closeCalendarModal();
        });

        function closeEventModal() {
            $('#eventModal').hide();
            $('#modalOverlay').hide();
            $('#eventForm')[0].reset();
        }

        function closeCalendarModal() {
            $('#calendarEventModal').hide();
            $('#calendarModalOverlay').hide();
            $('#calendarEventForm')[0].reset();
        }
    });


	</script>
</body>
</html>
