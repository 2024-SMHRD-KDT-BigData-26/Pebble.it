// 공통 함수: 입력값 검증
function validateInput(input, pattern, errorMessageElement, errorMessage, successCallback, errorCallback) {
   const value = input.value.trim();

   // 입력값이 비어 있을 경우 처리
   if (!value) {
      errorMessageElement.textContent = ""; // 오류 메시지 초기화
      errorMessageElement.style.display = "none"; // 메시지 숨김
      input.classList.remove("invalid");
      if (errorCallback) errorCallback();
      return false;
   }

   // 입력값이 패턴에 맞지 않을 경우 처리
   if (!pattern.test(value)) {
      errorMessageElement.textContent = errorMessage;
      errorMessageElement.style.display = "block"; // 메시지 표시
      errorMessageElement.classList.add("error-tooltip");
      input.classList.add("invalid");
      if (errorCallback) errorCallback();
      return false;
   }

   // 유효한 입력값일 경우 처리
   errorMessageElement.textContent = "";
   errorMessageElement.style.display = "none"; // 메시지 숨김
   input.classList.remove("invalid");
   if (successCallback) successCallback();
   return true;
}

// 공통 함수: 중복 확인 요청
function checkDuplicate(url, successCallback, errorCallback) {
   fetch(url)
      .then((response) => response.json())
      .then((data) => {
         if (data.available) {
            alert(data.message); // 사용 가능 메시지
            if (successCallback) successCallback(data);
         } else {
            alert(data.message); // 중복 메시지
            if (errorCallback) errorCallback(data);
         }
      })
      .catch((error) => {
         console.error("오류 발생:", error);
         alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
      });
}

// 프로필 사진 미리보기
document.getElementById("profile-upload").addEventListener("change", function (event) {
   const file = event.target.files[0]; // 업로드된 파일 가져오기
   const preview = document.getElementById("profile-preview"); // 미리보기 이미지 태그

   if (file) {
      const reader = new FileReader();

      reader.onload = function (e) {
         preview.src = e.target.result; // 업로드한 이미지 경로 설정
         preview.style.display = "block"; // 이미지 표시
      };

      reader.readAsDataURL(file); // 파일을 데이터 URL로 변환
   } else {
      preview.src = "#";
      preview.style.display = "none"; // 이미지 숨기기
   }
});

// 닉네임 입력 제한 및 실시간 오류 메시지
const nicknameInput = document.getElementById("nickname");
const nickError = document.getElementById("nick-error");
const checkNickBtn = document.getElementById("check-nick");
const joinBtn = document.querySelector(".join-btn");

nicknameInput.addEventListener("input", function () {
   validateInput(
      nicknameInput,
      /^[a-zA-Z가-힣0-9]{1,8}$/, // 1~8자 영문, 한글, 숫자만 허용 (특수문자 허용 안 함)
      nickError,
      "닉네임은 1~8자의 영문, 한글, 숫자만 입력 가능합니다.",
      () => {
         // 유효하면 버튼 활성화
         joinBtn.disabled = false;
         checkNickBtn.disabled = false;
      },
      () => {
         // 유효하지 않으면 버튼 비활성화
         joinBtn.disabled = true;
         checkNickBtn.disabled = true;
      }
   );
});

// 닉네임 중복 확인
checkNickBtn.addEventListener("click", function () {
   const nickname = nicknameInput.value.trim();
   if (!nickname) {
      alert("닉네임을 입력해주세요.");
      return;
   }

   checkDuplicate(
      `/Pebble.it/Join?action=checkNickname&nickname=${encodeURIComponent(nickname)}`,
      (data) => {
         nickError.textContent = "사용 가능한 닉네임입니다.";
         nickError.classList.add("valid");
         nickError.classList.remove("invalid");
      },
      (data) => {
         nickError.textContent = "닉네임이 중복되었습니다. 다른 닉네임을 입력해주세요.";
         nickError.classList.add("invalid");
         nickError.classList.remove("valid");
      }
   );
});

// 아이디 입력 제한 및 실시간 오류 메시지
const idInput = document.getElementById("userId");
const idError = document.getElementById("id-error");
const checkIdBtn = document.getElementById("check-id");

idInput.addEventListener("input", function () {
   validateInput(
      idInput,
      /^[a-z0-9]{5,10}$/, // 5~10자 영문 소문자, 숫자만 허용 (특수문자 허용 안 함)
      idError,
      "아이디는 5~10자의 영문 소문자와 숫자만 입력 가능합니다.",
      () => {
         // 유효하면 버튼 활성화
         joinBtn.disabled = false;
         checkIdBtn.disabled = false;
      },
      () => {
         // 유효하지 않으면 버튼 비활성화
         joinBtn.disabled = true;
         checkIdBtn.disabled = true;
      }
   );
});

// 아이디 중복 확인
checkIdBtn.addEventListener("click", function () {
   const idValue = idInput.value.trim();
   if (!idValue) {
      alert("아이디를 입력해주세요.");
      return;
   }

   checkDuplicate(
      `/Pebble.it/Join?action=checkId&id=${encodeURIComponent(idValue)}`,
      (data) => {
         idError.textContent = "사용 가능한 아이디입니다.";
         idError.classList.add("valid");
         idError.classList.remove("invalid");
      },
      (data) => {
         idError.textContent = "아이디가 중복되었습니다. 다른 아이디를 입력해주세요.";
         idError.classList.add("invalid");
         idError.classList.remove("valid");
      }
   );
});

// 비밀번호 입력 제한 및 실시간 오류 메시지
const pwInput = document.getElementById("password");
const pwError = document.getElementById("password-error");

pwInput.addEventListener("input", function () {
   validateInput(
      pwInput,
      /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/, // 8~16자 영문 대/소문자와 숫자 포함 (특수문자 허용 안 함)
      pwError,
      "비밀번호는 8~16자 영문 대/소문자와 숫자를 포함해야 합니다.",
      () => {
         // 유효하면 버튼 활성화
         joinBtn.disabled = false;
      },
      () => {
         // 유효하지 않으면 버튼 비활성화
         joinBtn.disabled = true;
      }
   );
});
