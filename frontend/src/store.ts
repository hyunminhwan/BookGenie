import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./slices/userSlice";

export const store = configureStore({
  reducer: {
    user: userReducer, // user 상태 관리
  },
});

export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch; 비동기 사용시 필요