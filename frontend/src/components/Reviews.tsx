"use client"

import API from "@/app/lib/api"
import { useEffect, useState } from "react"
import Rating from "./Raring";


interface review{
    movieId: number,
    userId: string,
    reviewContent: string,
    rating: number,
    createdAt: string
   }
   interface ReviewsProps {
    movieId: number;
}
export default function Reviews({movieId}:ReviewsProps){
    
    
    const [reviews,setReviews] = useState<review[]>([]);
    const [loading,setLoading]=useState<boolean>(false);

    useEffect(()=>{
        if (!movieId) return;
        const reviewStatus =async ()=>{

            setLoading(true);
            try{
                const response = await API.get("/reviews",{params:{movieId}})
                setReviews(response.data);
                console.log(response.data);
            }catch(error){
                console.error("리뷰데이터를 불러오지 못했습니다.",error)
            }finally{
                setLoading(false);
            }
            

        }
        reviewStatus();
    },[movieId])

    return(
        <div>
            {loading ? (
                <p>리뷰 데이터를 불러오는 중입니다...</p>
            ) :reviews.length === 0 ? (
                <p>아직 작성된 리뷰가 없습니다.</p> // 리뷰가 없을 때 표시
            ) : (
                reviews.map((r) => (
                    <div key={r.createdAt}>
                        <Rating rating={r.rating} />
                        <p>아이디: {r.userId   }</p>
                        <p>리뷰내용: {r.reviewContent}</p>
                    </div>
                ))
            )}
        </div>
    )
}