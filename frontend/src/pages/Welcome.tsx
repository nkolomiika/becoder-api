import {WalletView} from "../widgets/WalletView.tsx";
import {ACCOUNTS} from "../shared/constants/accounts.ts";
import {TransactionHistory} from "../widgets/TransactionHistory.tsx";
import {ModalWindowProvider} from "../shared/contexts/ModalContext.tsx";

export function Welcome() {
    return (
        <ModalWindowProvider>
            <div className="flex p-6 gap-4">
                <WalletView account={ACCOUNTS[0]}/>
                <TransactionHistory />
            </div>
        </ModalWindowProvider>

    )
}