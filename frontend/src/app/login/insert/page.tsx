"use client";


import API from "@/app/lib/api";
import { useState } from "react";

export default function Insert() {
    interface Movie{
        movieName: string,
        movieContent: string,
        releaseDate: string,
        director: string,
        ottLink: string,
        ottName: string,
        movieType: string,
        genre: string
    }

    const [movieData, setMovieData] = useState<Movie>({
        movieName: "",
        movieContent: "",
        releaseDate: "",
        director: "",
        ottLink: "",
        ottName: "",
        genre: "",
        movieType: "MOVIE",
      });
      const [file, setFile] = useState<File | null>(null); // 이미지 파일 상태
    
      // 입력 핸들러
      const movieChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
      ) => {
        const { name, value } = e.target;
        setMovieData((prev) => ({
          ...prev,
          [name]: value,
        }));
      };
    
      // 파일 선택 핸들러
      const fileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files && e.target.files[0]) {
          setFile(e.target.files[0]);
        }
      };
    
      // 폼 제출 핸들러
      const insertMovie = async (e: React.FormEvent) => {
        e.preventDefault();
    
        if (!file) {
          alert("이미지를 선택해주세요!");
          return;
        }
    
        const formData = new FormData();
        formData.append("file", file);
        Object.entries(movieData).forEach(([key, value]) => {
          formData.append(key, value); // 폼 데이터를 FormData에 추가
        });
    
        try {
          const response = await API.post("/movie/insert", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
          alert("영화/드라마 등록 성공!");
          window.location.href = "/";
          console.log(response.data);
        } catch (error) {
          console.error("등록 중 오류 발생:", error);
        }
      };
    
      return (
        <div>
          <h1>영화/드라마 등록</h1>
          <form onSubmit={insertMovie}>
            <label>
              제목:
              <input
                type="text"
                name="movieName"
                value={movieData.movieName}
                onChange={movieChange}
                required
              />
            </label>
            <label>
              설명:
              <textarea
                name="movieContent"
                value={movieData.movieContent}
                onChange={movieChange}
                required
              />
            </label>
            <label>
              개봉일:
              <input
                type="date"
                name="releaseDate"
                value={movieData.releaseDate}
                onChange={movieChange}
                required
              />
            </label>
            <label>
              감독:
              <input
                type="text"
                name="director"
                value={movieData.director}
                onChange={movieChange}
                required
              />
            </label>
            <label>
              OTT 링크:
              <input
                type="url"
                name="ottLink"
                value={movieData.ottLink}
                onChange={movieChange}
              />
            </label>
            <label>
              OTT 이름:
              <input
                type="text"
                name="ottName"
                value={movieData.ottName}
                onChange={movieChange}
              />
            </label>
            <label>
              장르:
              <input
                type="text"
                name="genre"
                value={movieData.genre}
                onChange={movieChange}
              />
            </label>
            <label>
              타입:
              <select name="movieType" value={movieData.movieType} onChange={movieChange}>
                <option value="MOVIE">영화</option>
                <option value="DRAMA">드라마</option>
              </select>
            </label>
            <label>
              이미지 업로드:
              <input type="file" accept="image/*" onChange={fileChange} />
            </label>
            <button type="submit">등록</button>
          </form>
        </div>
      );
}