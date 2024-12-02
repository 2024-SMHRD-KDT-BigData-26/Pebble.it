const el = document.getElementById('bar-chart');
const data = {
    categories: ['일정', '기록', '할일'],
    series: [
        {
            name: '일정',
            data: [20, 13, 22],
        },
    ],
};
const options = {
    chart: { width: 650, height: 210 },
    series: {
        stack: {
            type: 'normal',
        },
    },
    legend: {
        visible: false, // 범례 제거
    },
    exportMenu: {
        visible: false, // export 버튼 제거
    },
    theme: {
        series: {
            linewidth: 5,
            colors: ['#DE9631']
        }
    }


};

const chart = toastui.Chart.barChart({ el, data, options });