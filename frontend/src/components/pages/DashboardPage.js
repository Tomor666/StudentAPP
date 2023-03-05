import React, {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.min.css";
import {DataGrid} from '@mui/x-data-grid';


import '../../App.css'


async function getUsers(token) {
    return fetch('http://localhost:8080/api/users?page=-1&size=-1', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },

    }).then(data => data.json());
}


const columns = [
    {field: 'userName', headerName: 'Nazwa użytkownika', width: 150},
    {field: 'id', headerName: 'Id użytkownika', width: 150},
    {field: 'enabled', headerName: 'Aktywny', width: 150},
    {field: 'email', headerName: 'E-mail', width: 150},
    {field: 'role', headerName: 'Rola', width: 150},
    {field: 'createdAt', headerName: 'Data utworzenia', width: 150},
];
export default function DashboardPage() {

    const navigate = useNavigate();
    const tokenString = sessionStorage.getItem('token');
    const [users, setUsers] = useState([]);
    console.log("Token ")

    useEffect(() => {
        getUsers(tokenString)
            .then(r => setUsers(mapData(r)))

    }, [tokenString])



    const mapData = (response) => {
        if (!response) return [];
        return response.map(user => ({
            id: user.id,
            userName: user.userName,
            email: user.email,
            role: user.role,
            createdAt: user.createdAt,
            enabled: user.enabled ? "TAK" : "NIE"
        }))
    }

    console.log(users)

    if (tokenString == null || tokenString === '') {
        return <div className="text-center m-5-auto">
            <h2>Brak uprawnień zaloguj się.</h2>
            <footer>
            </footer>
        </div>
    }


    const onCustomButtonClick = (id) => {
        sessionStorage.setItem('userId', id);
        navigate('/user-details')
    }

    return (

        <div style={{height: 1200, width: '100%'}}>
            <h1>Lista użytkowników</h1>
            <DataGrid     onRowClick={(rows)=>{onCustomButtonClick(rows.id)}} rows={users} columns={columns}/>
        </div>
    )
}
