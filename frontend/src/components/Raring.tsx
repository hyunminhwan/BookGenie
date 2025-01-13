"use client";

import { FaStar } from "react-icons/fa";

export default function Rating({ rating }: { rating: number }) {
    // 별 개수 설정 (5개)
    const star = [0, 1, 2, 3, 4];

    return (
        <div>
            {star.map((index) => (
                <FaStar
                    key={index}
                    style={{
                        color: index < rating ? "gold" : "gray", // `rating` 값을 기준으로 색상 결정
                        fontSize: "24px",
                    }}
                />
            ))}
            <span>{rating}/5</span>
        </div>
    );
}
