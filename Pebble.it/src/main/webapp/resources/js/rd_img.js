// 배경 이미지 목록
const images = [
    './resources/img/Main_pabble01_bg.png',
    './resources/img/Main_pabble02_bg.png',
    './resources/img/Main_pabble03_bg.png',
    './resources/img/Main_pabble04_bg.png',
    './resources/img/Main_pabble05_bg.png',
];

// 랜덤 이미지 선택
function setRandomBackground() {
    const randomIndex = Math.floor(Math.random() * images.length);
    const randomImage = images[randomIndex];
    
    // .left_section에 배경 이미지 적용
    document.querySelector('.left_section').style.backgroundImage = `url(${randomImage})`;
}

// 페이지 로드 시 실행
window.onload = setRandomBackground;
