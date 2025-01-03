import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080/api', // 스프링 부트 API 주소
  headers: {
    'Content-Type': 'application/json',
  },
});

export default API;
