import {useContext} from "react";
import {ModalContext} from "../shared/contexts/ModalContext.tsx";
import {TransactionForm} from "../widgets/TransactionForm.tsx";

interface TransactionButtonProps {
    icon: string,
    title: string
}

export function TransactionButton({icon, title} : TransactionButtonProps) {
    const {show, setContent} = useContext(ModalContext)

    function replaceContent() {
        show()
        setContent(
            <div className="flex flex-col gap-4">
                <h2 className="font-bold text-3xl">Commit transaction</h2>
                <TransactionForm/>
            </div>
        )
    }

    return (
        <>
        <div className="hover:scale-105 transition-all ease w-[45%] py-[20%] h-0 bg-slate-100 items-center
         rounded-xl gap-2 text-2xl flex flex-col p-2 font-bold justify-center" onClick={replaceContent}>
                <img className="w-[40%]" src={icon} alt="transaction-icon"/>
                <p>{title}</p>
            </div>
        </>

    )
}