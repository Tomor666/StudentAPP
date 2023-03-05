import React, {useEffect, useState} from 'react'
import {Link} from 'react-router-dom'
import {useNavigate} from 'react-router-dom'
import {Popup} from "../PopUp";


async function getUser(token, id) {
    return fetch(`http://localhost:8080/api/manage?id=${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },

    }).then(data => data.json());
}


async function updateUser(token, body) {
    return fetch(`http://localhost:8080/api/manage`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }, body: JSON.stringify(body)

    });
}

async function deleteUserAsync(token, id) {
    return fetch(`http://localhost:8080/api/manage/delete?id=${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }


    });
}

export default function UserDetailsPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("");
    const [password, setPassword] = useState("")
    const [repeatedPassword, setRepeatedPassword] = useState("")
    const [userName, setUserName] = useState("")
    const tokenString = sessionStorage.getItem('token');
    const userId = sessionStorage.getItem('userId');
    const [user, setUsers] = useState("");


    const handleDelete = async  e => {
        await deleteUserAsync(tokenString,userId).then(data => {
            navigate('/dashboard')
        })
    }

    const handleSubmit = async e => {
        e.preventDefault()
        await updateUser(tokenString, {
            userId,
            email,
            userName,
            password,
            role,
            repeatedPassword

        }).then(data => {
            console.log(data)
            if (data.status === 200) {
                console.log(data.statusCode)
                alert("Udało się zaktualizować użytkownika")

            } else {
                alert("Nie udało się zaktualizować")
            }

        }).catch(err => {
            console.log(err)

        })
    }


    useEffect(() => {
        getUser(tokenString, userId)
            .then(r => setUsers(r))

    }, [tokenString, userId])

 console.log(user)

    function deleteUser() {

        deleteUserAsync(tokenString, userId)

    }

    if (tokenString == null || tokenString === '') {
        return <div className="text-center m-5-auto">
            <h2>Brak uprawnień zaloguj się.</h2>
            <footer>
            </footer>
        </div>
    }


    return (
        <div className="text-center m-5-auto">
            <h5>Dane użytkownika</h5>
            <form onSubmit={handleSubmit}>
                <p>
                    <label>Nazwa użytkownika</label><br/>
                    <input defaultValue={user.userName} type="text" name="first_name" required
                           onChange={e => setUserName(e.target.value)}/>
                </p>
                <p>
                    <label>Email </label><br/>
                    <input defaultValue={user.email} type="email" name="email" required
                           onChange={e => setEmail(e.target.value)}/>
                </p>
                <p>
                    <label>Rola </label><br/>
                    <input defaultValue={user.userRole} type="role" name="role" required
                           onChange={e => setRole(e.target.value)}/>
                </p>

                <p>
                    <label>Password </label><br/>
                    <input type="password" name="password" required onChange={e => setPassword(e.target.value)}/>
                </p>
                <p>
                    <label>RepeatPassword </label><br/>
                    <input type="password" name="password" required
                           onChange={e => setRepeatedPassword(e.target.value)}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit">Potwierdź zmiany</button>
                </p>


            </form>

            <p>
                <button id="sub_btn_red" onClick={handleDelete} type="submit">Usuń użytkownika</button>
            </p>
            <footer>
                <p><Link to="/">Powrót</Link>.</p>
            </footer>
        </div>
    )
}