import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {Welcome} from "./pages/Welcome.tsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {Login} from "./pages/Login.tsx";
import {Register} from "./pages/Register.tsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <Welcome />
    },
    {
        path: '/login',
        element: <Login />
    },
    {
        path: '/register',
        element: <Register />
    }
])

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
)
