"use client"

import { useEffect, useState } from "react";
import API from "./lib/api";
import Image from "next/image";
import Link from "next/link";
import Like from "@/components/Like";

export default function Home() {

  const [list,setList]=useState<Movie[]>([]);
  interface Movie{
    imgUrl: string,
    movieId: number,
    movieName: string,
    director: string,
    ottLink: string,
    ottName: string,
  }
  
  
  useEffect(()=>{
    API.get<Movie[]>("/movie/list")
    .then(response=>{
      console.log(response.data)
      if(response.data.length===0){
        console.warn("데이터가 없습니다.");
      }
      setList(response.data)})
    .catch(error=>{
      console.log(error,"에러남")
    })
  },[])

  return (

    <div >
      {
        list.map((movie,i) =>(
        <div key={i}>
          <Link href={`/${movie.movieId}`} >
            
                <div>
                  <Image src={
                  "http://localhost:8080/img/"+movie.imgUrl}
                  width={400}
                  height={300} 
                  alt="book 이미지"
                  priority />
                </div>
                
            
          </Link>
          <h3>{movie.movieName}</h3>  <Like movieId={movie.movieId}/>
          <p><a href={`${movie.ottLink}`} target="_blank">{movie.ottName}</a></p>
          <p>평점</p>
          </div>
        ))}
    </div>

  );
}
