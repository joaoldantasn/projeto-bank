import './global.css';
import { PrimeReactCSS } from "primereact/resources/themes/saga-green/theme.css";
import { PrimeReactMinCsss } from "primereact/resources/primereact.min.css";
import { Icons } from "primeicons/primeicons.css";
import 'primeflex/primeflex.css';
import Routes from './routes';
import Cliente from './Pages/Cliente';

//import "primereact/resources/themes/lara-light-blue/theme.css";
//import "primereact/resources/themes/lara-dark-blue/theme.css";
//import "primereact/resources/themes/saga-blue/theme.css";
//import "primereact/resources/themes/saga-orange/theme.css";

function App() {
  return (
    <div>
      <Routes></Routes>
      {/* <Cliente/> */}
    </div>
  );
}

export default App;
