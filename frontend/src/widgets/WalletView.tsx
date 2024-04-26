import {AccountView} from "../entities/AccountView.tsx";
import {AccountProps} from "../shared/types/accountTypes.ts";
import {TransactionButton} from "../slices/TransactionButton.tsx";
import donateArrow from "../shared/assets/icons/donate_arrow.png"
import fillArrow from "../shared/assets/icons/fill_arrow.png"
import {FrameHeader} from "../features/FrameHeader.tsx";

export function WalletView({account} : AccountProps) {
    return (
        <FrameHeader className="flex flex-col justify-around w-[500px] gap-6 p-6" title="My Wallet">
            <AccountView account={account}/>
            <div className="flex justify-between cursor-pointer">
                <TransactionButton icon={donateArrow} title={'Send'} />
                <TransactionButton icon={fillArrow} title={'Fill'} />
            </div>
        </FrameHeader>
    )
}