import {Input} from "../slices/Input.tsx";
import {NavLink} from "react-router-dom";

export function LoginForm() {
    return (
        <div className="flex flex-col gap-2 mt-28">
            <h2 className="text-3xl text-center font-bold">Login</h2>
            <Input title='Username'/>
            <Input title='Password'/>
            <p className="text-xl">Don't have an account yet? <NavLink to="/Register" className="underline text-orange-500">Go register</NavLink></p>

        </div>
    )
}