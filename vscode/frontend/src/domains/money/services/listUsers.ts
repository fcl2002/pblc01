async function listUsers(setMsg: (message: string) => void) {
  const url = import.meta.env.VITE_API_LIST_USERS;
  await fetch(url, {
    method: "GET",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((res) => {
      setMsg(res.message);
    })
    .catch((err) => {
      console.error(err);
    });
}

export { listUsers };