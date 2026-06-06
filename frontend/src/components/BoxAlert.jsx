import { useEffect } from "react";

export default function BoxAlert({title,message,onClose}){

    useEffect(() => {
        setTimeout(() => onClose() , 5000);
    },[])

    return(
        <div className="fixed top-5 right-5 w-72 bg-red-100 border-l-4 border-red-500 p-4 shadow-lg z-50">    
            <div className="flex justify-between items-start">
                <div>
                    <h3 className="font-bold text-red-800">{title}</h3>
                    <p className="text-sm text-red-700">{message}</p>
                </div>
                <button onClick={onClose} className="text-red-500 hover:text-red-800">✕</button>
            </div>
        </div>
    );
}