import {TransactionBlock} from "../entities/TransactionBlock.tsx";
import {TRANSACTIONS} from "../shared/constants/transactions.ts"
import {FrameHeader} from "../features/FrameHeader.tsx";
import {useContext} from "react";
import {ModalContext} from "../shared/contexts/ModalContext.tsx";

export function TransactionHistory() {
    const {show, setContent} = useContext(ModalContext)

    function replaceContent() {
        show()
        setContent(
            <div className="flex flex-col gap-4">
                <h2 className="text-3xl font-bold">Your transactions</h2>
                <div className="flex flex-col gap-4">
                    {TRANSACTIONS.map((trans, ind) => <TransactionBlock transaction={trans} key={ind}/>)}
                </div>
            </div>
        )
    }

    return (
        <FrameHeader className="w-[400px] p-2" title="Last operations">
            <div className="flex flex-col w-full gap-y-4 p-4">
                {TRANSACTIONS.slice(0,3).map((trans, ind) => <TransactionBlock transaction={trans} key={ind}/>)}
                <button className="bg-blue-400 rounded-xl text-white p-4 text-xl font-bold curson-pointer"
                    onClick={replaceContent}>See more</button>
            </div>
        </FrameHeader>
    )
}