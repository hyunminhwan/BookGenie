import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    domains: ["localhost"], // Spring Boot 서버 도메인 허용
  },
};

export default nextConfig;
