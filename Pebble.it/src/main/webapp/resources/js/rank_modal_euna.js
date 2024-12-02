// 모달과 버튼 가져오기
const modal = document.getElementById("modal");
const closeModalButton = document.getElementById("closeModal");
const openModalButton = document.querySelector(".btn");

// 모달 열기
openModalButton.addEventListener("click", () => {
    modal.style.display = "flex"; // 모달 보이기
});

// 모달 닫기
closeModalButton.addEventListener("click", () => {
    modal.style.display = "none"; // 모달 숨기기
});

// 모달 외부 클릭 시 닫기
window.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none"; // 배경 클릭 시 모달 숨기기
    }
});
