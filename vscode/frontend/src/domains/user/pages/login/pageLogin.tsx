import { Conteiner, Form } from "./styled";
import { Link } from "react-router-dom";
import { useFormik } from "formik";
import { useState } from "react";
import useSignIn from "react-auth-kit/hooks/useSignIn";
import { useNavigate } from "react-router-dom";
import image from "../../../../assets/ImageLogin.png";

export default function Login() {
  const [error, setError] = useState("");
  // const [token, setToken] = useState("");
  // const [userId, setUserId] = useState("");
  // const [username, setUsername] = useState("");
  // const [email, setEmail] = useState("");
  const signIn = useSignIn();
  const navigate = useNavigate();

  async function onSubmit(values: any) {
    console.log("Values: ", values);
    setError("");

    try {
      const url = import.meta.env.VITE_API_LOGIN;
      const response = await fetch(url, {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(values),
      });

      const parseResp = await response.json();
      console.log("Response: ", parseResp);

      // setUserId (parseResp.data.id);
      // setUsername(parseResp.data.username);
      // setEmail(parseResp.data.email);
      // setToken(parseResp.token);

      const sign = signIn({
        auth: {
          token: parseResp.token,
          type: "Bearer",
          
        },
        userState: {
          token: parseResp.token,
        },
        refresh: parseResp.token,
      });

      if (sign) {
        navigate("/home");
      } else {
        setError("Invalid credentials");
        throw new Error();
      }
    } catch (error) {
      console.log(error);
    }
  }

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    onSubmit,
  });

  return (
    <Conteiner>
      <div className="image">
        <img src={image} alt="Login" />
      </div>
      <div className="conteiner">
        <div className="content">
          <h1>Welcome Back!</h1>
          <p>Enter your credentials to access your account</p>
          <Form onSubmit={formik.handleSubmit}>
            <label htmlFor="username">Username</label>
            <input
              id="username"
              type="text"
              name="username"
              placeholder="Enter your username"
              value={formik.values.username}
              onChange={formik.handleChange}
              />
            <label htmlFor="password">Password:</label>
            <input
              id="password"
              name="password"
              type="password"
              placeholder="Password"
              value={formik.values.password}
              onChange={formik.handleChange}
              />
            {<p className="erro">{error}</p>}
            <button type="submit">Login</button>
          </Form>
          <span>
            Don't have an account? <Link to="/register">Sign Up</Link>
          </span>
        </div>
      </div>
    </Conteiner>
  );
}
