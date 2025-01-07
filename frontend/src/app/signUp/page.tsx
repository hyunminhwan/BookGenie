import API from "../lib/api"

export default function SignUp() {
    
    const loginSigup = ()=>{
        API.post("")
        .then()
        .catch()
    }

    return (
        <div>
            <form onSubmit={loginSigup}>
                <table>
                    <input/>
                </table>
                <button type="submit">회원가입</button>
            </form>
        </div>
    )
}