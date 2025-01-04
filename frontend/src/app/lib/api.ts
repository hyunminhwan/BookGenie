import axios from 'axios';

const API = axios.create({
  baseURL: "http://localhost:8080", // 서버 주소
  headers: {
    "Content-Type": "application/json",
  },
});

export default API;
