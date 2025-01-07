"use client";

import Link from "next/link";
import API from "../lib/api";
import { useState } from "react";
import { AxiosError } from "axios";

export default function Login() {
    const [userId, setUserId] = useState<string>("");
    const [userPassword, setUserPassword] = useState<string>("");

    const loginSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await API.post("/login", {
                username: userId,
                password: userPassword,
            });

            if (response.status === 200) {
                alert("로그인 되었습니다.");
                window.location.href = "/";
                
            }
        } catch (error: unknown) {
            if (error instanceof AxiosError) {
                if (error.response) {
                    alert("아이디 또는 비밀번호가 틀렸습니다.");
                    setUserId("");
                    setUserPassword("");
                } else if (error.request) {
                    alert("서버와의 연결에 실패했습니다. 네트워크를 확인해주세요.");
                }
        
            } else {
                console.error("설정 에러:", error);
                alert("예기치 못한 오류가 발생했습니다.");
            }
        }
    };

    return (
        <div>
            <form onSubmit={loginSubmit}>
                <div>
                    아이디: <input type="text" id="userId" value={userId} onChange={(e) => setUserId(e.target.value)} />
                </div>
                <div>
                    비밀번호:{" "}
                    <input type="password" id="userPassword" value={userPassword} onChange={(e) => setUserPassword(e.target.value)} />
                </div>
                <div>
                    <button type="submit">로그인</button>
                </div>
                <div>
                    <button><Link href={"/signUp"}>회원가입</Link></button>
                </div>
            </form>
        </div>
    );
}
