import { useRef, useState } from "react";
import book from "../assets/book.png";

const USERNAME_REGEX = /^[a-zA-Z0-9._-]+@gmail\.com$/

export default function Login(){

    const [usernameIsWrong, setUsernameIsWrong] = useState(false);
    const [passwordIsWrong, setPasswordIsWrong] = useState(false);
    const getPassword = useRef();
    const getUsername = useRef();

    const outlineUsername = usernameIsWrong ? "outline-red-600 outline-2" : "outline-none";
    const outlinePassword = passwordIsWrong ? "outline-red-600 outline-2" : "outline-none";

    function checkInformations(event, username, password){
        
        if(!username && !password){
            event.preventDefault(); // impede o envio imediato
            setPasswordIsWrong(true);
            setUsernameIsWrong(true);
            return;
        }

        if(!username || !USERNAME_REGEX.test(username)){
            event.preventDefault(); // impede o envio imediato
            setUsernameIsWrong(true);
            return;
        }
        if(!password || password.legth < 8){
            event.preventDefault(); // impede o envio imediato
            setPasswordIsWrong(true);
            return;
        }
        
    }
    
    return(
        <div className="min-h-screen w-full bg-[#eeeeee] flex items-center justify-center">
            <div className="w-[345px] h-[430px] bg-white rounded-lg shadow-md flex flex-col items-center px-[26px] pt-[18px]">
                <img src={book} className="w-[70px] h-[70px] rounded-full bg-[#d9d9d9] mb-[28px]" />

                <h1 className="text-[26px] font-extrabold text-center leading-[31px] text-black">
                    BEM VINDO AO<br />
                    GESTOR DE EVENTOS
                </h1>

                <form action="http://localhost:8080/login" method="POST" onSubmit={(event) => checkInformations(event, getUsername.current.value, getPassword.current.value)}className="w-full mt-[40px]">
                    <div className="flex flex-col gap-[31px]">
                        <label>
                            {usernameIsWrong && "Username invalid!"}
                            <input ref={getUsername} placeholder="EX:anaclara@gmail.com" id="username" name="username" type="text" className={`w-full h-[35px] bg-[#d9d9d9] ${outlineUsername} rounded-[9px] px-3 focus:bg-white focus:ring-1 focus:ring-[#d9d9d9]`} />
                        </label>
                        
                        
                        <label>
                            {passwordIsWrong && "Password invalid!"}
                            <input ref={getPassword} id="password" name="password" type="password" className={`w-full h-[35px] bg-[#d9d9d9] rounded-[9px] px-3 ${outlinePassword} focus:bg-white focus:ring-1 focus:ring-[#d9d9d9]`} />
                        </label>
                        
                    </div>

                    <div className="flex justify-end gap-[17px] mt-[80px]">
                        <button className="w-[80px] h-[25px] bg-[#8c210f] text-white text-[10px] font-bold rounded-[10px] hover:bg-[#A93B20]">
                            CADASTRAR
                        </button>

                        <button type="submit" className="w-[80px] h-[25px] bg-[#8c210f] text-white text-[10px] font-bold rounded-[10px] hover:bg-[#A93B20]">
                            LOGIN
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}