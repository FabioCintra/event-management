import { useEffect, useState, useContext } from "react";
import OrderItem from "./OrderItem";
import { AuthContext } from "../store/authentication-context";
import ButtonOrderItem from "./ButtonOrderItem";

export default function OrderItemShow(){
    const authContext = useContext(AuthContext);
    const [page, setPage] = useState(0);
    const [orderItems, setOrderItems] = useState(null);
    const [items, setItems] = useState([]);
    const [totPages, setTotPages] = useState(0);

    useEffect(() => {
        async function loadOrderItems(){
            try{
                const response = await fetch(`http://localhost:8080/events?page=${page}`,{
                    method:"GET",
                    credentials:"include",
                });
                
                if(!response.ok){
                    authContext.logout();
                }

                const {content,totalPages} = await response.json();
                
                setItems(content);
                setTotPages(totalPages);
            }
            catch(error){
                
            }
        }
        loadOrderItems();
    }, [page])

    function handlePages(indexPage){
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
            <ButtonOrderItem page={page} handlePage={handlePages}/>
        </main> 
    );
}