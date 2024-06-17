import { render, screen } from "@testing-library/react";
import { AccountView } from "./AccountView.tsx";
import {expect, test, describe } from "vitest";
import '@testing-library/jest-dom/vitest'

describe('Test balance viewing', () => {
    test('Account element: exist in DOM', () => {
        render(<AccountView account={{id: 'test', balance: 0}}/>)
        expect(screen.getByText('$0')).toBeInTheDocument()
    })
})