"use client";

import Link from "next/link";
import API from "../lib/api";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { AxiosError } from "axios";

export default function Login() {
    const [userId, setUserId] = useState<string>("");
    const [userPassword, setUserPassword] = useState<string>("");
    const router = useRouter();

    const loginSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await API.post("/login", {
                username: userId,
                password: userPassword,
            });

            if (response.status === 200) {
                console.log("로그인 성공:", response);
                alert("로그인 성공!");
                router.push("/");
            }
        } catch (error: unknown) {
            if (error instanceof AxiosError) {
                if (error.response) {
                    console.error("서버 응답 에러:", error.response.data);
                    alert(`로그인 실패: ${error.response.data.message || "오류가 발생했습니다."}`);
                } else if (error.request) {
                    console.error("요청 문제:", error.request);
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
                    아이디: <input type="text" id="userId" onChange={(e) => setUserId(e.target.value)} />
                </div>
                <div>
                    비밀번호:{" "}
                    <input type="password" id="userPassword" onChange={(e) => setUserPassword(e.target.value)} />
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
