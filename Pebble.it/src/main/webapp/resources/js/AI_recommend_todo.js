// 동적으로 Google AI SDK 로드
async function loadGoogleAI() {
  const module = await import('https://esm.run/@google/generative-ai');
  return module.GoogleGenerativeAI;
}

// AI 설정 및 초기화
const API_KEY = "AIzaSyBSF-N2LDT7EFtvQXdc-Ujr6GnOHjFUIys";
let genAI;
let model;

// 초기화 함수
async function initialize() {
  try {
    const GoogleGenerativeAI = await loadGoogleAI();
    genAI = new GoogleGenerativeAI(API_KEY);
    model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
    
    // 버튼 이벤트 리스너 등록
    const generateBtn = document.getElementById('generateBtn');
    if (generateBtn) {
      generateBtn.addEventListener('click', run);
    }
  } catch (error) {
    console.error('AI 초기화 실패:', error);
  }
}

// AI 실행 함수
async function run() {
  const outputDiv = document.getElementById('AIoutput_todo');
  const button = document.getElementById('generateBtn');
  
  try {
    button.style.display = 'none';
    
    const prompt = `나는 특정 스포츠에 대해 매일 새로운 할일을 추천해주려고 해
   너는 [수영] 코치고 나는 [수영] [전문가]야
   
   나에게 너는 아래의 키워드와 관련된 5가지 추천 할일을 알려줘
   
   모든 키워드에 대한 정보를 제공하지 않아도 되지만 최소 1개에 대한 정보는 제공해줘
   
   할일 내용을 한눈에 볼 수 있도록 #DE9631로 색을 넣어주고, <h3>태그를 넣고, 마지막에 어울리는 이모지를 넣어줘
   할일 내용은 한줄에 40자 내에서 한줄로 출력하고 흥미롭고 실용적으로 작성해줘
   줄바꿈 시 꼭 <br>을 넣어서 전체를 태그형태로 출력하는데 첫줄에 html 출력하지말고 :도 출력하지 말아줘
   
   오늘의 키워드는 아래와 같아
   - 키워드: 접영, 호흡법, 자유영, 발차기, 효율성, 어깨, 유연성, 강화운동

   마지막 문장은 두번 줄바꿈을 하고 응원하는 문구와 make it happen! 로 끝내고 <b>태그를 넣어 강조해줘`;

    const result = await model.generateContent(prompt);
    const response = await result.response;
    const text = response.text();
    
    if (outputDiv) {
      outputDiv.innerHTML = text;
    }
  } catch (error) {
    console.error('AI 생성 실패:', error);
    if (outputDiv) {
      outputDiv.innerHTML = '죄송합니다. 오류가 발생했습니다.';
    }
    button.style.display = 'block';
  }
}

// 페이지 로드 시 초기화
document.addEventListener('DOMContentLoaded', initialize);
