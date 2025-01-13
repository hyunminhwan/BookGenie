"use client";

import API from "@/app/lib/api";
import { clearUser, setUser } from "@/slices/userSlice";
import { RootState } from "@/store";
import Link from "next/link";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import styles from "./Header.module.css"; // CSS 모듈 가져오기

export default function Header() {
  const dispatch = useDispatch();

  // Redux의 user 상태 가져오기
  const user = useSelector((state: RootState) => state.user.user);

  useEffect(() => {
    API.get("/user")
      .then((response) => {
        dispatch(setUser(response.data));
      })
      .catch((error) => {
        if (error.response?.status === 401 || error.response?.status === 403) {
          console.log("인증 오류:", error.response.status);
        } else if (error.request) {
          console.log("서버와의 연결 실패:", error.request);
        } else {
          console.log("요청 설정 오류:", error.message);
        }
        dispatch(clearUser());
      });
  }, [dispatch]);

  const logOut = () => {
    API.get("/Logout")
      .then((response) => {
        alert(`${response.data}`);
        dispatch(clearUser());
        window.location.href = "/login";
      })
      .catch(() => {
        alert("로그아웃에 실패했습니다.");
      });
  };

  return (
    <header className={styles.header}>
      <div className={styles.headerContainer}>
        <h1 className={styles.logo}>
          <Link href="/">Movie Genie</Link>
        </h1>
        <div className={styles.navLinks}>
          {user ? (
            <>
              <span className={styles.userInfo}>
                {user.userId} ({user.userName})
              </span>
              <button
                type="button"
                className={styles.logoutButton}
                onClick={logOut}
              >
                로그아웃
              </button>
              <Link href="/login/insert" className={styles.registerLink}>
                등록페이지
              </Link>
            </>
          ) : (
            <Link href="/login" className={styles.loginLink}>
              로그인
            </Link>
          )}
        </div>
      </div>
    </header>
  );
}
