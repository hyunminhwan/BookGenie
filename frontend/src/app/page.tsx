export default function Home() {
  return (
    <div >
      <form action={"/user/login"}>
        아이디: <input type="text" id="userId"/>
        비밀번호: <input type="password" id="userPassword"/>
        <button>로그인</button>
        <button>회원가입</button>
      </form>
    </div>
  );
}
