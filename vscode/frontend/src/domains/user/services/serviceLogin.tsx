import { IUser } from "../interfaces/IUser";

async function login(
  data: IUser,
  setMsg: (message: string) => void,
  setUser: (userName: string) => void,
  setToken: (token: string) => void
) {
  try {
    const url = import.meta.env.VITE_API_LOGIN;
    const response = await fetch(url, {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    if (response.status === 200) {
      const userData = await response.json();
      setToken(userData.token);
      setUser(userData);
      return response.status; // Retorna o status 200
    } else {
      const errorData = await response.json();
      setMsg(errorData.message || "Erro na autenticação");
      return response.status; // Retorna o status de erro
    }
  } catch (error) {
    setMsg("Erro de rede. Verifique sua conexão e tente novamente.");
    console.error(error);
  }
}

export { login };
