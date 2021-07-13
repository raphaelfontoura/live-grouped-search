import DonutChart from "components/DonutChart";
import ImgDsDark from "./assets/img/ds-dark.svg";

function App() {
  return (
    <div className="main-container">
      <div className="container">
        <img src={ImgDsDark} alt="DevSuperior" height="40" />
        <h1>Spring & React</h1>
        <h2>Estudo de caso busca agrupada</h2>
        <DonutChart />
      </div>
    </div>
  );
}

export default App;
