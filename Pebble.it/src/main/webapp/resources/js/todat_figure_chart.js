
    // 데이터 및 옵션 설정
    const data = {
      categories: ['일정', '할일', '기록'],
      series: [
        {
          name: 'Revenue',
          data: [1, 10, 5],
        },
      ],
    };

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
            '#DE9631',
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

    // 라인 차트 생성
    const chart = toastui.Chart.lineChart({
      el: document.getElementById('today_figure_chart'),
      data,
      options,
    });