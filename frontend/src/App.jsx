import {Routes,Route} from "react-router-dom";
import Login from "./screens/Login";
import Home from "./screens/home";
import AuthContextProvider from "./store/authentication-context";
import ProtectRoute from "./components/ProtectRoute";
import Profile from "./components/Profile";
import MyOrders from "./components/MyOrders";

function App() {


  return (
    <AuthContextProvider>
      <Routes>
        <Route path="/"/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/home" element={<ProtectRoute><Home/></ProtectRoute>}/>
        <Route path="/profile" element={<ProtectRoute><Profile /></ProtectRoute>}/>
        <Route path="/my-orders" element={<ProtectRoute><MyOrders /></ProtectRoute>}/>
      </Routes>
    </AuthContextProvider>
  )
}

export default App
