import './App.css';
import {Header} from "./components/header/Header";
import {Footer} from "./components/footer/Footer";
import {SensorList} from "./components/SensorList/SensorList";
import {PumpList} from "./components/PumpList/PumpList";

function App() {
    return (
        <div>
            <Header/>
            <div className="center">
                <SensorList/>
                <PumpList/>
            </div>
            <Footer/>
        </div>
    );
}

export default App;
