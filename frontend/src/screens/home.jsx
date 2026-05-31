import { useState } from "react";
import OrderItem from "../components/OrderItem";
import OrderItemShow from "../components/OrderItemShow";
export default function Home(){
    const [sidebarIsOpen, setSidebarIsOpen] = useState(false);
    return (
        <div className="min-h-screen w-full bg-[#eeeeee] relative">
            <header className="w-full h-[85px] relative flex justify-end px-10 ">
                <div className="w-full flex flex-row items-center gap-3">
                    <input
                        type="search"
                        placeholder="Pesquisar"
                        className="block w-[70%] h-[44px] mx-auto bg-white rounded-md px-4 outline-none shadow-sm focus:ring focus:ring-[#8c210f]"
                    />

                    <button
                        onClick={() => setSidebarIsOpen(true)}
                        className=" absolute right-10 top-8 flex flex-col gap-[4px]"
                    >
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                    </button>
                </div>
            </header>
            <OrderItemShow />
            {sidebarIsOpen && (
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
                            <button className="text-left">Perfil</button>
                            <button className="text-left">Meus pedidos</button>
                            <button className="text-left">Configurações</button>
                            <button className="text-left">Sair</button>
                        </nav>
                    </aside>
                </div>
            )}
        </div>
    );
}