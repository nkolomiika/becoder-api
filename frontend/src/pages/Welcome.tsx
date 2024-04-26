import {WalletView} from "../widgets/WalletView.tsx";
import {TransactionHistory} from "../widgets/TransactionHistory.tsx";
import {ModalWindowProvider} from "../shared/contexts/ModalContext.tsx";
import {useEffect, useState} from "react";
import axios from 'axios'
import {useNavigate} from "react-router-dom";
import {AccountInfo} from "../shared/types/accountTypes.ts";

export function Welcome() {
    const [balance, setBalance] = useState<AccountInfo>({id: '', balance: -1})

    const navigate = useNavigate()

    useEffect(() => {
        // sessionStorage.setItem("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXdrIiwiaWQiOiJrQ2JhU2lPVVR3aVQ0NGtPNmFWcDVvbDA1cTNqR1NMbiIsImV4cCI6MTcxNDcwOTM5Nn0.rBR7Fq7aydgpGSb4SsxiwRoLR_8RwmqJGJwJlUV0UX8")
        axios.get('http://localhost:6868/api/auth/check', {
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }
        }).then(resp => setBalance(resp.data))
            .catch(err => {
                if (err.response.status === 403 || err.response.status === 400) {
                    navigate('/login')
                } else {
                    alert(err.message)
                }
            })
    }, []);

    return (
         <ModalWindowProvider>
             {balance.balance > 0 && <div className="flex p-6 gap-4">
                <WalletView account={balance}/>
                <TransactionHistory />
            </div>}
        </ModalWindowProvider>
    )
}