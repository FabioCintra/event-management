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
                        className=" absolute right-10 top-8 flex flex-col gap-[4px]"
                    >
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                        <span className="w-6 h-[2px] bg-black rounded-full"></span>
                    </button>
                </div>
            </header>
            {<OrderItemShow />}
            {sidebarIsOpen && <SideBar setSidebarIsOpen={setSidebarIsOpen}/>}
        </div>
    );
}