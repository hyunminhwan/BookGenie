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
       API.get("/api/user")
       .then((response)=>{
        console.log(response.data);
        setUser(response.data);
       })
       .catch((error)=>{
        console.log("정보를가져오는데 실패 했습니다.",error);
       })
    },[])

    return(
        <header>
            <nav>
                <Link href="/">홈</Link>
                {user?(
                    <span>
                        {user.userId} ({user.userName})
                    </span>
                    ):(
                    <Link href="/login">로그인</Link>
                    )
                }
            </nav>
        </header>
    )
}