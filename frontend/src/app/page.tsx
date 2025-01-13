import API from "./lib/api";
import Image from "next/image";
import Link from "next/link";
import Like from "@/components/Like";
import Rating from "@/components/Raring";
import styles from "./page.module.css";

interface Movie {
  imgUrl: string;
  movieId: number;
  movieName: string;
  director: string;
  ottLink: string;
  ottName: string;
  rating: number;
}

async function getMovies(): Promise<Movie[]> {
  try {
    const response = await API.get<Movie[]>("/movie/list");
    return response.data;
  } catch (error) {
    console.error("데이터 가져오기 실패", error);
    return [];
  }
}

export default async function Home() {
  const list = await getMovies();

  return (
    <div className={styles.movieGridContainer}>
      <div className={styles.movieGrid}>
        {list.map((movie) => (
          <div key={movie.movieId} className={styles.movieCard}>
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
            <div className={styles.titleRow}>
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
        ))}
      </div>
    </div>
  );
}
