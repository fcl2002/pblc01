import { Bar_title } from "./styled";
import { useNavigate } from "react-router-dom";

export default function card() {
  const navigate = useNavigate();

  function handle() {
    navigate("/home/addMoney");
  }
  return (
    <Bar_title>
      <div className="aux">
        <p>Moneylender List</p>

        <button onClick={handle}>ADD NEW</button>
      </div>

      <div className="line"></div>
    </Bar_title>
  );
}
