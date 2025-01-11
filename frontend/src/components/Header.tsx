"use client";

import API from "@/app/lib/api";
import { clearUser, setUser } from "@/slices/userSlice";
import { RootState } from "@/store";
import Link from "next/link";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default  function Header () {

    const dispatch = useDispatch();

    // Redux의 user 상태 가져오기
    const user = useSelector((state:RootState)=>state.user.user);


    useEffect(()=>{
            API.get("/user")
            .then((response)=>{
             console.log(response.data);
             dispatch(setUser(response.data));
            })
            .catch((error)=>{
             if (error.response.status === 401 || error.response.status === 403) {
                   
                } else if (error.request) {
                    console.log("서버와의 연결 실패:", error.request);
                } else {
                    console.log("요청 설정 오류:", error.message);
                }
                dispatch(clearUser());

            })
    },[dispatch])

    const logOut = ()=>{
        API.get("/Logout")
        .then((response)=>{
            alert(`${response.data}`)
             // 사용자 상태 초기화
             dispatch(clearUser());
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
            <h2>Movie Genie
                {user?(
                         <div>
                            <span>
                                {user.userId} ({user.userName})
                                <button type="button" onClick={logOut}>로그아웃</button>
                            </span>
                            <span>
                                <Link href="/login/insert">등록페이지</Link>
                            </span>
                        </div>
 
                    ):(
                    <Link href="/login">로그인</Link>
                    )
                }
                </h2>
            </nav>
            <Link href="/">홈</Link>
            </header>
    )
}