import React,{ useState }  from 'react'
import { Link,useNavigate} from 'react-router-dom'
import PropTypes from 'prop-types';
import {setToken} from "../../App";

import '../../App.css'
import {Popup} from "../PopUp";



async function loginUser(email) {
    return fetch(`http://localhost:8080/api/auth/reset?email=${email}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .catch(err => {
        return (<div> <h2> Błąd serwera </h2> </div>)
    })
}

export default function ResetPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [open, setOpen] = useState(false);

    const handleSubmit = async e => {
        e.preventDefault();
        const token = await loginUser(email).then(r => {
        navigate('/login')
        })


    }
    return (
        <div className="text-center m-5-auto">
            <div>
                {open ? <Popup text="" closePopup={() => setOpen(false)} /> : null}
            </div>
            <h2>Przypomnij hasło</h2>
            <form onSubmit={handleSubmit}>
                <p>
                    <label>Email</label><br/>
                    <input type="text"  name="email" onChange={e => setEmail(e.target.value)} />
                </p>
                <p>
                    <button id="sub_btn" type="submit">Resetuj hasło</button>
                </p>
            </form>
            <footer>
                <p>Pierwszy raz? <Link to="/register">Stwórz konto</Link>.</p>
                <p><Link to="/">Powrót</Link>.</p>
            </footer>
        </div>
    )
}

ResetPage.propTypes = {
    setToken: PropTypes.func
};