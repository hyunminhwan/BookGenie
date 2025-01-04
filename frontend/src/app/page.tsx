"use client"
import { useEffect, useState } from "react";
import API from "./lib/api";

export default function Home() {
  const [list,setList]=useState<Book[]>([]);
  interface Book{
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
      <table>
        <thead>
        <tr>
          <td>도서번호</td>
          <td>책이름</td>
          <td>도서설명</td>
          <td>ISBN번호</td>
          <td>출판일</td>
          <td>대여가능여부</td>
        </tr>
        </thead>
        <tbody>
      {
        list.map((book,i) =>(
          <tr key={i}>
             <td>{book.bookId}</td>
              <td>{book.bookName}</td>
              <td>{book.bookContent}</td>
              <td>{book.bookIsbn}</td>
              <td>{book.bookDate}</td>
              <td>{book.status}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>

  );
}
