import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { APP_ENV } from "../env";
import {ICategory} from "../types/Category.ts";

export const apiCategory = createApi({
    reducerPath: "apiCategory",
    baseQuery: fetchBaseQuery({ baseUrl: APP_ENV.REMOTE_BASE_URL }),
    endpoints: (builder) => ({
        getCategories: builder.query<ICategory[], void>({
            query: () => "/api/categories",
        }),
    }),
});

export const { useGetCategoriesQuery } = apiCategory;