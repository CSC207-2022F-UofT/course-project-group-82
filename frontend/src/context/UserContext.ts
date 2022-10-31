import React, { createContext } from "react";

export const UserContext = createContext({
  userID: "" as string | null,
  setUserID: ((response: string | boolean) => {}) as React.Dispatch<
    React.SetStateAction<string | null>
  >,
});
