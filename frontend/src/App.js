import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

import LandingPage from './components/pages/LandingPage'
import LoginPage from './components/pages/LoginPage'
import RegisterPage from './components/pages/RegisterPage'
import ForgetPasswordPage from './components/pages/ForgetPasswordPage'
import HomePage from './components/pages/HomePage'
import DashboardPage from './components/pages/DashboardPage'
import NoAuthPage from './components/pages/NoAuthPage'
import ResetPasswordPage from './components/pages/ResetPasswordPage'
import { useState } from "react";

import './App.css'
import UserDetailsPage from "./components/pages/UserDetailsPage";

export function setToken(userToken) {
    sessionStorage.setItem('token', JSON.stringify(userToken));
}

export function getToken() {
    const tokenString = sessionStorage.getItem('token');
    console.log(tokenString)
}

export default function App() {

    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/" element={<LandingPage/>}/>
                    <Route path="/login" element={ <LoginPage/> } />
                    <Route path="/register" element={ <RegisterPage/> } />
                    <Route path="/forget-password" element={ <ForgetPasswordPage/> } />
                    <Route path="/home" element={ <HomePage/> } />
                    <Route path="/dashboard" element={<DashboardPage/>} />
                    <Route path="/auth-error" element={<NoAuthPage/>} />
                    <Route path="/user-details" element={<UserDetailsPage/>} />
                    <Route path="/reset" element={<ResetPasswordPage/>} />
                </Routes>
                <Footer />
            </div>
        </Router>
    )
}

const Footer = () => {
    return (
        <p className="text-center" style={ FooterStyle }>Projekt stworzony w celach edukacyjnych. React JS + Java SpringBoot</p>
    )
}

const FooterStyle = {
    background: "#222",
    fontSize: ".8rem",
    color: "#fff",
    position: "absolute",
    bottom: 0,
    padding: "1rem",
    margin: 0,
    width: "100%",
    opacity: ".5"
}