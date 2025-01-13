"use client"

import API from "@/app/lib/api"
import { RootState } from "@/store";
import { useEffect, useState } from "react"
import { useSelector } from "react-redux";

export default function Like ({movieId}:{movieId:number}) {
   const [like,setlike]=useState<boolean>(false);
   const [loading,setLoading]=useState<boolean>(true);

    //Redux user 가져오기
   const user = useSelector((state: RootState) => state.user.user);
   const userId =user?.userId

useEffect(()=> {
    const likeStatus = async ()=>{
        if(userId != null){
            try{
                const response = await API.get("/likes/get",{params:{userId,movieId}});
                setlike(response.data);
            }catch(error){
                console.error(`${userId} 좋아요 가져오기 실패`,error)
            }finally{
                setLoading(false)
            }
        }
    }
    likeStatus();
},[userId,movieId])

    //버튼 클릭 이벤트
 const likeButton = async ()=>{
    if (loading || !userId) {
        return; // 로딩 중이거나 userId가 없으면 실행 안 함
      }
    setLoading(true);
    try{
 
        if(like){
            await API.delete("/likes/delete",{params:{userId,movieId}})
           }else{
            await API.post("/likes/post",{user:{userId},movie:{movieId}})
           }
           setlike(!like);
       }catch(error){
        console.error("좋아요 등록/삭제 실패",error)
       }finally{
        setLoading(false)
       }
 }
  
   

    return (
        <div>
            <button 
            onClick={likeButton}
            disabled={loading}
            style={{cursor:"pointer"}}
            >
                {
                    like?(
                        <span style={{ color: "red", fontSize: "24px" }}>❤️</span>
                    ):(
                        <span style={{ color: "gray", fontSize: "24px" }}>🤍</span>
                    )
                }
            </button>
        </div>
    )

}