import React, {useState} from 'react'
import {Link, useNavigate} from 'react-router-dom'

import '../../App.css'
import {Popup} from "../PopUp";


async function register(body) {
    return fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(body)
    }).then(data => data.json());
}

export default function SignUpPage() {

    const navigate = useNavigate();

    const [email, setEmail] = useState();
    const [password, setPassword] = useState()
    const [repeatedPassword, setRepeatedPassword] = useState()
    const [userName, setUserName] = useState()
    const [open, setOpen] = useState(false);


    const handleSubmit = async e => {
        e.preventDefault()
        const reg = await register({
            email,
            userName,
            password,
            repeatedPassword

        }).then(data => {
            navigate("/login")
            if (data.statusCode === 200) {
                console.log(data.statusCode)

            } else {
                setOpen(true)
            }

        }).catch(err => {
            navigate("/login")
        })
    }


    return (
        <div className="text-center m-5-auto">
            <div>
                {open ? <Popup text="Coś poszło nie tak przy rejestracji" closePopup={() => setOpen(false)} /> : null}
            </div>
            <h5>Podaj dane do rejestracji</h5>
            <form onSubmit={handleSubmit}>
                <p>
                    <label>Nazwa użytkownika</label><br/>
                    <input type="text" name="first_name" required onChange={e => setUserName(e.target.value)}/>
                </p>
                <p>
                    <label>Email </label><br/>
                    <input type="email" name="email" required onChange={e => setEmail(e.target.value)}/>
                </p>
                <p>
                    <label>Hasło</label><br/>
                    <input type="password" name="password" required onChange={e => setPassword(e.target.value)}/>
                </p>
                <p>
                    <label>Powtórz hasło</label><br/>
                    <input type="password" name="password" required
                           onChange={e => setRepeatedPassword(e.target.value)}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit">Zarejestuj</button>
                </p>
            </form>
            <footer>
                <p><Link to="/">Powrót</Link>.</p>
            </footer>
        </div>
    )

}
