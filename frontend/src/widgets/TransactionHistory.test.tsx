import {fireEvent, render, screen, waitFor} from "@testing-library/react";
import {ModalWindowProvider} from "../shared/contexts/ModalContext";
import axios from 'axios';
import {test, afterEach, beforeEach, describe, Mocked, vi, expect} from 'vitest';
import {TransactionHistory} from "./TransactionHistory.tsx";
import '@testing-library/jest-dom/vitest'

// Mock axios
vi.mock('axios');
const mockedAxios = axios as Mocked<typeof axios>;

describe('TransactionHistory', () => {
    const transactions = [
        {id: 1, sellerId: "Seller 1", timeInit: "2024-06-18T10:00:00Z", cost: 100},
        {id: 2, sellerId: "Seller 2", timeInit: "2024-06-18T11:00:00Z", cost: 200},
        {id: 3, sellerId: "Seller 3", timeInit: "2024-06-18T12:00:00Z", cost: 300},
        {id: 4, sellerId: "Seller 4", timeInit: "2024-06-18T13:00:00Z", cost: 400},
    ];

    beforeEach(() => {

        mockedAxios.get.mockResolvedValue({
            data: {message: transactions}
        });
    });

    afterEach(() => {
        vi.clearAllMocks();
    });

    test('renders transactions on mount', async () => {
        render(
            <ModalWindowProvider>
                <TransactionHistory/>
            </ModalWindowProvider>
        );

        // Check if axios get call was made
        expect(mockedAxios.get).toHaveBeenCalledWith('http://localhost:6868/api/home/id-transactions', {
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }
        });

        // Wait for transactions to be rendered
        await waitFor(() => {
            expect(screen.getByText("Seller 1")).toBeInTheDocument();
            expect(screen.getByText("Seller 2")).toBeInTheDocument();
            expect(screen.getByText("Seller 3")).toBeInTheDocument();
        });

        // Check that only first 3 transactions are rendered initially
        expect(screen.queryByText("Seller 4")).not.toBeInTheDocument();
    });

    test('renders all transactions when "See more" button is clicked', async () => {

        // Wait for initial transactions to be rendered
        await waitFor(() => {
            expect(screen.getByText("Seller 1")).toBeInTheDocument();
            expect(screen.getByText("Seller 2")).toBeInTheDocument();
            expect(screen.getByText("Seller 3")).toBeInTheDocument();
        });

        // Click the "See more" button
        fireEvent.click(screen.getByText("See more"));

        // Check if modal is shown
        await waitFor(() => {
            expect(screen.getByText("Your transactions")).toBeInTheDocument();
        });

        // Check if all transactions are rendered in the modal
        screen.getAllByText("Seller 1").map(block => expect(block).toBeInTheDocument())
        screen.getAllByText("Seller 2").map(block => expect(block).toBeInTheDocument())
        screen.getAllByText("Seller 3").map(block => expect(block).toBeInTheDocument())
        expect(screen.getByText("Seller 4")).toBeInTheDocument();
    });
});
