import { useEffect, useState } from "react";
import OrderItem from "./OrderItem";

export default function OrderItemShow(){

    const [page, setPage] = useState(0);
    const [orderItems, setOrderItems] = useState(null);
    const [items, setItems] = useState([]);
    const [totPages, setTotPages] = useState(10);
    const cssButtonsPage = "px-3 py-1 bg-white rounded-md shadow disabled:opacity-40";

    useEffect(() => {
        async function loadOrderItems(){
            try{
                const response = await fetch(`http://localhost:8080/events?page=${page}`,{
                    method:"GET",
                    credentials:"include",
                });

                const {content,totalPages} = await response.json();
                console.log(content);
                setItems(content);
                setTotPages(totalPages);
            }
            catch(error){
                
            }
        }
        loadOrderItems();
    }, [page])

    function handlePage(indexPage){
        if(page >= 0 && indexPage <= totPages){ // botar tambem com base na  quantia qu eeu receber do back
            setPage(indexPage);
        } 
    }

    return (
        <main className="w-full flex flex-col items-center justify-between">
            <div className="w-full max-w-[1000px] grid grid-cols-5 gap-3 px-4">
                {items 
                    ? items.map(item => <OrderItem key={item.id} product={item} />)
                    : <p>VAZIO...</p>
                }
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