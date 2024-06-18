import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import axios from 'axios';
import {test, beforeEach, describe, Mocked, vi, expect, beforeAll} from 'vitest';
import '@testing-library/jest-dom/vitest'
import {RegisterForm} from "./RegisterForm.tsx";


vi.mock('axios');
const mockedAxios = axios as Mocked<typeof axios>;

describe('RegisterForm testing', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    beforeAll(() => {
        Object.defineProperty(window, 'sessionStorage', {
            value: {
                getItem: vi.fn(),
                setItem: vi.fn(),
                removeItem: vi.fn(),
                clear: vi.fn(),
            },
            writable: true,
        });
    })

    render(
        <BrowserRouter>
            <RegisterForm />
        </BrowserRouter>
    );

    test('renders input fields and register button', () => {

        expect(screen.getByPlaceholderText("Username")).toBeInTheDocument();
        expect(screen.getByPlaceholderText("Password")).toBeInTheDocument();
        expect(screen.getByPlaceholderText("Repeat password")).toBeInTheDocument();
    });

    test('updates input fields on change', () => {

        const usernameInput = screen.getByPlaceholderText("Username");
        const passwordInput = screen.getByPlaceholderText("Password");
        const repeatedPasswordInput = screen.getByPlaceholderText("Repeat password");

        fireEvent.change(usernameInput, { target: { value: "testuser" } });
        fireEvent.change(passwordInput, { target: { value: "password" } });
        fireEvent.change(repeatedPasswordInput, { target: { value: "password" } });

        expect(usernameInput).toHaveValue("testuser");
        expect(passwordInput).toHaveValue("password");
        expect(repeatedPasswordInput).toHaveValue("password");
    });

    test('submits form and handles successful response', async () => {
        mockedAxios.post.mockResolvedValue({ data: { token: "fake-token" } });
        window.alert = vi.fn();


        const usernameInput = screen.getByPlaceholderText("Username");
        const passwordInput = screen.getByPlaceholderText("Password");
        const repeatedPasswordInput = screen.getByPlaceholderText("Repeat password");
        const registerButton = screen.getByTitle("register-button");

        fireEvent.change(usernameInput, { target: { value: "testuser" } });
        fireEvent.change(passwordInput, { target: { value: "password" } });
        fireEvent.change(repeatedPasswordInput, { target: { value: "password" } });

        fireEvent.click(registerButton);

        await waitFor(() => {
            expect(mockedAxios.post).toHaveBeenCalledWith(
                'http://localhost:6868/api/auth/register',
                { login: "testuser", password: "password" }
            );
            expect(sessionStorage.setItem).toHaveBeenCalledWith("token", "fake-token");
        });
    });

    test('handles error response', async () => {
        mockedAxios.post.mockRejectedValue(new Error("Registration failed"));
        window.alert = vi.fn();


        const usernameInput = screen.getByPlaceholderText("Username");
        const passwordInput = screen.getByPlaceholderText("Password");
        const repeatedPasswordInput = screen.getByPlaceholderText("Repeat password");
        const errorMessage = screen.getByTitle("error-message");
        const registerButton = screen.getByTitle("register-button");

        fireEvent.change(usernameInput, { target: { value: "testuser" } });
        fireEvent.change(passwordInput, { target: { value: "password" } });
        fireEvent.change(repeatedPasswordInput, { target: { value: "password" } });

        fireEvent.click(registerButton);

        await waitFor(() => {
            expect(mockedAxios.post).toHaveBeenCalledWith(
                'http://localhost:6868/api/auth/register',
                { login: "testuser", password: "password" }
            );
            expect(errorMessage).toBeVisible()
        });
    });
});
