import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../store/authentication-context";
import { useNavigate } from "react-router-dom";

export default function SideBar({setSidebarIsOpen}){
    const authContext = useContext(AuthContext);
    const navigate = useNavigate();
    const [isVisible, setIsVisible] = useState(false);

    useEffect(() => {
        // Ativa a animação de entrada após o componente montar
        setIsVisible(true);
    }, []);

    function handleClose() {
        setIsVisible(false);
        // Espera a animação de saída terminar antes de desmontar o componente
        setTimeout(() => {
            setSidebarIsOpen(false);
        }, 300);
    }

    return(
        <div className="fixed inset-0 z-50 flex overflow-hidden">
            {/* Overlay com transição de opacidade */}
            <div
                onClick={handleClose}
                className={`absolute inset-0 bg-black transition-opacity duration-300 ease-in-out ${
                    isVisible ? "opacity-50" : "opacity-0"
                }`}
            />

            {/* Sidebar com transição de slide */}
            <aside 
                className={`relative ml-auto w-[280px] h-full bg-white shadow-2xl p-6 transition-transform duration-300 ease-in-out transform ${
                    isVisible ? "translate-x-0" : "translate-x-full"
                }`}
            >
                {/* Botão de Fechar Melhorado (X no canto superior) */}
                <div className="flex justify-end mb-8">
                    <button
                        onClick={handleClose}
                        className="p-2 rounded-full hover:bg-gray-100 text-gray-500 hover:text-[#8c210f] transition-all"
                        aria-label="Fechar menu"
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                </div>

                <nav className="flex flex-col gap-2 text-base font-semibold">
                    <button 
                        onClick={() => { navigate("/profile", {replace: true}); handleClose(); }} 
                        className="flex items-center gap-3 text-left p-3 rounded-xl hover:bg-gray-100 transition-colors text-gray-700 hover:text-black"
                    >
                        Perfil
                    </button>
                    <button 
                        onClick={() => { navigate("/my-orders", {replace:true}); handleClose(); }} 
                        className="flex items-center gap-3 text-left p-3 rounded-xl hover:bg-gray-100 transition-colors text-gray-700 hover:text-black"
                    >
                        Meus pedidos
                    </button>
                    <button className="flex items-center gap-3 text-left p-3 rounded-xl hover:bg-gray-100 transition-colors text-gray-700 hover:text-black">
                        Configurações
                    </button>
                    
                    <button 
                        onClick={() => authContext.logout()} 
                        className="flex items-center gap-3 text-left p-3 rounded-xl hover:text-red-600 transition-colors mt-8 border-t border-gray-100 pt-6 text-red-500"
                    >
                        Sair
                    </button>
                </nav>
            </aside>
        </div>
    );
}