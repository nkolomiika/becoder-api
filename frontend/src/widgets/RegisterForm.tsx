import {Input} from "../slices/Input.tsx";
import {NavLink} from "react-router-dom";

export function RegisterForm() {
    return (
        <div className="flex flex-col gap-2 mt-28">
            <h2 className="text-3xl text-center font-bold">Register</h2>
            <Input title='Username'/>
            <Input title='Password'/>
            <Input title='Repeat password'/>
            <p className="text-xl">Already have an account? <NavLink to="/Login" className="underline text-orange-500">Log in</NavLink></p>

        </div>
    )
}