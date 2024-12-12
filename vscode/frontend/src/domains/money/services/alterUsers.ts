async function alterUsers(data: any, setMsg: (message: string) => void) {
  const url = import.meta.env.VITE_API_ALTER_USERS;
  await fetch(url, {
    method: "PUT",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((res) => {
      console.log(res);
      if (res.message == "usuario update com sucesso") {
        setMsg(res.message);
      } else {
        setMsg(res);
      }
    })
    .catch((err) => {
      console.error(err);
    });
}

export { alterUsers };
