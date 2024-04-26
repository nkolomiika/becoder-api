import {ReactNode} from "react";

interface FrameHeaderProps {
    className?: string,
    title: string,
    children: ReactNode
}

export function FrameHeader({className = '', title, children} : FrameHeaderProps) {
    return (
        <div className={`rounded-xl border-solid border-2 relative mt-6 ${className}`}>
            <h2 className="font-bold px-2 text-2xl text-slate-700 bg-white absolute -top-5 left-5">{title}</h2>
            {children}
        </div>
    )
}