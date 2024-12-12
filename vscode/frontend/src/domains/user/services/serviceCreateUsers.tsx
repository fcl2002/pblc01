import { IUser } from "../interfaces/IUser";

const CreateUsers = async (data: IUser, setMsg: (message: string) => void) => {
  try {
    const url = import.meta.env.VITE_API_REGISTER;
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const result = await res.json();

    if (result.message === "User already exists") {
      setMsg(result.message);
    }

    if (result.errors) {
      setMsg(result.errors[0].message);
    }
  } catch (err) {
    console.error(err);
  }
};

export { CreateUsers };
