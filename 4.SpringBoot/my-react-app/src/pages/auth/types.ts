import {ReactNode} from "react";

export interface IUserRegisterRequest {
    username: string;         // Назва категорії (обов'язкове поле)     // Унікальний ідентифікатор (обов'язкове поле)
    password: string;
}

export interface IUserLoginRequest {
    username: string;         // Назва категорії (обов'язкове поле)     // Унікальний ідентифікатор (обов'язкове поле)
    password: string;
}


export interface LoginButtonProps{
    title:string
    onLogin:(token: string) => void
    icon:ReactNode
}