export default function OrderItem(){
   return (
        <button className="w-full bg-white rounded-xl shadow-sm hover:shadow-md transition overflow-hidden text-left">
            <div className="w-full h-[110px] bg-[#d9d9d9] items-center justify-center">
                <img
                    src=""
                    alt="Produto"
                    className="w-full h-full object-cover"
                />
            </div>

            <div className="p-3">
                <h3 className="text-sm font-bold text-black line-clamp-2">
                    NOME DO PRODUTO
                </h3>

                <div className="mt-3 flex justify-between items-center gap-2">
                    <p className="text-xs text-gray-500">
                        LOCALIZAÇÃO
                    </p>

                    <p className="text-sm font-bold text-[#8c210f]">
                        R$ 00,00
                    </p>
                </div>
            </div>
        </button>
    );
}