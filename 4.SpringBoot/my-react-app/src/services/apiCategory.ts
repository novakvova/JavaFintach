import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { APP_ENV } from "../env";
import {ICategory, ICategoryCreate} from "../types/Category.ts";
import {serialize} from "object-to-formdata";

export const apiCategory = createApi({
    reducerPath: "apiCategory",
    baseQuery: fetchBaseQuery({ baseUrl: APP_ENV.REMOTE_BASE_URL }),
    tagTypes: ["Category"], // Додаємо tag для категорій
    endpoints: (builder) => ({
        getCategories: builder.query<ICategory[], void>({
            query: () => "/api/categories",
            providesTags: ["Category"], // Позначаємо, що цей запит пов'язаний з "Category"
        }),

        createCategory: builder.mutation<ICategory, ICategoryCreate>({
            query: (model) => {
                try {
                    const formData = serialize(model);
                    return {
                        url: '/api/categories',
                        method: 'POST',
                        body: formData,
                    };
                } catch {
                    throw new Error("Error serializing the form data.");
                }
            },
            invalidatesTags: ["Category"], // Інвалідовуємо "Category" після створення
        }),
    }),
});

export const { useGetCategoriesQuery, useCreateCategoryMutation } = apiCategory;