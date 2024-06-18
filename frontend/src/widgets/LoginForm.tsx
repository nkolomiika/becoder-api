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
    const [errors, setErrors] = useState<string[]>([])
    const inputChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target
        setResponseBody({...responseBody, [name]: value})
    }
    const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        console.log(responseBody)
        axios.post('http://localhost:6868/api/auth/login', {
            login: responseBody.username,
            password: responseBody.password
        }).then(res => {sessionStorage.setItem("token", res.data.token); console.log(res.data); navigate('/')})
            .catch(err => setErrors([...errors, err.message]))
    }

    return (
        <form className="flex flex-col gap-2 mt-28" onSubmit={onSubmitHandler}>
            <div id="error-message" title="error-message"
                 className="text-center text-red-500">{errors.length > 0 && errors[0]}</div>
            <h2 className="text-3xl text-center font-bold">Log in</h2>
            <Input title='Username' name="username" onChange={inputChangeHandler}/>
            <Input title='Password' name="password" onChange={inputChangeHandler}/>
            <button className="bg-blue-400 rounded-xl text-white p-4 text-xl font-bold curson-pointer">Log in</button>
            <p className="text-xl">Don't have an account yet? <NavLink to="/Register"
                                                                       className="underline text-orange-500">Go
                register</NavLink></p>
        </form>
    )
}