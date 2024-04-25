import {TransactionBlock} from "../entities/TransactionBlock.tsx";
import {TRANSACTIONS} from "../shared/constants/transactions.ts"
import {FrameHeader} from "../features/FrameHeader.tsx";

export function TransactionHistory() {
    return (
        <FrameHeader className="w-[400px]" title="Last operations">
            <div className="flex flex-col w-full gap-y-4 p-4">
                {TRANSACTIONS.slice(0,3).map((trans, ind) => <TransactionBlock transaction={trans} key={ind}/>)}
                <button className="bg-blue-400 rounded-xl text-white p-4 text-xl font-bold curson-pointer">See more</button>
            </div>
        </FrameHeader>
    )
}