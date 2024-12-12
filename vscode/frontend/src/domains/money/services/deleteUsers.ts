async function deleteUser(setMsg: (message: string) => void) {
  const url = import.meta.env.VITE_API_DELETE_USERS;
  await fetch(url, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify("chch"),
  })
    .then((res) => res.json())
    .then((res) => {
      if (res.message == "user delete sucess!!") {
        setMsg(res.message);
      } else {
        setMsg(res);
      }
    })
    .catch((err) => {
      console.error(err);
    });
}

export { deleteUser };
