import { useContext } from "react";
import { AuthContext } from "../store/authentication-context";

export default function ProtectRoute({children}){
    const infos  = useContext(AuthContext);

    if (!infos) {
        return <Navigate to="/login" replace />;
    }

    return children;
}