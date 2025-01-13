import Image from "next/image";
import API from "../lib/api";
import { format } from "date-fns";
import Like from "@/components/Like";
import Reviews from "@/components/Reviews";



interface Movie {
  movieId:number
  imgUrl: string;
  movieName: string;
  movieContent: string;
  releaseDate: string;
  director: string;
  ottLink: string;
  ottName: string;
  genre: string;
  createdAt: string;
}



//[id] 의값
interface Props {
    params: {
      id: string; // id는 항상 string으로 전달됩니다.
    };
  }

// 서버에서 데이터를 가져오는 함수
async function getMovieData(id: number): Promise<Movie | null> {
  
  try {
    const response = await API.get(`/movie/detail/${id}`);
    return response.data; // API에서 받은 데이터 반환
  } catch (error) {
    console.error("Error fetching movie data:", error);
    return null; // 오류 시 null 반환
  }
}



export default async function MovieDetail({ params }: Props) {
    const  id  = params.id;
    const movie = await getMovieData(Number(id));

  if (!movie) {
    return <p>Movie not found</p>;
  }

  
  return (
    <div >
      <Image
        src={`http://localhost:8080/img/${movie.imgUrl}`}
        width={800}
        height={500}
        alt={movie.movieName}
        priority
        style={{ borderRadius: "10px" }}
      />
      <h1 >{movie.movieName}</h1><Like movieId={movie.movieId} />
      <p >{movie.movieContent}</p>
      <p>
        <strong>개봉일:</strong> {format(new Date(movie.releaseDate), "yyyy-MM-dd")}
      </p>
      <p>
        <strong>감독:</strong> {movie.director}
      </p>
      <p>
        <strong>장르:</strong> {movie.genre}
      </p>
      <p>
        <strong>OTT:</strong>{" "}
        <a href={movie.ottLink} target="_blank" rel="noopener noreferrer">
          {movie.ottName}
        </a>
      </p>
      <p>
        <strong>등록일 :</strong> {format(new Date(movie.releaseDate), "yyyy-MM-dd")}
      </p>
      <h2>리뷰</h2>
      <Reviews movieId={movie.movieId}/>
    </div>
  );
}
