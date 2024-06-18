import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from 'axios';
import {test, beforeEach, describe, vi, expect, Mocked} from 'vitest';
import {TransactionForm} from "./TransactionForm.tsx";
import '@testing-library/jest-dom/vitest'

vi.mock('axios');
const mockedAxios = axios as Mocked<typeof axios>;

describe('TransactionForm testing', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    test('renders input fields and submit button', () => {
        render(<TransactionForm />);

        expect(screen.getByPlaceholderText("Where to send money")).toBeInTheDocument();
        expect(screen.getByPlaceholderText("How much")).toBeInTheDocument();
        expect(screen.getByText("Submit")).toBeInTheDocument();
    });

    test('updates input fields on change', () => {

        const sellerIdInput = screen.getByPlaceholderText("Where to send money");
        const sumInput = screen.getByPlaceholderText("How much");

        fireEvent.change(sellerIdInput, { target: { value: "12345" } });
        fireEvent.change(sumInput, { target: { value: "100" } });

        expect(sellerIdInput).toHaveValue("12345");
        expect(sumInput).toHaveValue('100');
    });

    test('submits form and handles successful response', async () => {
        mockedAxios.post.mockResolvedValue({ data: { message: "Transaction successful" } });
        window.alert = vi.fn();


        const sellerIdInput = screen.getByPlaceholderText("Where to send money");
        const sumInput = screen.getByPlaceholderText("How much");
        const submitButton = screen.getByText("Submit");

        fireEvent.change(sellerIdInput, { target: { value: "12345" } });
        fireEvent.change(sumInput, { target: { value: "100" } });

        fireEvent.click(submitButton);

        await waitFor(() => {
            expect(mockedAxios.post).toHaveBeenCalledWith(
                'http://localhost:6868/api/transactions/contract',
                { sellerId: "12345", sum: '100' },
                { headers: { Authorization: 'Bearer ' + sessionStorage.getItem("token") } }
            );
            expect(window.alert).toHaveBeenCalledWith("Transaction successful");
        });
    });

    test('handles error response', async () => {
        mockedAxios.post.mockRejectedValue(new Error("Transaction failed"));
        window.alert = vi.fn();

        const sellerIdInput = screen.getByPlaceholderText("Where to send money");
        const sumInput = screen.getByPlaceholderText("How much");
        const submitButton = screen.getByText("Submit");

        fireEvent.change(sellerIdInput, { target: { value: "12345" } });
        fireEvent.change(sumInput, { target: { value: "100" } });

        fireEvent.click(submitButton);

        await waitFor(() => {
            expect(mockedAxios.post).toHaveBeenCalledWith(
                'http://localhost:6868/api/transactions/contract',
                { sellerId: "12345", sum: '100' },
                { headers: { Authorization: 'Bearer ' + sessionStorage.getItem("token") } }
            );
            expect(window.alert).toHaveBeenCalledWith(new Error("Transaction failed"));
        });
    });
});
