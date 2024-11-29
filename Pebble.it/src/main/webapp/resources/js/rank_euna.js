// 문서가 준비되었을 때 실행되는 함수
$(document).ready(function() {
    // 사이드바를 외부 파일에서 로드
    $(".sidebar").load("sidebar.html");
    
    // 추가적인 JavaScript 동작이 필요하면 여기에 작성
});
// 특정 원을 획득 상태로 변경
function achieve(index) {
    const items = document.querySelectorAll('.achievement-item');
    if (items[index]) {
      items[index].classList.add('achieved'); // 'achieved' 클래스 추가
      items[index].style.display = 'flex'; // 숨김 해제
    }
  }
  
  // 예시: 첫 번째 원을 획득 상태로 변경
  achieve(0);
  