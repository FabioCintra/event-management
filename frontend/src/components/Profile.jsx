import { useContext } from "react";
import { AuthContext } from "../store/authentication-context";
import { useNavigate } from "react-router-dom";

export default function Profile() {
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    return (
        <div className="min-h-screen w-full bg-[#eeeeee] relative flex flex-col items-center">
            <header className="w-full h-[85px] relative flex items-center px-10 ">
                <button
                    onClick={() => navigate("/home", {replace: true})}
                    className="flex items-center justify-center hover:opacity-70 transition-opacity"
                >
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={2.5}
                        stroke="black"
                        className="w-8 h-8"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M15.75 19.5L8.25 12l7.5-7.5"
                        />
                    </svg>
                </button>
            </header>

            <main className="w-full max-w-[1000px] px-4 flex flex-col items-center mt-4">
                <div className="w-full bg-white rounded-md shadow-sm p-10 flex flex-col gap-8">
                    <h2 className="text-2xl font-bold text-[#8c210f] border-b pb-4">Meu Perfil</h2>
                    
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-y-8 gap-x-12">
                        <div className="flex flex-col gap-1">
                            <span className="text-gray-400 text-xs font-bold uppercase tracking-wider">Nome</span>
                            <p className="text-lg font-medium text-gray-800">{user?.name || "N/A"}</p>
                        </div>

                        <div className="flex flex-col gap-1">
                            <span className="text-gray-400 text-xs font-bold uppercase tracking-wider">ID</span>
                            <p className="text-lg font-medium text-gray-800">{user?.id || "N/A"}</p>
                        </div>

                        <div className="flex flex-col gap-1">
                            <span className="text-gray-400 text-xs font-bold uppercase tracking-wider">Email</span>
                            <p className="text-lg font-medium text-gray-800">{user?.email || "N/A"}</p>
                        </div>

                        <div className="flex flex-col gap-1">
                            <span className="text-gray-400 text-xs font-bold uppercase tracking-wider">Grupos</span>
                            <p className="text-lg font-medium text-gray-800">
                                {user?.role && user.role.length > 0 ? user.role.join(", ") : "Nenhum grupo"}
                            </p>
                        </div>

                        <div className="flex flex-col gap-1">
                            <span className="text-gray-400 text-xs font-bold uppercase tracking-wider">Data de Criação</span>
                            <p className="text-lg font-medium text-gray-800">
                                {user?.createdAt 
                                    ? new Date(user.createdAt).toLocaleDateString("pt-BR") 
                                    : "N/A"}
                            </p>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}
