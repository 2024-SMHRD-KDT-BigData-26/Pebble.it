const data = {
    categories: ['월', '화', '수', '목', '금', '토', '일'],
    series: [
      {
        name: '지난주',
        data: [5000, 3000, 5000, 7000, 6000, 4000, 1000]
      },
      {
        name: '이번주',
        data: [8000, 4000, 7000, 2000, 6000, 3000, 5000]
      },
    ]
  }

  const options = {
    chart: {
      width: 580,
      height: 285,
    },
    xAxis: {
      title: '',
    },
    yAxis: {
      title: '개수',
    },
    series: {
      showDot: true,
      spline: true, // 스플라인 곡선 스타일

    },
    theme: {
      series: {
        lineWidth: 5,
        colors: [
          '#F2D5AD', '#DE9631',
        ]
      }
    },
    legend: {
      visible: false, // 범례 제거
    },
    exportMenu: {
      visible: false, // export 버튼 제거
    },
  };

  // 컬럼 차트 생성
  const chart = toastui.Chart.columnChart({
    el: document.getElementById('weekly_todo_figure_chart'),
    data,
    options,
  });