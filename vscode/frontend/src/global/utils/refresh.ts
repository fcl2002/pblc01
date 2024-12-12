import createRefresh from "react-auth-kit/createRefresh";

const refresh = createRefresh({
  interval: 10, // O tempo em segundos para atualizar o token de acesso
  refreshApiCallback: async (param) => {
    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${param.refreshToken}`,
        },
      });

      if (!response.ok) {
        throw new Error("Failed to refresh token");
      }

      const data = await response.json();

      return {
        isSuccess: true, // Indica que o token foi atualizado com sucesso
        newAuthToken: data.newAuthToken, // O novo token de autenticação
        newRefreshToken: data.newRefreshToken, // (Opcional) Novo token de atualização, se disponível
        newAuthTokenExpiresIn: data.expiresIn || 60 * 60 * 24 * 7, // (Opcional) Tempo de expiração do novo token em segundos
      };
    } catch (error) {
      console.error("Failed to refresh token:", error);
      return {
        isSuccess: false, // Indica que a atualização do token falhou
        newAuthToken: "", // Necessário para manter a tipagem correta
      };
    }
  },
});

export { refresh };
