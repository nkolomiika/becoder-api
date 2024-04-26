import {TransactionBlock} from "../entities/TransactionBlock.tsx";
import {FrameHeader} from "../features/FrameHeader.tsx";
import {useContext, useEffect, useState} from "react";
import {ModalContext} from "../shared/contexts/ModalContext.tsx";
import axios from 'axios'
import {ITransaction} from "../shared/types/transactionTypes.ts";

export function TransactionHistory() {
    const {show, setContent} = useContext(ModalContext)
    const [transactions, setTransactions] = useState<ITransaction[]>([])

    useEffect(() => {
        axios.get('http://localhost:6868/api/home/id-transactions', {
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }
        }).then(resp => {setTransactions(resp.data.message); console.log(resp)})
    }, []);

    function replaceContent() {
        show()
        setContent(
            <div className="flex flex-col gap-4">
                <h2 className="text-3xl font-bold">Your transactions</h2>
                <div className="flex flex-col gap-4">
                    {transactions.map((trans, ind) => <TransactionBlock transaction={trans} key={ind}/>)}
                </div>
            </div>
        )
    }

    return (
        <FrameHeader className="w-[600px] p-2" title="Last operations">
            <div className="flex flex-col w-full gap-y-4 p-4">
                {transactions.slice(0,3).map((trans, ind) => <TransactionBlock transaction={trans} key={ind}/>)}
                <button className="bg-blue-400 rounded-xl text-white p-4 text-xl font-bold curson-pointer"
                    onClick={replaceContent}>See more</button>
            </div>
        </FrameHeader>
    )
}