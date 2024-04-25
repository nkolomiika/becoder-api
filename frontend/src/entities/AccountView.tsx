import {AccountProps} from "../shared/types/accountTypes.ts"
import {useState} from "react";

export function AccountView({account} : AccountProps) {
    const [isHidden, setHidden] = useState(true)

    function changeBalanceView() {
        setHidden(prevState => !prevState)
    }

    return (
        <div className="h-[200px] w-full bg-black rounded-xl text-xs
         content-center flex flex-col justify-between font-mono text-gray-600 overflow-hidden">
            <p className="text-right py-2 px-4">Your ID: {account.id}</p>
            <div className="relative cursor-pointer text-center w-[60%] mx-auto mb-6" onClick={changeBalanceView}>
                <div className={`font-bold h-full w-full absolute bg-black mx-auto flex justify-center 
                transition-all ease text-xl flex items-center ${isHidden? 'opacity-1' : 'opacity-0'}`}>
                    Show balance
                </div>
                <p className="text-3xl text-white">${account.balance}</p>
            </div>
        </div>
    )
}