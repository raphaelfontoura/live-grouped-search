import axios from "axios";
import { useState, useEffect } from "react";
import Chart from "react-apexcharts";
import { SaleByStore } from "types/sale";

type ChartData = {
  labels: string[];
  series: number[];
};

const DonutChart = () => {

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    series: [],
  });

  useEffect(() => {

    axios.get("http://localhost:8080/sales/by-store")
    .then((response) => {

        const data = response.data as SaleByStore[];
        const myLabels = data.map(x => x.storeName);
        const mySeries = data.map(x => x.sum);

        setChartData({
            series: mySeries,
            labels: myLabels
        })
    });
  }, []);

  const options = {
    legend: {
      show: true,
    },
  };

  return (
    <Chart
      options={{ ...options, labels: chartData.labels }}
      series={chartData.series}
      type="donut"
      height="240"
    />
  );
};

export default DonutChart;
