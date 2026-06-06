import { createContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext({
    user:{
        id:"",
        name:"",
        email:"",
        createdAt:"",
        role:[]
    },
    login:() => {},
    logout:() => {},
});

export default function AuthContextProvider({children}){
    const [infos, setInfos] = useState(null);
    const navigate = useNavigate();

    function handleInfos({id,name,email,createdAt,role}){
        setInfos({
            id,
            name,
            email,
            createdAt,  
            role
        });
    }

    async function logout() {
        try{
            const response = await fetch("http://localhost:8080/auth/logout", {
                method:"POST",
                credentials:"include"   
            });
            
            setInfos(null);
            navigate("/login", { replace: true });
        }catch(error){
            console.log(error);
        }
    }

    /*
    Verifica com o backend se o usuario esta authenticado, se estiver, seta as informacoes que vao ser usadas pelo front
    se nao manda para o login
    */
    useEffect(() => {
        async function getAuthentication(){
            try{
                const response = await fetch("http://localhost:8080/auth/me", {
                    method:"POST",
                    credentials:"include"
                })
                
                if(!response.ok){
                    navigate("/login",{replace: true});
                }
                else{
                    const userData = await response.json();
                    handleInfos(userData);
                    navigate("/home");
                }
            }
            catch(error){
                logout();
            }
        }

        getAuthentication();
    }, [])

    const ctxValue = {
        user: infos,
        login: handleInfos,
        logout: logout
    }

    return(
        <AuthContext.Provider value={ctxValue}>
            {children}
        </AuthContext.Provider>
    );
}