import { useState } from "react";
import { ThemeProvider } from "styled-components";

//themes
import Light from "./styles/theme/light";
import Dark from "./styles/theme/dark";
//pages
import Route from "./routes/route";

function App() {
  const [theme] = useState(Dark);

  return (
    <ThemeProvider theme={theme}>
      <Route />
    </ThemeProvider>
  );
}

export default App;
