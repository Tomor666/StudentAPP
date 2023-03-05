import React from 'react'
import { Link } from 'react-router-dom'

import '../../App.css'
import BackgroundImage from "../../assets/images/bg.png";


export default function NoAuthPage() {
    return (
        <div className="text-center m-5-auto">
            <h2>Brak uprawnień do oglądania tej storny. Proszę się zalogować.</h2>
        </div>
    )
}

const HeaderStyle = {
    width: "100%",
    height: "100vh",
    background: `url(${BackgroundImage})`,
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
}