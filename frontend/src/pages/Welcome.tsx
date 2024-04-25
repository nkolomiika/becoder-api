import {WalletView} from "../widgets/WalletView.tsx";
import {ACCOUNTS} from "../shared/constants/accounts.ts";

export function Welcome() {
    return (
        <div>
            <WalletView account={ACCOUNTS[0]}/>
        </div>
    )
}