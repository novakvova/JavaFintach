import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { APP_ENV } from "../env";
import {IProduct} from "../types/Product.ts";

export const apiProduct = createApi({
    reducerPath: "apiProduct",
    baseQuery: fetchBaseQuery({ baseUrl: APP_ENV.REMOTE_BASE_URL }),
    tagTypes: ["Product"], // Додаємо tag для категорій
    endpoints: (builder) => ({
        getProducts: builder.query<IProduct[], void>({
            query: () => "/api/products",
            providesTags: ["Product"], // Позначаємо, що цей запит пов'язаний з "Category"
        }),
    }),
});

export const { useGetProductsQuery } = apiProduct;