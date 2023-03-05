import React,{ useState }  from 'react'
import { Link,useNavigate} from 'react-router-dom'
import PropTypes from 'prop-types';
import {setToken} from "../../App";

import '../../App.css'
import {Popup} from "../PopUp";



async function loginUser(credentials) {
    return fetch('http://localhost:8080/api/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(credentials)
    }).then(data => data.json())
        .catch(err => {
        return (<div> <h2> Błąd serwera </h2> </div>)
    })
}

export default function SignInPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [open, setOpen] = useState(false);

    const handleSubmit = async e => {
        e.preventDefault();
        const token = await loginUser({
            email,
            password
        }).then(data => {
            if(data.jwtToken != null){
                navigate("/dashboard")
                localStorage.setItem("authenticated", true);
                setToken(data.jwtToken);

            } else {
                setOpen(true)
            }
        }).catch(err => {
            setOpen(true)
            alert("Wrong password or email")
        })


    }
    return (
        <div className="text-center m-5-auto">
            <div>
                {open ? <Popup text="Coś poszło nie tak przy logowaniu" closePopup={() => setOpen(false)} /> : null}
            </div>
            <h2>Zaloguj się</h2>
            <form onSubmit={handleSubmit}>
                <p>
                    <label>Email</label><br/>
                    <input type="text"  name="email" onChange={e => setEmail(e.target.value)} />
                </p>
                <p>
                    <label>Hasło</label>
                    <input type="password" onChange={e => setPassword(e.target.value)} />
                    <br/>
                </p>
                <p>
                    <button id="sub_btn" type="submit">Login</button>
                </p>
            </form>
            <footer>
                <p>Pierwszy raz? <Link to="/register">Stwórz konto</Link>.</p>
                <p>Zapomniałeś hasła ? <Link to="/reset">Resetuj</Link>.</p>
                <p><Link to="/">Powrót</Link>.</p>
            </footer>
        </div>
    )
}

SignInPage.propTypes = {
    setToken: PropTypes.func
};