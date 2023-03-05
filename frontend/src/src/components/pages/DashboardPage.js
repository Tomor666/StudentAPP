import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.min.css";

import '../../App.css'
import {setToken} from "../../App";



const columns = [
    {
        field: "title",
        headerName: "Title",
    },
    {
        field: "number",
        headerName: "Amount",
    },
];

export default function DashboardPage() {

    const list = [
        { title: "Michael", number: 1 },
        { title: "Lindsay", number: 10 },
        { title: "Tobias", number: 6 },
        { title: "Byron", number: 3 },
        { title: "George", number: 1 },
        { title: "Rachel", number: 10 },
        { title: "Lawson", number: 6 },
        { title: "Ferguson", number: 3 },
        { title: "Funke", number: 1 },
    ];

    const navigate = useNavigate();
    const tokenString = sessionStorage.getItem('token');
    console.log("Token ")
    if(tokenString == null || tokenString === ''){
        return <div className="text-center m-5-auto">
            <h2>Brak uprawnień zaloguj się.</h2>
            <footer>
            </footer>
        </div>
    }



    // Render the UI for your table
    return (
        <div className="App">
            {/*<SimpleTableComponent*/}
            {/*    columns={columns}*/}
            {/*    list={list}*/}
            {/*    tableClassName={"table table-bordered"}*/}
            {/*/>*/}
        </div>
    );
}
