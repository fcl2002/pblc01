import ReactDOM from "react-dom/client";
import { GlobalProvider } from "./global/context/msg";

import Global_styles from "./global/styles/global_styles";
import App from "./global/App";
import AuthProvider from "react-auth-kit";
import createStore from "react-auth-kit/createStore";
import { refresh } from "./global/utils/refresh";

const store = createStore({
  authName: "token",
  authType: "cookie",
  cookieDomain: window.location.hostname,
  cookieSecure: window.location.protocol === "https:",
  refresh: refresh,
});

ReactDOM.createRoot(document.getElementById("root")!).render(
  <>
    <Global_styles />
    <GlobalProvider>
      <AuthProvider store={store}>
        <App />
      </AuthProvider>
    </GlobalProvider>
  </>
);
