$(document).ready(function () {
    // Summernote 에디터 초기화
    $('#summernote').summernote({
        height: 650, // 에디터 높이를 600px로 고정
        lang: 'ko-KR', // 한국어 설정
        placeholder: '여기에 내용을 입력하세요!' // 에디터 기본 텍스트
    });

    // 취소 버튼 클릭 이벤트 처리
    $('#cancel-btn').click(function () {
        // 사용자에게 작성 중인 내용을 취소할지 확인
        if (confirm('작성 중인 내용을 취소하시겠습니까?')) {
            $('#editor-title').val(''); // 제목 입력 필드를 초기화
            $('#summernote').summernote('reset'); // 에디터 내용을 초기화
        }
    });

    // 등록 버튼 클릭 이벤트 처리
    $('#submit-btn').click(function () {
        // 제목과 에디터 내용 가져오기
        const title = $('#editor-title').val(); // 제목 필드 값
        const content = $('#summernote').summernote('code'); // 에디터 내용

        // 제목이 비어있는 경우 경고 메시지 표시
        if (!title) {
            alert('제목을 입력하세요!');
            return;
        }

        // 내용이 비어있는 경우 경고 메시지 표시
        if (!content || content === '<p><br></p>') {
            alert('내용을 입력하세요!');
            return;
        }

        // 확인용 메시지 (추후 서버 전송 코드로 대체 가능)
        alert(`제목: ${title}\n내용: ${content}`);
    });
});
