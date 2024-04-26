import {Input} from "../slices/Input.tsx";
import {NavLink, useNavigate} from "react-router-dom";
import {ChangeEvent, FormEvent, useState} from "react";
import axios from 'axios';

interface FormValues {
    username: string,
    password: string
}
export function LoginForm() {
    const [responseBody, setResponseBody] = useState<FormValues>({username: "", password: ""})
    const navigate = useNavigate()
    const inputChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target
        setResponseBody({...responseBody, [name]: value})
    }
    const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        console.log(responseBody)
        axios.post('http://localhost:8080/api/auth/login', {
            username: responseBody.username,
            password: responseBody.password
        }).then(res => {sessionStorage.setItem("token", res.data); console.log(res.data); navigate('/')})
            .catch(err => alert(err))
    }

    return (
        <form className="flex flex-col gap-2 mt-28" onSubmit={onSubmitHandler}>
            <h2 className="text-3xl text-center font-bold">Login</h2>
            <Input title='Username' name="username" onChange={inputChangeHandler}/>
            <Input title='Password' name="password" onChange={inputChangeHandler}/>
            <p className="text-xl">Don't have an account yet? <NavLink to="/Register" className="underline text-orange-500">Go register</NavLink></p>
        </form>
    )
}