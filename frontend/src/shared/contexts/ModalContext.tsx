import {createContext, ReactNode, useState} from "react";

interface IModalContext {
    state: boolean,
    show: () => void
    hide: () => void,
    setContent: (content: ReactNode) => void
}

export const ModalContext = createContext<IModalContext>({
    state: false,
    show: ()=> {},
    hide: () => {},
    setContent: () => {}
})

export function ModalWindowProvider({children}: {children : ReactNode}) {
    const [isActive, setActive] = useState(true)
    const [content, setContent] = useState<ReactNode>(<div></div>)
    function show() {
        setActive(true)
    }

    function hide() {
        setActive(false)
    }

    return (
        <ModalContext.Provider value={{state: isActive, show, hide, setContent}}>
            {children}
            {isActive && <div className="fixed top-0 left-0 w-full h-full">
                <div className="bg-[rgba(0,_0,_0,_.5)] w-full h-full" onClick={() => setActive(false)}/>
                <div className="fixed w-[80%] h-[80%] rounded-xl -translate-x-1/2 left-1/2 top-20 py-8 px-20 bg-white">
                    {content}
                </div>
            </div>}
        </ModalContext.Provider>
    )
}