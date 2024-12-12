import { RiMoneyDollarBoxLine } from "react-icons/ri";
import { BiDollarCircle } from "react-icons/bi";

import { Card } from "./styled";
import { useEffect, useState } from "react";
import useAuthUser from "react-auth-kit/hooks/useAuthUser";

interface Count {
  debito: number;
  credito: number;
}

export default function card() {
  const [count, setCount] = useState<Count>({ debito: 0, credito: 0 });
  const auth: any = useAuthUser();

  async function handleCount() {
    try {
      const data = {
        userId: auth.userId,
      };
      const url = import.meta.env.VITE_API_COUNT_MONEYS;
      console.log(url);

      const response = await fetch(url, {
        method: "POST",
        credentials: "include", // Garante que cookies de sessão sejam enviados
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      const parseResp = await response.json();

      if (response.status === 200) {
        console.log(parseResp, "respostaaaaaaaaa");
        // Itera sobre os itens e formata a data de cada um
        setCount({
          debito: parseResp.data.debito._sum.value,
          credito: parseResp.data.credito._sum.value,
        });
      } else {
        console.log("erro na resposta de listar moneys !!", parseResp.message);
      }
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    // Aqui você chama a função que faz a requisição
    handleCount();
  }, []);

  return (
    <Card>
      <div className="card divida">
        <RiMoneyDollarBoxLine className="icon" />

        <h3>Payments</h3>
        <div className="value">
          <span>R$ </span>
          {count.debito}
        </div>
      </div>
      <div className="card credito">
        <BiDollarCircle className="icon" />
        <h3>buyments</h3>
        <div className="value">
          <span>R$ </span>
          {count.credito}
        </div>
      </div>
    </Card>
  );
}
