"use client"
import { useEffect, useState } from "react";
import API from "./lib/api";
import Image from "next/image";

export default function Home() {
  const [list,setList]=useState<Book[]>([]);
  interface Book{
    imgUrl: string,
    bookId: number,
    bookName: string,
    bookContent: string,
    bookIsbn: string,
    bookDate: string,
    status: BookStatus;
  }
  
   enum BookStatus{
    대여중="대여중",
    이용가능="이용가능",
    예약됨="예약됨"
  }
  useEffect(()=>{
    API.get<Book[]>("/book/list")
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
        list.map((book,i) =>(
          <div key={i}>
              <div>
                <Image src={
                "http://localhost:8080/img/"+book.imgUrl}
                width={400}
                height={300} 
                alt="book 이미지"
                priority />
              </div>
              <div><span>{book.bookId}</span>  <span>좋아요</span></div>
              <h3>{book.bookName}</h3>
              <p>가격</p>
              <p>평점</p>
          </div>
        ))}
    </div>

  );
}
