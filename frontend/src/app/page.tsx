import API from "./lib/api";
import Image from "next/image";
import Link from "next/link";

  interface Movie{
    imgUrl: string,
    movieId: number,
    movieName: string,
    director: string,
    ottLink: string,
    ottName: string,
  }

  async function getMovies(): Promise<Movie[]> {
    try {
      const response = await API.get<Movie[]>("/movie/list");
      return response.data;
    } catch (error) {
      console.error("Error fetching movies:", error);
      return [];
    }
  }

  export default async function Home() {

    const movies = await getMovies();
  return (
    <div>
      {movies.map((movie) =>(
      <div key={movie.movieId}>
          <Link href={`/${movie.movieId}`} >
                <div>
                  <Image src={
                  "http://localhost:8080/img/"+movie.imgUrl}
                  width={400}
                  height={300} 
                  alt="movie 이미지"
                  priority />
                </div>
          </Link>
          <h3>{movie.movieName}</h3>  <span>좋아요</span>
          <p><a href={`${movie.ottLink}`} target="_blank">{movie.ottName}</a></p>
          <p>평점</p>
      </div>
        ))}
    </div>

  )
}