// 초기 변수 설정
let currentPage = 1;
const totalPages = 20; // 총 페이지 수

// 요소 선택
const pageInfo = document.getElementById("page-info");
const prevPageBtn = document.getElementById("prev-page");
const nextPageBtn = document.getElementById("next-page");

// 페이지 업데이트 함수
function updatePagination(direction) {
  // 방향에 따라 페이지 번호 변경
  if (direction === "next" && currentPage < totalPages) {
    currentPage++;
  } else if (direction === "prev" && currentPage > 1) {
    currentPage--;
  }

  // 페이지 정보 업데이트
  pageInfo.textContent = currentPage;

  // 버튼 활성화/비활성화
  prevPageBtn.disabled = currentPage === 1;
  nextPageBtn.disabled = currentPage === totalPages;
}

// 이전 버튼 클릭 이벤트
prevPageBtn.addEventListener("click", () => updatePagination("prev"));

// 다음 버튼 클릭 이벤트
nextPageBtn.addEventListener("click", () => updatePagination("next"));
