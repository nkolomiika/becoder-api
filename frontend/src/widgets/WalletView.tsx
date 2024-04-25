import {AccountView} from "../entities/AccountView.tsx";
import {AccountProps} from "../shared/types/accountTypes.ts";
import {TransactionButton} from "../slices/TransactionButton.tsx";
import donateArrow from "../shared/assets/icons/donate_arrow.png"
import fillArrow from "../shared/assets/icons/fill_arrow.png"

export function WalletView({account} : AccountProps) {
    return (
        <div className="flex flex-col gap-y-4 w-[350px] p-4 rounded-xl border-solid border-2 relative pt-10 m-10">
            <h2 className="font-bold px-2 text-2xl text-slate-700 bg-white absolute -top-5 left-5">My Wallet</h2>
            <AccountView account={account}/>
            <div className="flex justify-between cursor-pointer">
                <TransactionButton icon={donateArrow} title={'Send'} />
                <TransactionButton icon={fillArrow} title={'Fill'} />
            </div>
        </div>
    )
}