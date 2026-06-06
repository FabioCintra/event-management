
export default function OrderBoxExhibition({ id, createdDate, paidAt, status, totalValue }) {
    return (
        <button 
            className="w-[96%] mx-auto bg-white rounded-xl shadow-sm border border-gray-100 p-5 flex justify-between items-center my-4 hover:shadow-md transition-all active:scale-[0.98] text-left cursor-pointer"
        >
            {/* Canto Superior Esquerdo: ID e Data abaixo */}
            <div className="flex flex-col gap-1">
                <span className="text-xs font-bold text-gray-400 tracking-wider uppercase">
                    ID: {id}
                </span>
                <span className="text-lg font-semibold text-gray-700">
                    {createdDate}
                </span>
                {paidAt && 
                    <span className="text-lg font-semibold text-gray-700">
                        {paidAt}
                    </span> 
                }
            </div>

            {/* Canto Superior Direito: Status e Valor abaixo */}
            <div className="flex flex-col items-end gap-1">
                <div className="px-3 py-1 rounded-full bg-blue-50 text-blue-600 text-xs font-bold uppercase tracking-tight">
                    {status}
                </div>
                <span className="text-xl font-black text-[#8c210f]">
                    R$ {totalValue}
                </span>
            </div>
        </button>
    );
}
