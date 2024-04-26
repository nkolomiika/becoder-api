import {FrameHeader} from "../features/FrameHeader.tsx";

export function Input({title, placeholder = ''} : {title: string, placeholder?: string }) {
    return (
        <FrameHeader title={title}>
            <input className="rounded-xl h-14 text-xl px-4 text-bold" type="text" placeholder={placeholder}/>
        </FrameHeader>
    )
}