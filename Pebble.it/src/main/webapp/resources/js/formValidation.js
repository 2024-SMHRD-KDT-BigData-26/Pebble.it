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

// 닉네임 입력 시 실시간 검증
document.getElementById("nickname").addEventListener("input", function() {
	const nicknameInput = this.value.trim(); // 닉네임 입력값
	const message = document.getElementById("nickname-error"); // 실시간 검증 메시지 영역

	// 닉네임 제한 조건 (1~8자, 한글/영문/숫자만 허용)
	const nicknamePattern = /^[가-힣a-zA-Z0-9]{0,8}$/;

	if (!nicknamePattern.test(nicknameInput)) {
		message.textContent = "닉네임은 1~8자 이내의 한글, 영문 또는 숫자만 허용됩니다.";
		message.classList.add("invalid");
		message.classList.remove("valid");
	} else if (nicknameInput.length > 8) {
		message.textContent = "닉네임이 8자를 초과했습니다. 다시 입력해주세요.";
		message.classList.add("invalid");
		message.classList.remove("valid");
	} else {
		message.textContent = "";
		message.classList.remove("invalid");
		message.classList.add("valid");
	}
});

// 닉네임 입력 시 실시간 검증
document.getElementById("nickname").addEventListener("input", function() {
	const nicknameInput = this.value.trim(); // 닉네임 입력값
	const message = document.getElementById("nick-error"); // 오류 메시지 표시 영역

	// 닉네임 제한 조건 (1~8자, 한글/영문/숫자만 허용)
	const nicknamePattern = /^[가-힣a-zA-Z0-9]{1,8}$/;

	if (!nicknamePattern.test(nicknameInput)) {
		message.textContent = "닉네임은 1~8자 이내의 한글, 영문 또는 숫자만 입력 가능합니다.";
		message.classList.add("invalid");
		message.classList.remove("valid");
	} else if (nicknameInput.length > 8) {
		message.textContent = "닉네임이 8자를 초과했습니다. 다시 입력해주세요.";
		message.classList.add("invalid");
		message.classList.remove("valid");
	} else {
		message.textContent = ""; // 제한 조건 만족 시 메시지 초기화
		message.classList.remove("invalid");
		message.classList.add("valid");
	}
});

// 닉네임 입력 제한 및 실시간 오류 메시지
const nicknameInput = document.getElementById("nickname");
const nickError = document.getElementById("nick-error"); // 오류 메시지 출력 영역
const checkNickBtn = document.getElementById("check-nick"); // 중복확인 버튼
const joinBtn = document.querySelector(".join-btn"); // 회원가입 버튼

nicknameInput.addEventListener("input", function() {
	const nicknameValue = nicknameInput.value;
	const nicknamePattern = /^[a-zA-Z가-힣0-9]{1,8}$/; // 1~8자 영문, 한글, 숫자 허용

	if (!nicknameValue) {
		// 닉네임이 비어 있을 때 처리
		nickError.textContent = ""; // 오류 메시지 초기화
		nicknameInput.classList.remove("invalid");
		nickError.style.display = "none"; // 오류 메시지 숨김
		joinBtn.disabled = true; // 회원가입 버튼 비활성화
		checkNickBtn.disabled = true; // 중복확인 버튼 비활성화
		return; // 이후 로직 실행하지 않음
	}

	if (!nicknamePattern.test(nicknameValue)) {
		// 패턴에 맞지 않을 때
		nickError.textContent = "닉네임은 1~8자의 영문, 한글, 숫자만 입력 가능합니다.";
		nickError.classList.add("error-tooltip");
		nicknameInput.classList.add("invalid");
		joinBtn.disabled = true; // 회원가입 버튼 비활성화
		checkNickBtn.disabled = true; // 중복확인 버튼 비활성화
		nickError.style.display = "block"; // 에러 표시
	} else {
		// 유효한 닉네임일 때
		nickError.textContent = "";
		nicknameInput.classList.remove("invalid");
		joinBtn.disabled = false; // 회원가입 버튼 활성화
		checkNickBtn.disabled = false; // 중복확인 버튼 활성화
		nickError.style.display = "none"; // 에러 숨김
	}
});

// 닉네임 중복 확인
document.getElementById("check-nick").addEventListener("click", function() {
	const nickname = nicknameInput.value.trim();

	if (!nickname) {
		alert("닉네임을 입력해주세요.");
		return;
	}

	fetch(`/Pebble.it/Join?action=checkNickname&nickname=${encodeURIComponent(nickname)}`)
		.then((response) => response.json())
		.then((data) => {
			console.log("서버응답:", data); // 디버깅용 로그
			if (data.available) {
				alert(data.message); // "사용 가능한 닉네임입니다."
				nickError.textContent = "사용 가능한 닉네임입니다.";
				nickError.classList.add("valid");
				nickError.classList.remove("invalid");
				joinBtn.disabled = false; // 중복 확인 성공 시 버튼 활성화
			} else {
				alert(data.message); // "닉네임이 중복되었습니다."
				nickError.textContent = "닉네임이 중복되었습니다. 다른 닉네임을 입력해주세요.";
				nickError.classList.add("invalid");
				nickError.classList.remove("valid");
				joinBtn.disabled = true; // 중복된 닉네임일 경우 버튼 비활성화
			}
		})
		.catch((error) => {
			console.error("오류 발생:", error);
			alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
		});
});
// 아이디 입력 필드와 버튼 선택
const idInput = document.getElementById("userId");
const idError = document.getElementById("id-error");
const checkIdBtn = document.getElementById("check-id"); // 아이디 중복확인 버튼

// 아이디 입력 제한 및 실시간 오류 메시지
idInput.addEventListener("input", function () {
  const idValue = idInput.value;
  const idPattern = /^[a-z0-9]{5,10}$/; // 5~10자 영문 소문자, 숫자 허용

  if (!idValue) {
    // 아이디가 비어 있을 때
    idError.textContent = ""; // 오류 메시지 초기화
    idInput.classList.remove("invalid");
    idError.style.display = "none"; // 오류 메시지 숨김
    joinBtn.disabled = true; // 회원가입 버튼 비활성화
    checkIdBtn.disabled = true; // 중복확인 버튼 비활성화
    return;
  }

  if (!idPattern.test(idValue)) {
    // 패턴에 맞지 않을 때
    idError.textContent = "아이디는 5~10자의 영문 소문자와 숫자만 입력 가능합니다.";
    idError.classList.add("error-tooltip");
    idInput.classList.add("invalid");
    joinBtn.disabled = true;
    checkIdBtn.disabled = true; // 중복확인 버튼 비활성화
    idError.style.display = "block"; // 에러 표시
  } else {
    // 유효한 아이디일 때
    idError.textContent = "";
    idInput.classList.remove("invalid");
    joinBtn.disabled = false; // 회원가입 버튼 활성화
    checkIdBtn.disabled = false; // 중복확인 버튼 활성화
    idError.style.display = "none"; // 에러 숨김
  }
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
document.addEventListener("DOMContentLoaded", function() {
	const pwInputField = document.getElementById("pw");
	if (pwInputField) {
		pwInputField.addEventListener("input", function() {
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

// 비밀번호 입력 필드와 오류 메시지 영역
const pwInput = document.getElementById("password");
const pwError = document.getElementById("password-error");

// 비밀번호 입력 제한 및 실시간 오류 메시지
pwInput.addEventListener("input", function () {
  const pwValue = pwInput.value;
  const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/; // 8~16자 영문 대/소문자, 숫자 포함

  if (!pwValue) {
    // 비밀번호가 비어 있을 때
    pwError.textContent = ""; // 오류 메시지 초기화
    pwInput.classList.remove("invalid");
    pwError.style.display = "none"; // 오류 메시지 숨김
    joinBtn.disabled = true; // 회원가입 버튼 비활성화
    return;
  }

  if (!pwPattern.test(pwValue)) {
    // 패턴에 맞지 않을 때
    pwError.textContent = "비밀번호는 8~16자 영문 대/소문자와 숫자를 포함해야 합니다.";
    pwError.classList.add("error-tooltip");
    pwInput.classList.add("invalid");
    joinBtn.disabled = true; // 회원가입 버튼 비활성화
    pwError.style.display = "block"; // 에러 표시
  } else {
    // 유효한 비밀번호일 때
    pwError.textContent = "";
    pwInput.classList.remove("invalid");
    joinBtn.disabled = false; // 회원가입 버튼 활성화
    pwError.style.display = "none"; // 에러 숨김
  }
});
