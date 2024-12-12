import { Conteiner, StyledErrorMessage, Header } from "./styled";
import BarLat from "../../components/bar_lat/bar_lat";
import img from "../../../../assets/avatar01.jpg";

import { Formik, Form, Field } from "formik";
import * as Yup from "yup";

import useAuthUser from "react-auth-kit/hooks/useAuthUser";

interface FormValues {
  id: number;
  name: string;
  risk: number;
}

const initialValues: FormValues = {
  id: 999,
  name: "",
  risk: 0,
};

const validationSchema = Yup.object({
  name: Yup.string().required("Name is required").min(3, "Name is too short"),
  risk: Yup.number()
    .required("Value is required")
    .min(0, "Value must be positive"),
});

export default function CreateMoney() {
  const auth: any = useAuthUser();

  async function handleSubmit(values: FormValues) {
    values.id = auth.userId;

    const url = import.meta.env.VITE_API_ADD_MONEYS;
    const response = await fetch(url, {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });

    if (response.status === 201) {
      console.log("Wallet created successfully");
      // Resetting form values
      initialValues.name = "";
      initialValues.risk = 0;
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
      <div className="title">
        <h1>Create New Wallet</h1>
      </div>

      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        {({ setFieldValue }) => (
          <Form className="form">
            <label htmlFor="name">Name</label>
            <Field name="name" type="text" />
            <StyledErrorMessage name="name" component="div" />

            <label htmlFor="risk">Risk</label>
            <Field name="risk" type="number" />
            <StyledErrorMessage name="risk" component="div" />

            <button type="submit">Submit</button>
          </Form>
        )}
      </Formik>
    </Conteiner>
    </div>
  );
}
