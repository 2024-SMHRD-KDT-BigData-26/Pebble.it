<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>diary_list</title>

    <!-- FullCalendar Library -->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js"></script>

    <!-- jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- CSS 연결 -->
    <link rel="stylesheet" href="resources/Sidebar.css"> <!-- 사이드바 스타일 -->
    <link rel="stylesheet" href="resources/css/diary.css"> <!-- 메인 콘텐츠 스타일 -->
</head>
<body>
 <!-- 레이아웃 컨테이너 -->
    <div class="layout">
        <!-- 사이드바 -->
        <div class="sidebar">
              <script>
                $(document).ready(function () {
                    $(".sidebar").load("Sidebar.html", function(response, status, xhr) {
                        if (status == "error") {
                            // 만약 Sidebar.html이 로드되지 않으면 Sidebar.htm으로 대체 시도
                            $(".sidebar").load("/Pebble.it/Sidebar.jsp");
                        }
                    });
                });
            </script>
        </div>

        <!-- 메인 콘텐츠 영역 -->
        <div class="main-content">
            <!-- 상단 툴바 -->
            <div class="diary-toolbar">
                <!-- 기록 등록 버튼 -->
                <a href="diary_post.jsp"><button id="diary_post_btn" class="diary_post_btn">+ 기록 등록</button></a>
                <!-- 카테고리 선택 -->
                <select name="diary_category" id="diary_category">
                    <option value="">전체</option>
                    <option value="">나의기록</option>
                    <option value="">운동정보</option>
                    <option value="">추천할일</option>
                </select>
            </div>
            
                                                
            <!-- 콘텐츠 영역-->
            <div class="content-wrapper">
                <!-- 기록 콘텐츠 -->
                <div class="diary-content">
                    <!-- 기록 목록-->
                        <a href="#"><table width="100%" class="diary_content_list">
                            <tr>
                                <td><span class="diary_category_label_01_diary">나의기록</span>2024.12.31(목)</td>
                                <td rowspan="3"><img src="resources/img/diary_sample_img.png" id="diary_img"></td>
                            </tr>
                            <tr>
                                <td class="diary_title">오늘의 운동정보: 배영</td>
                            </tr>
                            <tr>
                                <td>배영은 물 위에 등을 대고 누운 자세로 물을 가르는 수영 방식입니다. <br>
                                    초보자에게 적합한 이유는 호흡이 자유롭고 비교적 배우기 쉽기 때문입니다. <br> 
                                    기본 자세는 몸을 최대한 곧게 펴고, 배꼽이 물 밖으로 나오도록 몸을 띄우는 것입니다.</td>
                            </tr>
                        </table></a>

                        <a href="#"><table width="100%" class="diary_content_list">
                            <tr>
                                <td><span class="diary_category_label_02_sport">운동정보</span>2024.12.31(목)</td>
                                <td rowspan="3"><img src="resources/img/diary_sample_img.png" id="diary_img"></td>
                            </tr>
                            <tr>
                                <td class="diary_title">오늘의 운동정보: 배영</td>
                            </tr>
                            <tr>
                                <td>배영은 물 위에 등을 대고 누운 자세로 물을 가르는 수영 방식입니다. <br>
                                    초보자에게 적합한 이유는 호흡이 자유롭고 비교적 배우기 쉽기 때문입니다. <br> 
                                    기본 자세는 몸을 최대한 곧게 펴고, 배꼽이 물 밖으로 나오도록 몸을 띄우는 것입니다.</td>
                            </tr>
                        </table></a>

                        <a href="#"><table width="100%" class="diary_content_list">
                            <tr>
                                <td><span class="diary_category_label_03_recomment">추천할일</span>2024.12.31(목)</td>
                                <td rowspan="3"><img src="resources/img/diary_sample_img.png" id="diary_img"></td>
                            </tr>
                            <tr>
                                <td class="diary_title">오늘의 운동정보: 배영</td>
                            </tr>
                            <tr>
                                <td>배영은 물 위에 등을 대고 누운 자세로 물을 가르는 수영 방식입니다. <br>
                                    초보자에게 적합한 이유는 호흡이 자유롭고 비교적 배우기 쉽기 때문입니다. <br> 
                                    기본 자세는 몸을 최대한 곧게 펴고, 배꼽이 물 밖으로 나오도록 몸을 띄우는 것입니다.</td>
                            </tr>
                        </table></a>

                        <a href="#"><table width="100%" class="diary_content_list">
                            <tr>
                                <td><span class="diary_category_label_01_diary">나의기록</span>2024.12.31(목)</td>
                                <td rowspan="3"><img src="resources/img/diary_sample_img.png" id="diary_img"></td>
                            </tr>
                            <tr>
                                <td class="diary_title">오늘의 운동정보: 배영</td>
                            </tr>
                            <tr>
                                <td>배영은 물 위에 등을 대고 누운 자세로 물을 가르는 수영 방식입니다. <br>
                                    초보자에게 적합한 이유는 호흡이 자유롭고 비교적 배우기 쉽기 때문입니다. <br> 
                                    기본 자세는 몸을 최대한 곧게 펴고, 배꼽이 물 밖으로 나오도록 몸을 띄우는 것입니다.</td>
                            </tr>
                        </table></a>


                        
                    <div class="pagination">
                        <!-- 왼쪽 하단: 페이지 정보 -->
                        <div class="page-info">
                            <span id="page-info">1</span>/<span id="total-pages">20Page</span>
                        </div>
            
                        <!-- 오른쪽 하단: 이전 및 다음 버튼 -->
                        <div class="page-buttons">
                            <button id="prev-page" class="pagination-btn"><</button>
                            <button id="next-page" class="pagination-btn">></button>
                        </div>
                    </div>
                </div>
            
</body>
</html>