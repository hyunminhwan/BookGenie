"use client"

import { useState } from "react"
import { FaStar } from "react-icons/fa";


export default function Rating({rating}:{rating:number}){
    // 별점 기본값
    const [clicked, setClicked] =useState<boolean[]>([false,false,false,false,false])

    //별을 5개로 설정
    const star = [0,1,2,3,4]
    
    const starScore = (index:number):void=>{
        
        const newclicked =[...clicked];
        for(let i=0; i<5; i++){
            newclicked[i] = i <= index ? true : false
        }
        setClicked(newclicked)
    }
    return(
        <div>
            {star.map((index)=>(
                <FaStar key={index}
                    onClick={()=>{starScore(rating)}}
                    style={{
                        cursor: "pointer",
                        color: clicked[index] ? "gold" : "gray",
                        fontSize: "24px",
                    }}

                />
    )) 
            }
            <span>{rating}/5</span>
        </div>
    )
}