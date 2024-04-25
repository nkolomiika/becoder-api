import {WalletView} from "../widgets/WalletView.tsx";
import {ACCOUNTS} from "../shared/constants/accounts.ts";
import {TransactionHistory} from "../widgets/TransactionHistory.tsx";

export function Welcome() {
    return (
        <div className="flex p-6 gap-4">
            <WalletView account={ACCOUNTS[0]}/>
            <TransactionHistory />
        </div>
    )
}