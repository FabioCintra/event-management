import { useState, useContext } from "react";
import OrderItem from "../components/OrderItem";
import OrderItemShow from "../components/OrderItemShow";
import { AuthContext } from "../store/authentication-context";
import SideBar from "../components/SideBar";

export default function Home(){
    const [sidebarIsOpen, setSidebarIsOpen] = useState(false);
    const authContext = useContext(AuthContext);

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
                        className="absolute right-10 top-6 group flex items-center justify-center bg-white p-3 rounded-xl shadow-sm hover:shadow-md transition-all duration-300 overflow-hidden"
                    >
                        <div className="flex items-center gap-2">
                            {/* Container das Barrinhas */}
                            <div className="flex flex-col gap-[4px] transition-transform duration-300 group-hover:-translate-x-1">
                                <span className="w-5 h-[2px] bg-black rounded-full transition-all duration-300 group-hover:bg-[#8c210f]"></span>
                                <span className="w-5 h-[2px] bg-black rounded-full transition-all duration-300 group-hover:bg-[#8c210f]"></span>
                                <span className="w-5 h-[2px] bg-black rounded-full transition-all duration-300 group-hover:bg-[#8c210f]"></span>
                            </div>
                            
                            {/* Texto Menu que aparece */}
                            <span className="max-w-0 overflow-hidden text-[#8c210f] font-bold text-xs uppercase tracking-widest transition-all duration-300 group-hover:max-w-[50px] opacity-0 group-hover:opacity-100 whitespace-nowrap">
                                Menu
                            </span>
                        </div>
                    </button>
                </div>
            </header>
            {<OrderItemShow />}
            {sidebarIsOpen && <SideBar setSidebarIsOpen={setSidebarIsOpen}/>}
        </div>
    );
}