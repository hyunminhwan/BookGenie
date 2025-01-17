"use client"

import { RootState } from "@/store";
import { useSelector } from "react-redux";
import API from "../lib/api";
import { useEffect, useState } from "react";
import Link from "next/link";
import Image from "next/image";
import Rating from "@/components/Raring";
import Like from "@/components/Like";


interface Movie {
    imgUrl: string;
    movieId: number;
    movieName: string;
    director: string;
    ottLink: string;
    ottName: string;
    rating: number;
  }
export default function Profile (){
    const user = useSelector((state:RootState)=>(state.user.user))||null;
    const [list,setList] = useState<Movie[]>([]);

    useEffect(()=>{
        if (!user?.userId) return;
        const myList = async()=>{
            try{
                const response = await API.get<Movie[]>("/myList",{params:{userId:user?.userId}});
                setList(response.data);
                console.log(response.data)
            }catch(error){
                console.error(error);
            }
        }  
        myList();
    },[user?.userId])

   return(
    <div>
        {
            list.length > 0 ?(
                list.map((movie)=>(
                    <div key={movie.movieId}>
                        <Link href={`/${movie.movieId}`}>
                            <div>
                                <Image
                                src={`http://localhost:8080/img/${movie.imgUrl}`}
                                width={400}
                                height={300}
                                alt={`${movie.movieName} 이미지`}
                                priority
                                />
                            </div>
                        </Link>
                         <div>
                            <h3>{movie.movieName}</h3>
                            <Like movieId={movie.movieId} />
                            </div>
                            <p>
                            <a href={movie.ottLink} target="_blank" rel="noopener noreferrer">
                                {movie.ottName}
                            </a>
                            </p>
                            <Rating rating={movie.rating} />
                     </div>
                ))
            ):(
               <p>정보가없습니다.</p>
            )
        }
    </div>
   )
    

    

}