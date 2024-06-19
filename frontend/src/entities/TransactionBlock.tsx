import {ITransaction} from "../shared/types/transactionTypes.ts";
import clockIcon from "../shared/assets/icons/clock.png"


export function TransactionBlock({transaction} : {transaction: ITransaction}) {
    return (
        <div className="p-4 rounded-xl flex border-2 justify-between items-center">
            <div className="flex gap-x-4">
                <img src={clockIcon} className="h-12 bg-blue-100 rounded-xl p-2" alt="transaction-icon"/>
                <div className="flex flex-col">
                    <span className="transaction-seller-id text-m font-bold">{transaction.sellerId}</span>
                    <span className="text-xs">{transaction.timeInit}</span>
                </div>
            </div>
            <span className={`font-bold ${transaction.cost > 0 ? 'text-green-400' : ''}`}>{transaction.cost} $</span>
        </div>
    )
}