import { Conteiner, Header, StyledErrorMessage } from "./styled";
import { Field, Form, Formik } from "formik";
import useAuthUser from "react-auth-kit/hooks/useAuthUser";
import useSignOut from "react-auth-kit/hooks/useSignOut";
import { useNavigate } from "react-router-dom";
import BarLat from "../../components/bar_lat/bar_lat";
import img from "../../../../assets/avatar01.jpg";

interface FormValues {
  id: number;
  username: string;
  email: string;
  password: string;
}

const initialValues: FormValues = {
  id: 999,
  email: "",
  username: "",
  password: "",
};

export default function Settings() {
  const navigate = useNavigate();
  const auth: any = useAuthUser();
  const SignOut: any = useSignOut();

  async function handleSubmitAlter(values: FormValues) {
    values.id = auth.userId;
    console.log(values);
    const url = import.meta.env.VITE_API_ALTER_USERS;
    const response = await fetch(url, {
      method: "PUT",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });

    if (response.status === 200) {
      alert("User altered");
    } else {
      alert("Error");
    }
  }

  async function handleSubmitDelete() {
    const values = { id: auth.userId };

    console.log(values);

    const url = import.meta.env.VITE_API_DELETE_USERS;
    const response = await fetch(url, {
      method: "DELETE",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });

    if (response.status === 200) {
      console.log("User deleted");
      SignOut();
      navigate("/");
    } else {
      alert("Error");
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "row", backgroundColor: "#1F1F1F",}}>
    <BarLat />
    <Conteiner style={{ marginTop: "100px", marginLeft: "100px", flex: 1 }}>
      <Header>
        <div>
          <img src={img} alt="User Profile" />
          <h1>Welcome Back!</h1>
        </div>
        
      </Header>

      <h3>Alter User</h3>
      <Formik initialValues={initialValues} onSubmit={handleSubmitAlter}>
        <Form className="form">
          <label htmlFor="username">Username</label>
          <Field name="username" type="text" />
          <StyledErrorMessage name="username" component="div" />

          <label htmlFor="value">Email: </label>
          <Field name="email" type="email" />
          <StyledErrorMessage name="email" component="div" />

          <label htmlFor="value">Password: </label>
          <Field name="password" type="password" />
          <StyledErrorMessage name="password" component="div" />

          <button type="submit">Submit</button>
        </Form>
      </Formik>

      <h3>Delete User</h3>
      <Formik initialValues={initialValues} onSubmit={handleSubmitDelete}>
        <Form className="form">
          <button type="submit" className="delete">
            Delete Account
          </button>
          <label className="info">This action is irreversible</label>
        </Form>
      </Formik>
    </Conteiner>
  </div>
);
}
