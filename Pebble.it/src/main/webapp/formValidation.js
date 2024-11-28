// 프로필 사진 미리보기 기능
document.getElementById("profile-upload").addEventListener("change", function(event) {
    const file = event.target.files[0]; // 업로드된 파일 가져오기
    const preview = document.getElementById("profile-preview"); // 미리보기 이미지 태그

    if (file) {
        const reader = new FileReader();

        reader.onload = function(e) {
            preview.src = e.target.result; // 업로드한 이미지 경로 설정
            preview.style.display = "block"; // 이미지 표시
        };

        reader.readAsDataURL(file); // 파일을 데이터 URL로 변환
    } else {
        preview.src = "#";
        preview.style.display = "none"; // 이미지 숨기기
    }
});

// 아이디 중복 체크
document.getElementById("checkIdButton").addEventListener("click", function () {
    const idInput = document.getElementById("id").value;
    if (!idInput) {
        alert("아이디를 입력해주세요.");
        return;
    }

    fetch(`/Join?action=checkId&id=${idInput}`)
        .then(response => response.json())
        .then(data => {
            if (data.available) {
                alert(data.message); // "사용 가능한 아이디입니다."
            } else {
                alert(data.message); // "아이디가 중복되었습니다. 다른 아이디를 입력해주세요."
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        });
});

// 닉네임 중복 체크
document.getElementById("checkNicknameButton").addEventListener("click", function () {
    const nicknameInput = document.getElementById("nickname").value;
    if (!nicknameInput) {
        alert("닉네임을 입력해주세요.");
        return;
    }

    fetch(`/Join?action=checkNickname&nickname=${nicknameInput}`)
        .then(response => response.json())
        .then(data => {
            if (data.available) {
                alert(data.message); // "사용 가능한 닉네임입니다."
            } else {
                alert(data.message); // "닉네임이 중복되었습니다. 다른 닉네임을 입력해주세요."
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        });
});


