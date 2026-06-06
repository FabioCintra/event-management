import { useContext } from "react";
import { AuthContext } from "../store/authentication-context";
import { replace, useNavigate } from "react-router-dom";

export default function SideBar({setSidebarIsOpen}){
    const authContext = useContext(AuthContext);
    const navigate = useNavigate();

    function navigateProfile(){
        navigate("/profile", {replace: true});    
    }

    return(
        <div className="fixed inset-0 z-50 flex">
            <div
                onClick={() => setSidebarIsOpen(false)}
                className="absolute inset-0 bg-black/50"
            />

            <aside className="relative ml-auto w-[230px] h-full bg-white shadow-lg p-5">
                <button
                    onClick={() => setSidebarIsOpen(false)}
                    className="mb-6 text-sm font-bold text-[#8c210f]"
                >
                    FECHAR
                </button>

                <nav className="flex flex-col gap-4 text-sm font-semibold">
                    <button onClick={() => navigate("/profile", {replace: true})} className="text-left">Perfil</button>
                    <button onClick={() => navigate("/my-orders", {replace:true})}className="text-left">Meus pedidos</button>
                    <button className="text-left">Configurações</button>
                    <button onClick={() => authContext.logout()} className="text-left hover:text-red-400">Sair</button>
                </nav>
            </aside>
        </div>
    );
}