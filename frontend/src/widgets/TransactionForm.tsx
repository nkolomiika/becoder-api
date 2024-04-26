import {Input} from "../slices/Input.tsx";

export function TransactionForm() {
    return (
        <div className="flex flex-col gap-4">
            <div>
                <Input title="Account ID" placeholder="Where to send money"/>
                <Input title="Amount" placeholder="How much"/>
            </div>
            <button className="bg-blue-400 p-4 text-xl rounded-xl text-white font-bold">Submit</button>
        </div>
    )
}