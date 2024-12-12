import { Conteiner, Form } from "./styled";
import { Link, useNavigate } from "react-router-dom";
import { useFormik } from "formik";
import { useState } from "react";
import image from "../../../../assets/ImageRegister.png";

export default function Register() {
  const [erro, setError] = useState("");
  const navegate = useNavigate();

  async function onSubmit(values: any) {
    console.log("Values: ", values);
    setError("");

    try {
      const url = import.meta.env.VITE_API_REGISTER;
      const response = await fetch(url, {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(values),
      });

      if (response.status === 201) {
        navegate("/");
      }
    } catch (error) {
      if ((error as any).status === 400) {
        setError("Invalid credentials");
      } else {
        console.log(error);
      }
    }
  }

  const formik = useFormik({
    initialValues: {
      username: "",
      email: "",
      password: "",
    },
    onSubmit,
  });

  return (
    <Conteiner>
      <div className="image">
        <img src={image} alt="Register" />
      </div>
      <div className="conteiner">
        <div className="content">
          <h1>Get Started Now</h1>
          <p>Enter your credentials to create your account</p>
          <Form onSubmit={formik.handleSubmit}>
            <label>Username</label>
            <input
              name="username"
              type="text"
              placeholder="Enter a username"
              value={formik.values.username}
              onChange={formik.handleChange}
            />
            <label>Email address: </label>
            <input
              type="email"
              placeholder="Enter your email"
              name="email"
              value={formik.values.email}
              onChange={formik.handleChange}
            />
            <label>Password</label>
            <input
              type="password"
              placeholder="Password"
              name="password"
              value={formik.values.password}
              onChange={formik.handleChange}
            />
            <p>{erro}</p>
            <button type="submit">Sign Up</button>
          </Form>
          <span>
            Have an account? <Link to="/">Log In</Link>
          </span>
        </div>
      </div>
    </Conteiner>
  );
}
