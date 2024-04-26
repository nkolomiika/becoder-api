import {Input} from "../slices/Input.tsx";
import {ChangeEvent, FormEvent, useState} from "react";
import axios from 'axios'

interface FormValues {
    seller_id: string,
    cost: number
}

export function TransactionForm() {
    const [responseBody, setResponseBody] = useState<FormValues>({seller_id: "", cost: 0})
    const inputChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target
        setResponseBody({...responseBody, [name]: value})
    }
    const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.post('http://localhost:6868/api/transactions/contract', {
            seller_id: responseBody.seller_id,
            cost: responseBody.cost
        }, {
            headers : {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            }
        }).then(res => alert(res.data.message))
            .catch(err => alert(err))
    }
    return (
        <form className="flex flex-col gap-4" onSubmit={onSubmitHandler}>
            <div>
                <Input title="Account ID" placeholder="Where to send money" onChange={inputChangeHandler}/>
                <Input title="Amount" placeholder="How much" onChange={inputChangeHandler}/>
            </div>
            <button className="bg-blue-400 p-4 text-xl rounded-xl text-white font-bold">Submit</button>
        </form>
    )
}