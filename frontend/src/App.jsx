import {Routes,Route} from "react-router-dom";
import Login from "./screens/Login";
import Home from "./screens/home";

function App() {


  return (
    <Routes>
      <Route path="/" />
      <Route path="/login" element={<Login/>}/>
      <Route path="/home" element={<Home/>}/>
    </Routes>
  )
}

export default App
