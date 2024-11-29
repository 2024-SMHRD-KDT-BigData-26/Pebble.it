$(document).ready(function () {
    // Summernote 초기화
    $('#summernote').summernote({
        height: 300, // 에디터 높이
        lang: 'ko-KR', // 한국어 설정
        placeholder: '여기에 내용을 입력하세요!' // 기본 텍스트
    });

    // 사이드바 로드
    $(".sidebar").load("Sidebar.html");
});
$(document).ready(function () {
    // Summernote 초기화
    $('#summernote').summernote({
        height: 500, // 전체 에디터 높이 고정
        lang: 'ko-KR', // 한국어 설정
        placeholder: '여기에 내용을 입력하세요!', // 기본 텍스트
        toolbar: [ // 필요한 툴바만 선택
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'help']]
        ]
    });

    // 스크롤 방지 (강제적으로 overflow hidden)
    $('.note-editable').css('overflow', 'hidden');
});
