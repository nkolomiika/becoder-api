import {useState} from "react";
import {ModalWindow} from "../features/ModalWindow.tsx";

interface TransactionButtonProps {
    icon: string,
    title: string
}

export function TransactionButton({icon, title} : TransactionButtonProps) {
    const [isActive, setActive] = useState(false)

    return (
        <>
            <div className="hover:scale-105 transition-all ease w-[45%] py-[20%] h-0 bg-slate-100 items-center
         rounded-xl shadow-[4px_4px_blue] gap-2 text-2xl flex flex-col p-2 font-bold justify-center"
                 onClick={() => setActive(true)}>
                <img className="w-[40%]" src={icon} alt="transaction-icon"/>
                <p>{title}</p>
            </div>
            <ModalWindow isActive={isActive} setActive={setActive}>
                <p>{title}</p>
            </ModalWindow>
        </>

    )
}