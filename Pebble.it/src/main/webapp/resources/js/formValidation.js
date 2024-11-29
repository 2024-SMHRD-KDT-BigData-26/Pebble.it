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

// 닉네임 중복 확인
document.getElementById("check-nick").addEventListener("click", function() {
	const nicknameInput = document.getElementById("nickname").value;

	if (!nicknameInput) {
		alert("닉네임을 입력해주세요.");
		return;
	}

	fetch(`/Pebble.it/Join?action=checkNickname&nickname=${encodeURIComponent(nicknameInput)}`)
		.then(response => response.json())
		.then(data => {
			console.log("서버응답:", data); //디버깅용 로그
			if (data.available) {
				alert(data.message); // "사용 가능한 닉네임입니다."
			} else {
				alert(data.message); // "닉네임이 중복되었습니다."
			}
		})
		.catch(error => {
			console.error("오류 발생:", error);
			alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
		});
});

// 아이디 중복 확인
document.getElementById("check-id").addEventListener("click", function() {
	const idInput = document.getElementById("userId").value;

	if (!idInput) {
		alert("아이디를 입력해주세요.");
		return;
	}

	fetch(`/Pebble.it/Join?action=checkId&id=${encodeURIComponent(idInput)}`)
		.then(response => response.json())
		.then(data => {
			console.log("서버응답:", data); //디버깅용 로그
			if (data.available) {
				alert(data.message); // "사용 가능한 아이디입니다."
			} else {
				alert(data.message); // "아이디가 중복되었습니다."
			}
		})
		.catch(error => {
			console.error("오류 발생:", error);
			alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
		});
});

// 입력중 실시간 피드백
document.addEventListener("DOMContentLoaded", function () {
    const pwInputField = document.getElementById("pw");
    if (pwInputField) {
        pwInputField.addEventListener("input", function () {
            const pwInput = this.value;
            const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;
            const message = document.getElementById("pw-error");

            if (pwPattern.test(pwInput)) {
                message.textContent = "사용 가능한 비밀번호입니다.";
                message.className = "valid";
            } else {
                message.textContent =
                    "비밀번호는 8~16자 사이의 영문(대/소문자)와 숫자를 포함해야 합니다.";
                message.className = "invalid";
            }
        });
    } else {
        console.error("'pw' input field not found in DOM.");
    }
});


// 비밀번호 설정
document.getElementById("join-btn").addEventListener("click", function(event) {
	const pwInput = document.getElementById("pw").value;
	const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;

	if (!pwPattern.test(pwInput)) {
		alert("비밀번호는 8~16자 사이의 영문(대/소문자)과 숫자를 포함해야 합니다.");
		event.preventDefault();
	}
})