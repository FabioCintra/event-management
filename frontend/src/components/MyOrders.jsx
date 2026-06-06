import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../store/authentication-context";
import { useNavigate } from "react-router-dom";
import ButtonOrderItem from "./ButtonOrderItem";
import OrderBoxExibition from "./OrderBoxExibition";

export default function MyOrders(){
    const authContext = useContext(AuthContext);
    const navigate = useNavigate();
    const [myOrders, setMyOrders] = useState([]);
    const [totPages, setTotPages] = useState(0);
    const [page, setPage] = useState(0);

    function handlePages(indexPage){
        if(indexPage >= 0 && indexPage < totPages){ 
            setPage(indexPage);
        } 
    }

    useEffect(() => {
        async function getOrders(){
            try{
                const response = await fetch(`http://localhost:8080/orders?page=${page}`, {
                    method:"GET",
                    credentials:"include"
                });

                if(!response.ok){
                    authContext.logout();
                }

                const {content, totalPages} = await response.json();
                setMyOrders(content);
                setTotPages(totalPages);
            }   
            catch(error){
                console.log(error);
            }
        }

        getOrders();
    },[page])

    return(
        <div className="min-h-screen bg-[#eeeeee] pb-10">
            <header className="w-full h-[85px] flex items-center px-6 md:px-10 bg-white shadow-sm mb-6">
                <button
                    onClick={() => navigate("/home", {replace: true})}
                    className="flex items-center justify-center hover:opacity-70 transition-opacity p-2 rounded-full hover:bg-gray-100"
                >
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={2.5}
                        stroke="black"
                        className="w-6 h-6 md:w-8 h-8"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M15.75 19.5L8.25 12l7.5-7.5"
                        />
                    </svg>
                </button>
                <h1 className="ml-4 text-xl md:text-2xl font-bold text-gray-800">Meus Pedidos</h1>
            </header>

            <main className="max-w-4xl mx-auto px-2">
                {myOrders.length === 0 
                    ? (
                    <div className="flex flex-col items-center justify-center mt-20 opacity-40">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-20 h-20 mb-4">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 10.5V6a3.75 3.75 0 1 0-7.5 0v4.5m11.356-1.993 1.263 12c.07.665-.45 1.243-1.119 1.243H4.25a1.125 1.125 0 0 1-1.12-1.243l1.264-12A1.125 1.125 0 0 1 5.513 7.5h12.974c.576 0 1.059.435 1.119 1.007ZM8.625 10.5a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm7.5 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Z" />
                        </svg>
                        <p className="text-xl font-bold uppercase tracking-widest">Ainda não há pedidos</p>
                    </div>
                ) : (
                    <div className="space-y-1">
                        {myOrders.map(order => (
                            <OrderBoxExibition 
                                key={order.id}
                                id={order.id} 
                                date={new Date(order.createdAt).toLocaleDateString('pt-BR')} 
                                status={order.status}
                                totalValue={order.totalAmount.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}
                            />
                        ))}
                    </div>
                )}
            </main>

            {totPages > 1 && (
                <div className="mt-8 flex justify-center">
                    <ButtonOrderItem page={page} handlePage={handlePages} totalPages={totPages}/>
                </div>
            )}
        </div>
    );
}