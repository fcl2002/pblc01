import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "../../domains/user/pages/login/pageLogin.tsx";
import Register from "../../domains/user/pages/register/pageRegister";
import Settings from "../../domains/money/pages/settings/settings";
import Home from "../../domains/money/pages/home/home";
import Wallet from "../../domains/money/pages/wallet/wallet.tsx";

import AuthOutlet from "@auth-kit/react-router/AuthOutlet";

function route() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<AuthOutlet fallbackPath="/" />}>
          <Route element={<Home />}>
            <Route path="wallet" element={<Wallet />} />
            <Route path="settings" element={<Settings />} />
          </Route>
        </Route>
      </Routes>
    </Router>
  );
}

export default route;
