// 문서가 준비되었을 때 실행되는 함수
$(document).ready(function() {
    // 사이드바를 외부 파일에서 로드
    $(".sidebar").load("sidebar.html");
    
// 특정 뱃지를 획득 상태로 변경
function achieveBadge(index) {
    const items = document.querySelectorAll('.achievement-item');
    if (items[index]) {
        const notAchievedImg = items[index].querySelector('.not-achieved');
        const achievedImg = items[index].querySelector('.achieved');

        // 미획득 이미지를 숨기고, 획득 이미지를 표시
        if (notAchievedImg) notAchievedImg.style.display = 'none';
        if (achievedImg) achievedImg.style.display = 'block';
    }
}

});
