"use client";

import API from "@/app/lib/api";
import Link from "next/link";
import { useEffect, useState } from "react";

export default  function Header () {
    interface User {
        userId: string;
        userName: string;
        userEmail: string;
    }

    const [user, setUser] = useState<User | null>(null);

    useEffect(()=>{
            API.get("/user")
            .then((response)=>{
             console.log(response.data);
             setUser(response.data);
            })
            .catch((error)=>{
             if (error.response.status === 401 || error.response.status === 403) {
                   
                } else if (error.request) {
                    console.log("서버와의 연결 실패:", error.request);
                } else {
                    console.log("요청 설정 오류:", error.message);
                }
                setUser(null);
                
            })
    },[])

    const logOut = ()=>{
        API.get("/Logout")
        .then((response)=>{
            alert(`${response.data}`)
             // 사용자 상태 초기화
        setUser(null);
        // 로그인 페이지로 리다이렉트
        window.location.href = "/login";
        })
        .catch(()=>{
            alert("로그아웃에 실패했습니다.")
        })
       
        
    }
    return(
        <header>
            <nav>
                <Link href="/">홈</Link>
                {user?(
                   
                        <span>
                            {user.userId} ({user.userName})
                            <button type="button" onClick={logOut}>로그아웃</button>
                        </span>
 
                    ):(
                    <Link href="/login">로그인</Link>
                    )
                }
            </nav>
            </header>
    )
}