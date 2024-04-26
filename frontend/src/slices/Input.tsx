import {FrameHeader} from "../features/FrameHeader.tsx";
import {ChangeEventHandler} from "react";

interface InputProps {
    title: string,
    name?: string,
    onChange?: ChangeEventHandler,
    placeholder?: string
}
export function Input({title, name, onChange, placeholder = ''} : InputProps) {
    return (
        <FrameHeader title={title}>
            <input name={name} className="rounded-xl h-14 w-full text-xl px-4 text-bold" type="text"
                   placeholder={placeholder} onChange={onChange}/>
        </FrameHeader>
    )
}