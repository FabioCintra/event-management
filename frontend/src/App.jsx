import {Routes,Route} from "react-router-dom";
import Login from "./screens/Login";
import Home from "./screens/home";
import AuthContextProvider from "./store/authentication-context";
import ProtectRoute from "./components/ProtectRoute";
import Profile from "./components/Profile";

function App() {


  return (
    <AuthContextProvider>
      <Routes>
        <Route path="/"/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/home" element={<ProtectRoute><Home/></ProtectRoute>}/>
        <Route path="/profile" element={<ProtectRoute><Profile /></ProtectRoute>}/>
      </Routes>
    </AuthContextProvider>
  )
}

export default App
