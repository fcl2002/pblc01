import { Bar_lat } from "./styled";
import { IoIosLogOut } from "react-icons/io";
import { Link, useNavigate } from "react-router-dom";
import { MdDashboard } from "react-icons/md";
import { IoMdAnalytics, IoMdSettings } from "react-icons/io";
import { IoWalletSharp } from "react-icons/io5";

import avatar01 from "../../../../assets/avatar01.jpg";
import avatar02 from "../../../../assets/avatar02.jpg";
import avatar03 from "../../../../assets/avatar03.jpg";

import useAuthUser from "react-auth-kit/hooks/useAuthUser";
import useSignOut from "react-auth-kit/hooks/useSignOut";

import img from "../../../../../public/Group 7010.png";

export default function BarLat() {
  const navegate = useNavigate();
  const auth: any = useAuthUser();
  const signOut = useSignOut();

  const avatars = [avatar01, avatar02, avatar03];

  return (
    <Bar_lat>
      <h2>
        <span>
          <img src={img} alt="logo" />
        </span>
        <span style={{ fontWeight: 'bold', color: "white" }}> Wealth</span>
        <span style={{ fontWeight: 'normal', color: "white"}}>Wise</span>
      </h2>


      {/* <div className="profile">
        <img
          src={avatars[Math.floor(Math.random() * avatars.length)]}
          alt="avatar porfile"
        />
        <h3>carlos</h3>
      </div> */}

      <div className="conteiner_button">
        <Link to="/home/" className="button active">
          <MdDashboard className="icon" />
          <p>Dashboard</p>
        </Link>

        <Link to="/home/" className="button">
          <IoMdAnalytics className="icon" />
          <p>Analytics</p>
        </Link>

        <Link to="/home/wallet" className="button">
          <IoWalletSharp className="icon" />
          <p>My Wallets</p>
        </Link>

        <Link to="/home/settings" className="button">
          <IoMdSettings className="icon" />
          <p>Settings</p>
        </Link>

        <button
          className="button end"
          onClick={() => {
            signOut();
            navegate("/");
          }}
        >
          <IoIosLogOut className="icon" style={{color: "#FFF8C5"}}/>
          <p style={{color: "#FFF8C5"}}>Log Out</p>
        </button>
      </div>
    </Bar_lat>
  );
}
