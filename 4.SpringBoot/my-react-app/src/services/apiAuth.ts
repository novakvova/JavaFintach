// Створюємо API Slice
import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";
import {APP_ENV} from "../env";
import {IUserRegisterRequest} from "../pages/auth/types.ts";


export const apiAuth = createApi({
    reducerPath: 'authApi', // Унікальний шлях для цього API у Redux Store
    baseQuery: fetchBaseQuery({ baseUrl: `${APP_ENV.REMOTE_BASE_URL}/api/auth` }), // Базовий URL
    tagTypes: ["AuthUser"], // Додаємо tag для категорій
    endpoints: (builder) => ({

        registerUser: builder.mutation<void, IUserRegisterRequest>({
            query: (userRegister) => ({
                url: "register",
                method: "POST",
                body: userRegister,
            }),
            //invalidatesTags: ["AuthUser"], // Інвалідовуємо "Category" після створення
        })
    }),
});

// Автоматично згенерований хук
export const {
    useRegisterUserMutation,} = apiAuth;