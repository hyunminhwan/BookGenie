import Link from "next/link";

export default  function Header () {
    return(
        <>
        <Link href="/">홈</Link>
        <Link href="/login">로그인</Link>
        </>
    )
}