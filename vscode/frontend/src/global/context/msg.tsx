import { createContext, useContext, useState } from "react";

// Define a interface para o contexto
interface GlobalContextType {
  msg: string;
  user: string;
  setMsg: React.Dispatch<React.SetStateAction<string>>;
  setUser: React.Dispatch<React.SetStateAction<string>>;
}

// Cria o Contexto com valor inicial `undefined`
const GlobalContext = createContext<GlobalContextType>(" " as any);

// Provedor do contexto
export const GlobalProvider = ({ children }: any) => {
  const [msg, setMsg] = useState("");
  const [user, setUser] = useState("");

  return (
    <GlobalContext.Provider value={{ msg, setMsg, user, setUser }}>
      {children}
    </GlobalContext.Provider>
  );
};

// Hook para usar o contexto
export const useGlobalContext = () => {
  return useContext(GlobalContext);
};
