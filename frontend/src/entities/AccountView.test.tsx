import {fireEvent, render, screen} from "@testing-library/react";
import { AccountView } from "./AccountView.tsx";
import {expect, test, describe } from "vitest";
import '@testing-library/jest-dom/vitest'
import {AccountProps} from "../shared/types/accountTypes.ts";

const mockAccount: AccountProps = {
    account: {
        id: '12345',
        balance: 10000
    }
};

describe('AccountView component testing', () => {
    render(<AccountView {...mockAccount} />);
    const accountIdElement = screen.getByText(/Your ID: 12345/i);
    const balanceElement = screen.getByText('$10000');
    const showBalanceElement = screen.getByText(/Show balance/i);

    test('renders account ID correctly', () => {
        expect(accountIdElement).toBeInTheDocument();
    });

    test('renders balance correctly and initially hides it', () => {
        expect(balanceElement).toBeInTheDocument();
        expect(showBalanceElement).toHaveClass('opacity-1')
        expect(showBalanceElement).toBeInTheDocument();
    });

    test('toggles balance visibility when clicked', () => {
        expect(showBalanceElement).toHaveClass('opacity-1');
        expect(balanceElement).toBeVisible();

        fireEvent.click(showBalanceElement);
        expect(showBalanceElement).toHaveClass('opacity-0');
        expect(balanceElement).toBeVisible();

        fireEvent.click(showBalanceElement);
        expect(showBalanceElement).toHaveClass('opacity-1');
        expect(balanceElement).toBeVisible();
    });
});