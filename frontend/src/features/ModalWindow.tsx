import {ReactNode} from "react";

interface ModalProps {
    isActive: boolean,
    setActive: (status: boolean) => void,
    children: ReactNode
}

export function ModalWindow({isActive, setActive, children}: ModalProps) {
    return (
        <>
            {isActive && <div className="fixed top-0 left-0 w-full h-full">
                <div className="bg-[rgba(0,_0,_0,_.5)] w-full h-full" onClick={() => setActive(false)}/>
                <div className="fixed w-[80%] h-[80%] rounded-xl -translate-x-1/2 left-1/2 top-20 p-4 bg-white shadow-[3px_3px_orange]">
                    {children}
                </div>
            </div>}
        </>
    )
}