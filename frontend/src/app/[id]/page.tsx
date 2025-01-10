import Image from "next/image";
import API from "../lib/api";


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
  movieType: string;
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
    const { id } =  params;
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
      <h1 >{movie.movieName}</h1>
      <p >{movie.movieContent}</p>
      <p>
        <strong>Release Date:</strong> {new Date(movie.releaseDate).toLocaleDateString()}
      </p>
      <p>
        <strong>Director:</strong> {movie.director}
      </p>
      <p>
        <strong>Genre:</strong> {movie.genre}
      </p>
      <p>
        <strong>Type:</strong> {movie.movieType}
      </p>
      <p>
        <strong>OTT Platform:</strong>{" "}
        <a href={movie.ottLink} target="_blank" rel="noopener noreferrer">
          {movie.ottName}
        </a>
      </p>
      <p>
        <strong>Created At:</strong> {new Date(movie.createdAt).toLocaleDateString()}
      </p>
    </div>
  );
}
