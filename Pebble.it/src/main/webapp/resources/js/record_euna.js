$(document).ready(function () {
    // 수정 버튼 클릭 이벤트
    $('#modify-btn').click(function () {
        alert('수정 기능은 구현 중입니다.');
    });

    // 삭제 버튼 클릭 이벤트
    $('#delete-btn').click(function () {
        const confirmDelete = confirm('정말 삭제하시겠습니까?');
        if (confirmDelete) {
            alert('삭제되었습니다.');
            // 삭제 후 동작 추가
        }
    });

    // 목록으로 버튼 클릭 이벤트
    $('#back-btn').click(function () {
        alert('목록으로 이동합니다.');
        // 이동 로직 추가
    });
});
