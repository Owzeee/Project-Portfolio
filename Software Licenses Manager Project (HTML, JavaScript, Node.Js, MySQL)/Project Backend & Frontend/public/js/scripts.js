// BAR CHART LAYOUT WITH ARBITRARY DATA
const barChartOptions = {
  series: [
    {
      data: [10, 8, 6, 4, 2],
    },
  ],
  chart: {
    type: 'bar',
    height: 350,
    toolbar: {
      show: false,
    },
  },
  colors: ['#0094CF', '#246dec', '#0094CF', '#246dec', '#0094CF'],
  plotOptions: {
    bar: {
      distributed: true,
      borderRadius: 4,
      horizontal: false,
      columnWidth: '40%',
    },
  },
  dataLabels: {
    enabled: false,
  },
  legend: {
    show: false,
  },
  xaxis: {
    categories: ['#1', '#2', '#3', '#4', '#5'],
  },
  yaxis: {
    title: {
      text: 'Count',
    },
  },
};

const barChart = new ApexCharts(
  document.querySelector('#bar-chart'),
  barChartOptions
);
barChart.render();

// GRAPH LAYOUT WITH ARBITRARY DATA
const areaChartOptions = {
  series: [
    {
      name: 'Series 1',
      data: [23, 36, 45, 51, 25, 23, 134],
    },
    {
      name: 'Series 2',
      data: [5, 10, 20, 35, 40, 50, 41],
    },
  ],
  chart: {
    height: 350,
    type: 'area',
    toolbar: {
      show: false,
    },
  },
  colors: ['#246dec', '#0094CF'],
  dataLabels: {
    enabled: false,
  },
  stroke: {
    curve: 'smooth',
  },
  labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
  markers: {
    size: 0,
  },
  yaxis: [
    {
      title: {
        text: 'Axis 1',
      },
    },
    {
      opposite: true,
      title: {
        text: 'Axis 2',
      },
    },
  ],
  tooltip: {
    shared: true,
    intersect: false,
  },
};

const areaChart = new ApexCharts(
  document.querySelector('#graph'),
  areaChartOptions
);
areaChart.render();