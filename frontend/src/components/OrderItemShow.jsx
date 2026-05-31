import { useState } from "react";
import OrderItem from "./OrderItem";

export default function OrderItemShow(){

    const [page, setPage] = useState(0);
    const cssButtonsPage = "px-3 py-1 bg-white rounded-md shadow disabled:opacity-40";

    function handlePage(indexPage){
        if(page >= 0){ // botar tambem com base na  quantia qu eeu receber do back
            setPage(indexPage);
        } 
    }

    return (
        <main className="w-full flex flex-col items-center justify-between">
            <div className="w-full max-w-[1000px] grid grid-cols-5 gap-3 px-4">
                <OrderItem />
            </div>
            <div className="fixed bottom-6 left-1/2 -translate-x-1/2 flex gap-3">
                <button className={cssButtonsPage} onClick={() => handlePage(page - 1)}>
                    Anteiror
                </button>
                
                <button className={cssButtonsPage} onClick={() => handlePage(page + 1)}>
                    Proximo
                </button>
            </div>
        </main> 
    );
}