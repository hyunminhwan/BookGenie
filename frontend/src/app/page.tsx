"use client"
import { useEffect, useState } from "react";
import API from "./lib/api";
import Image from "next/image";

export default function Home() {
  const [list,setList]=useState<Movie[]>([]);
  interface Movie{
    imgUrl: string,
    movieId: number,
    movieName: string,
    movieContent: string,
    releaseDate: string,
    director: string,
    ottLink: string,
    createdAt: string
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
              <div>
                <Image src={
                "http://localhost:8080/img/"+movie.imgUrl}
                width={400}
                height={300} 
                alt="book 이미지"
                priority />
              </div>
              <div><span>{movie.movieName}</span>  <span>좋아요</span></div>
              <h3>{movie.movieContent}</h3>
              <p>가격</p>
              <p>평점</p>
          </div>
        ))}
    </div>

  );
}
